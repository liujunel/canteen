package com.canteen.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.domain.Faculty;
import com.canteen.sys.domain.Role;
import com.canteen.sys.domain.Section;
import com.canteen.sys.service.FacultyService;
import com.canteen.sys.service.RoleService;
import com.canteen.sys.service.SectionService;
import com.canteen.sys.vo.FacultyVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教工管理
 *
 * @author:junle
 * @create:2020/2/19-10:27
 */
@RestController
@RequestMapping("faculty")
@Slf4j
public class FacultyController {

    @Autowired
    @Lazy
    private FacultyService facultyService;

    @Autowired
    @Lazy
    private SectionService sectionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * 全查询
     *
     * @param facultyVo
     * @return
     */
    @GetMapping("facultys")
    public DataGridView loadAll(FacultyVo facultyVo) {

        IPage<Faculty> page = new Page<>(facultyVo.getPage(), facultyVo.getLimit());
        QueryWrapper<Faculty> queryWrapper = new QueryWrapper<>();
        // 工号
        queryWrapper.like(null != facultyVo.getFacultyNumber(), Faculty.COL_FACULTY_NUMBER, facultyVo.getFacultyNumber());
        // 姓名
        queryWrapper.like(StringUtils.isNotBlank(facultyVo.getFacultyName()), Faculty.COL_FACULTY_NAME, facultyVo.getFacultyName());
        // 领导
        if (StringUtils.isNotBlank(facultyVo.getLeaderName())) {
            QueryWrapper<Faculty> leaderQueryWrapper = new QueryWrapper<>();
            leaderQueryWrapper.like(Faculty.COL_FACULTY_NAME, facultyVo.getLeaderName());
            Faculty leader = facultyService.getOne(leaderQueryWrapper);
            queryWrapper.eq(Faculty.COL_FACULTY_MGR, leader.getFacultyNumber());
        }
        // 系别
        if (null != facultyVo.getFacultySectionId() && facultyVo.getFacultySectionId() != 0) {
            queryWrapper.eq(Faculty.COL_FACULTY_SECTION_ID, facultyVo.getFacultySectionId());
        }
        // 入职日期
        queryWrapper.ge(null != facultyVo.getStartTime(), Faculty.COL_FACULTY_HIREDATE, facultyVo.getStartTime());
        queryWrapper.le(null != facultyVo.getEndTime(), Faculty.COL_FACULTY_HIREDATE, facultyVo.getEndTime());
        facultyService.page(page, queryWrapper);
        // 对数据进行处理
        List<Faculty> faculties = page.getRecords();
        for (Faculty faculty : faculties) {
            // 处理系别
            Integer sectionId = faculty.getFacultySectionId();
            Section section = sectionService.getById(sectionId);
            faculty.setFacultySectionName(section.getSectionName());

            // 处理领导
            Integer mgrId = faculty.getFacultyMgr();
            if (null != mgrId) {
                Faculty mgr = null;
                try {
                    mgr = facultyService.getById(mgrId);
                    faculty.setFacultyLeaderName(mgr.getFacultyName());
                } catch (Exception e) {
                    log.info("教工：【" + faculty.getFacultyName() + "】无领导 " + e.getMessage());
                    faculty.setFacultyLeaderName("暂时无领导");
                }
            } else {
                faculty.setFacultyLeaderName("无领导");
            }

            // 处理角色
            Integer roleId = roleService.queryRoleByFacultyNumber(faculty.getFacultyNumber());
            Role role = roleService.getById(roleId);
            if (null != role) {
                faculty.setFacultyRoleName(role.getRoleName());
            }
        }
        return new DataGridView(page.getTotal(), faculties);
    }

    /**
     * 处理添加时 输入领导不存在的情况
     *
     * @param leaderName
     * @return
     */
    @GetMapping("queryLeaderByLeaderName")
    public ResultObj queryLeaderByLeaderName(String leaderName) {
        Faculty leader = facultyService.queryLeaderByLeaderName(leaderName);
        if (null != leader) {
            return new ResultObj(200, "该领导存在");
        } else {
            return new ResultObj(-1, "领导【" + leaderName + "】不存在, 请重新输入");
        }
    }

    /**
     * 修改时回显领导
     *
     * @param facultyMgr
     * @return
     */
    @GetMapping("queryLeaderByMgr")
    public DataGridView queryLeaderByMgr(Integer facultyMgr) {
        if (null != facultyMgr) {
            Faculty leader = facultyService.getById(facultyMgr);
            return new DataGridView(200, leader);
        } else {
            return new DataGridView(-1, null);
        }
    }

    /**
     * 添加
     * restful post
     *
     * @param facultyVo
     * @return
     */
    @PostMapping("faculty")
    public ResultObj add(FacultyVo facultyVo) {
        try {
            // 处理图片（将临时图片更为名字 去掉_temp）
            String imgPath = facultyVo.getFacultyImgPath();
            if (StringUtils.isNotBlank(imgPath) && imgPath.endsWith("_temp")) {
                imgPath = FileUploadController.renameFile(imgPath);
                facultyVo.setFacultyImgPath(imgPath);
            }
            if (facultyVo.getFacultySectionId() == 0) {
                throw new Exception("请选择系别");
            }
            // 处理领导
            if (StringUtils.isNotBlank(facultyVo.getLeaderName())) {
                Faculty leader = facultyService.queryLeaderByLeaderName(facultyVo.getLeaderName());
                facultyVo.setFacultyMgr(leader.getFacultyNumber());
            }
            // 添加
            facultyVo.setFacultyPassword(new Md5Hash("123456", "饭堂", 2).toString());
            // 保存
            facultyService.save(facultyVo);
            // 设置默认角色【教职工】
            Integer number = facultyVo.getFacultyNumber();
            // 向角色和教工关联表中插入数据
            roleService.insertRoleFacultyByRoleIdAndFacultyNumber(2, number);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param facultyVo
     * @return
     */
    @PutMapping("faculty")
    public ResultObj update(FacultyVo facultyVo) {
        try {
            // 处理图片（将临时图片更为名字 去掉_temp）
            String imgPath = facultyVo.getFacultyImgPath();
            if (!(StringUtils.isNotBlank(imgPath) && imgPath.equals("default.jpg"))) {
                if (imgPath.endsWith("_temp")) {
                    imgPath = FileUploadController.renameFile(imgPath);
                    facultyVo.setFacultyImgPath(imgPath);
                    // 删除原来的图片
                    String facultyImgPath = facultyService.getById(facultyVo.getFacultyNumber()).getFacultyImgPath();
                    FileUploadController.removeFileByPath(facultyImgPath);
                }
            }

            // 处理领导
            if (StringUtils.isNotBlank(facultyVo.getLeaderName())) {
                Faculty leader = facultyService.queryLeaderByLeaderName(facultyVo.getLeaderName());
                facultyVo.setFacultyMgr(leader.getFacultyNumber());
            }
            facultyService.updateById(facultyVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     * 1. 删除当前教工
     * 2. 删除角色和教工所关联的数据
     *
     * @param facultyNumber
     * @return
     */
    @DeleteMapping("faculty/{facultyNumber}")
    public ResultObj delete(@PathVariable("facultyNumber") Integer facultyNumber) {
        try {
            // 删除图片
            Faculty faculty = facultyService.getById(facultyNumber);
            FileUploadController.removeFileByPath(faculty.getFacultyImgPath());
            facultyService.removeById(facultyNumber);
            // 删除角色和教工所关联的数据
            roleService.removeRoleFacultyByFacultyNumber(facultyNumber);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 分配角色
     * 1. 通过教工id删除角色和教工关系表中的数据
     * 2. 向角色和教工关系表中插入数据
     *
     * @param roleId
     * @param facultyNumber
     * @return
     */
    @PostMapping("assignRoleByRoleIdAndFacultyNumber")
    public ResultObj assignRoleByRoleIdAndFacultyNumber(Integer roleId, Integer facultyNumber) {
        try {
            // 1.
            roleService.removeRoleFacultyByFacultyNumber(facultyNumber);
            // 2.
            roleService.insertRoleFacultyByRoleIdAndFacultyNumber(roleId, facultyNumber);
            log.info("教工工号为：" + facultyNumber + "的角色【" + roleId + "】分配成功");
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            log.info("教工工号为：" + facultyNumber + "的角色【" + roleId + "】分配失败");
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 重置教工密码
     * 初始值为123456
     *
     * @param facultyNumber
     * @return
     */
    @PostMapping("resetFacultyPassword")
    public ResultObj resetFacultyPassword(Integer facultyNumber) {
        try {
            Faculty faculty = facultyService.getById(facultyNumber);
            faculty.setFacultyPassword(new Md5Hash("123456", "饭堂", 2).toString());
            facultyService.updateById(faculty);
            return new ResultObj(200, "重置工号为：" + facultyNumber + " 的密码成功");
        } catch (Exception e) {
            log.info("重置工号为：" + facultyNumber + "的密码失败" + e.getMessage());
            return new ResultObj(-1, "重置工号为：" + facultyNumber + " 的密码失败");
        }
    }

    /**
     * 修改密码
     *
     * @param facultyNumber
     * @param password
     * @return
     */
    @PostMapping("changePassword")
    public ResultObj changePassword(Integer facultyNumber, String password) {
        try {
            Faculty faculty = facultyService.getById(facultyNumber);
            faculty.setFacultyPassword(new Md5Hash(password, "饭堂", 2).toString());
            facultyService.updateById(faculty);
            return new ResultObj(200, "修改密码成功, 请重新登录");
        } catch (Exception e) {
            log.info("工号为：" + facultyNumber + " 修改密码失败" + e.getMessage());
            return new ResultObj(-1, "修改密码失败");
        }
    }

    @GetMapping("queryFacultyByFacultyNumber")
    public DataGridView queryFacultyByFacultyNumber(Integer facultyNumber) {
        Faculty faculty = facultyService.getById(facultyNumber);
        if (null != faculty) {
            return new DataGridView(200, faculty);
        } else {
            return new DataGridView(-1, null);
        }
    }
}
