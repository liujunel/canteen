package com.canteen.sys.cache;

import com.canteen.sys.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存处理
 *
 * @author:junle
 * @create:2020/2/19-11:17
 */
@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class Cache {

    // 缓存容器
    private Map<String, Object> cacheMap = new HashMap<>();


    //----------------------------------对系别进行缓存-------------------------------------
    // 对系别进行CRUD的缓存处理
    public static final String POINTCUT_SECTION_ADD = "execution(* com.canteen.sys.service.impl.SectionServiceImpl.save(..))";
    public static final String POINTCUT_SECTION_GET = "execution(* com.canteen.sys.service.impl.SectionServiceImpl.getById(..))";
    public static final String POINTCUT_SECTION_DELETE = "execution(* com.canteen.sys.service.impl.SectionServiceImpl.removeById(..))";
    public static final String POINTCUT_SECTION_UPDATE = "execution(* com.canteen.sys.service.impl.SectionServiceImpl.updateById(..))";
    // 定义系别缓存的前缀
    public static final String CACHE_SECTION_PREFIX = "section:";


    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_SECTION_ADD)
    public Object sectionAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Section section = (Section) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_SECTION_PREFIX + section.getSectionId(), section);
            log.info("【系别对象】，缓存已添加" + CACHE_SECTION_PREFIX + section.getSectionId());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_SECTION_GET)
    public Object sectionGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        Section section1 = (Section) cacheMap.get(CACHE_SECTION_PREFIX + sectionId);
        if (null != section1) {
            log.info("已从缓存中找到【系别对象】" + CACHE_SECTION_PREFIX + sectionId);
            return section1;
        } else {
            // 执行目标方法
            Section section2 = (Section) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_SECTION_PREFIX + section2.getSectionId(), section2);
            log.info("未从缓存中找到【系别对象】，去数据库查询" + CACHE_SECTION_PREFIX + section2.getSectionId());
            return section2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_SECTION_UPDATE)
    public Object sectionUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Section section = (Section) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_SECTION_PREFIX + section.getSectionId());
            cacheMap.put(CACHE_SECTION_PREFIX + section.getSectionId(), section);
            log.info("【系别对象】，缓存已更新" + CACHE_SECTION_PREFIX + section.getSectionId());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_SECTION_DELETE)
    public Object sectionDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_SECTION_PREFIX + sectionId);
            log.info("【系别对象】，缓存已删除" + CACHE_SECTION_PREFIX + sectionId);
        }
        return flag;
    }

    //----------------------------------对教工进行缓存-------------------------------------

    // 对教工进行CRUD的缓存处理
    public static final String POINTCUT_FACULTY_ADD = "execution(* com.canteen.sys.service.impl.FacultyServiceImpl.save(..))";
    public static final String POINTCUT_FACULTY_GET = "execution(* com.canteen.sys.service.impl.FacultyServiceImpl.getById(..))";
    public static final String POINTCUT_FACULTY_DELETE = "execution(* com.canteen.sys.service.impl.FacultyServiceImpl.removeById(..))";
    public static final String POINTCUT_FACULTY_UPDATE = "execution(* com.canteen.sys.service.impl.FacultyServiceImpl.updateById(..))";
    // 定义教工缓存的前缀
    public static final String CACHE_FACULTY_PREFIX = "faculty:";

    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_FACULTY_ADD)
    public Object facultyAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Faculty faculty = (Faculty) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_FACULTY_PREFIX + faculty.getFacultyNumber(), faculty);
            log.info("【教工对象】，缓存已添加" + CACHE_FACULTY_PREFIX + faculty.getFacultyNumber());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_FACULTY_GET)
    public Object facultyGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer facultyNumber = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        Faculty faculty1 = (Faculty) cacheMap.get(CACHE_FACULTY_PREFIX + facultyNumber);
        if (null != faculty1) {
            log.info("已从缓存中找到【教工对象】" + CACHE_FACULTY_PREFIX + facultyNumber);
            return faculty1;
        } else {
            // 执行目标方法
            Faculty faculty2 = (Faculty) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_FACULTY_PREFIX + faculty2.getFacultyNumber(), faculty2);
            log.info("未从缓存中找到【系别对象】，去数据库查询" + CACHE_FACULTY_PREFIX + faculty2.getFacultyNumber());
            return faculty2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_FACULTY_UPDATE)
    public Object facultyUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Faculty faculty = (Faculty) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_FACULTY_PREFIX + faculty.getFacultyNumber());
            cacheMap.put(CACHE_FACULTY_PREFIX + faculty.getFacultyNumber(), faculty);
            log.info("【教工对象】，缓存已更新" + CACHE_FACULTY_PREFIX + faculty.getFacultyNumber());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_FACULTY_DELETE)
    public Object facultyDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer facultyNumber = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_FACULTY_PREFIX + facultyNumber);
            log.info("【教工对象】，缓存已删除" + CACHE_FACULTY_PREFIX + facultyNumber);
        }
        return flag;
    }

    //----------------------------------对角色进行缓存-------------------------------------
    // 对角色进行CRUD的缓存处理
    public static final String POINTCUT_ROLE_ADD = "execution(* com.canteen.sys.service.impl.RoleServiceImpl.save(..))";
    public static final String POINTCUT_ROLE_GET = "execution(* com.canteen.sys.service.impl.RoleServiceImpl.getById(..))";
    public static final String POINTCUT_ROLE_DELETE = "execution(* com.canteen.sys.service.impl.RoleServiceImpl.removeById(..))";
    public static final String POINTCUT_ROLE_UPDATE = "execution(* com.canteen.sys.service.impl.RoleServiceImpl.updateById(..))";
    // 定义角色缓存的前缀
    public static final String CACHE_ROLE_PREFIX = "role:";

    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_ROLE_ADD)
    public Object roleAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Role role = (Role) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_ROLE_PREFIX + role.getRoleId(), role);
            log.info("【角色对象】，缓存已添加" + CACHE_ROLE_PREFIX + role.getRoleId());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_ROLE_GET)
    public Object roleGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer roleId = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        Role role1 = (Role) cacheMap.get(CACHE_ROLE_PREFIX + roleId);
        if (null != role1) {
            log.info("已从缓存中找到【角色对象】" + CACHE_ROLE_PREFIX + roleId);
            return role1;
        } else {
            // 执行目标方法
            Role role2 = (Role) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_ROLE_PREFIX + role2.getRoleId(), role2);
            log.info("未从缓存中找到【角色对象】，去数据库查询" + CACHE_ROLE_PREFIX + role2.getRoleId());
            return role2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_ROLE_UPDATE)
    public Object roleUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Role role = (Role) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_ROLE_PREFIX + role.getRoleId());
            cacheMap.put(CACHE_ROLE_PREFIX + role.getRoleId(), role);
            log.info("【角色对象】，缓存已更新" + CACHE_ROLE_PREFIX + role.getRoleId());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_ROLE_DELETE)
    public Object roleDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer roleNumber = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_ROLE_PREFIX + roleNumber);
            log.info("【教工对象】，缓存已删除" + CACHE_ROLE_PREFIX + roleNumber);
        }
        return flag;
    }

    //----------------------------------对饭堂部门进行缓存-------------------------------------
    // 对部门进行CRUD的缓存处理
    public static final String POINTCUT_DEPT_ADD = "execution(* com.canteen.sys.service.impl.DeptServiceImpl.save(..))";
    public static final String POINTCUT_DEPT_GET = "execution(* com.canteen.sys.service.impl.DeptServiceImpl.getById(..))";
    public static final String POINTCUT_DEPT_DELETE = "execution(* com.canteen.sys.service.impl.DeptServiceImpl.removeById(..))";
    public static final String POINTCUT_DEPT_UPDATE = "execution(* com.canteen.sys.service.impl.DeptServiceImpl.updateById(..))";
    // 定义部门缓存的前缀
    public static final String CACHE_DEPT_PREFIX = "dept:";

    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_DEPT_ADD)
    public Object deptAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Dept dept = (Dept) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_DEPT_PREFIX + dept.getDeptId(), dept);
            log.info("【饭堂部门对象】，缓存已添加" + CACHE_DEPT_PREFIX + dept.getDeptId());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_DEPT_GET)
    public Object deptGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer deptId = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        Dept dept1 = (Dept) cacheMap.get(CACHE_DEPT_PREFIX + deptId);
        if (null != dept1) {
            log.info("已从缓存中找到【饭堂部门对象】" + CACHE_DEPT_PREFIX + deptId);
            return dept1;
        } else {
            // 执行目标方法
            Dept dept2 = (Dept) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_DEPT_PREFIX + dept2.getDeptId(), dept2);
            log.info("未从缓存中找到【饭堂部门对象】，去数据库查询" + CACHE_DEPT_PREFIX + dept2.getDeptId());
            return dept2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_DEPT_UPDATE)
    public Object deptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Dept dept = (Dept) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_DEPT_PREFIX + dept.getDeptId());
            cacheMap.put(CACHE_DEPT_PREFIX + dept.getDeptId(), dept);
            log.info("【饭堂部门对象】，缓存已更新" + CACHE_DEPT_PREFIX + dept.getDeptId());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_DEPT_DELETE)
    public Object deptDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer deptId = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_DEPT_PREFIX + deptId);
            log.info("【饭堂部门对象】，缓存已删除" + CACHE_DEPT_PREFIX + deptId);
        }
        return flag;
    }

    //----------------------------------对饭堂员工进行缓存-------------------------------------
    // 对员工进行CRUD的缓存处理
    public static final String POINTCUT_STAFF_ADD = "execution(* com.canteen.sys.service.impl.StaffServiceImpl.save(..))";
    public static final String POINTCUT_STAFF_GET = "execution(* com.canteen.sys.service.impl.StaffServiceImpl.getById(..))";
    public static final String POINTCUT_STAFF_DELETE = "execution(* com.canteen.sys.service.impl.StaffServiceImpl.removeById(..))";
    public static final String POINTCUT_STAFF_UPDATE = "execution(* com.canteen.sys.service.impl.StaffServiceImpl.updateById(..))";
    // 定义员工缓存的前缀
    public static final String CACHE_STAFF_PREFIX = "staff:";

    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_STAFF_ADD)
    public Object staffAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Staff staff = (Staff) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_STAFF_PREFIX + staff.getStaffId(), staff);
            log.info("【饭堂员工对象】，缓存已添加" + CACHE_STAFF_PREFIX + staff.getStaffId());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_STAFF_GET)
    public Object staffGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer staffId = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        Staff staff1 = (Staff) cacheMap.get(CACHE_STAFF_PREFIX + staffId);
        if (null != staff1) {
            log.info("已从缓存中找到【饭堂员工对象】" + CACHE_STAFF_PREFIX + staff1);
            return staff1;
        } else {
            // 执行目标方法
            Staff staff2 = (Staff) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_STAFF_PREFIX + staff2.getStaffId(), staff2);
            log.info("未从缓存中找到【饭堂员工对象】，去数据库查询" + CACHE_STAFF_PREFIX + staff2.getStaffId());
            return staff2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_STAFF_UPDATE)
    public Object staffUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Staff staff = (Staff) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_STAFF_PREFIX + staff.getStaffId());
            cacheMap.put(CACHE_STAFF_PREFIX + staff.getStaffId(), staff);
            log.info("【饭堂员工对象】，缓存已更新" + CACHE_STAFF_PREFIX + staff.getStaffId());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_STAFF_DELETE)
    public Object staffDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer staffId = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_STAFF_PREFIX + staffId);
            log.info("【饭堂员工对象】，缓存已删除" + CACHE_STAFF_PREFIX + staffId);
        }
        return flag;
    }
}
