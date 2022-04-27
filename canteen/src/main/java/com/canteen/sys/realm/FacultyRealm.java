package com.canteen.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.canteen.sys.common.ActiveFaculty;
import com.canteen.sys.common.Constant;
import com.canteen.sys.domain.Faculty;
import com.canteen.sys.domain.Permission;
import com.canteen.sys.service.FacultyService;
import com.canteen.sys.service.PermissionService;
import com.canteen.sys.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;


/**
 * Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。
 * 也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。
 *
 * @author:junle
 * @create:2020/2/15-21:08
 */
public class FacultyRealm extends AuthorizingRealm {

    /**
     * 需要注入用户管理
     */
    @Autowired
    @Lazy
    private FacultyService facultyService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    /**
     * 需要重写getName()方法
     *
     * @return
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取登录的用户名
        String loginNumber = token.getPrincipal().toString();
        // 条件构造器
        QueryWrapper<Faculty> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("faculty_number", loginNumber);
        // 查询数据库会返回一个user对象
        Faculty faculty = facultyService.getOne(queryWrapper);
        if (null != faculty) {
            // 创建带权限的用户
            ActiveFaculty activeFaculty = new ActiveFaculty();
            // 将user注入进去
            activeFaculty.setFaculty(faculty);
            // 密码的盐
            ByteSource credentialsSalt = ByteSource.Util.bytes("饭堂");
            // 存在权限编码
            List<Permission> permissionPercodes = new ArrayList<>();
            // 根据用户ID查询percode
            // 查询所有的权限
            QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
            permissionQueryWrapper.eq(Permission.COL_PERMISSION_TYPE, Constant.TYPE_PERMISSION);
            // 获取当前登录人的id
            Integer facultyNumber = faculty.getFacultyNumber();
            // 获取当前登录人的角色
            Integer roleId = roleService.queryRoleByFacultyNumber(facultyNumber);
            // 根据角色id获取角色所拥有的权限菜单集合
            List<Integer> permissionList = roleService.queryPermissionByRoleId(roleId);
            // 根据权限菜单集合获取子菜单集合
            List<Integer> childrenList = permissionService.queryPermissionByPid(permissionList);
            if (null != childrenList && childrenList.size() > 0) {
                permissionQueryWrapper.in(Permission.COL_PERMISSION_PID, childrenList);
                // 查询所拥有的权限编码
                permissionPercodes = permissionService.list(permissionQueryWrapper);
            }
            // 获取所有的权限编码
            List<String> percodes = new ArrayList<>();
            for (Permission permission : permissionPercodes) {
                percodes.add(permission.getPermissionPercode());
            }
            // 设置权限
            activeFaculty.setPermissions(percodes);
            /**
             * 参数1传输对象 可以传到doGetAuthorizationInfo 也可以传到subject.getPrincipal()
             * 参数2 加密后的字符串
             * 参数3 盐
             * 参数4 当前类名
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeFaculty, faculty.getFacultyPassword(), credentialsSalt, this.getName());
            return info;
        }
        return null;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取登陆的身份
        ActiveFaculty activeFaculty = (ActiveFaculty) principalCollection.getPrimaryPrincipal();
        // 获取当前登录人
        Faculty faculty = activeFaculty.getFaculty();
        // 获取当前登录人的权限列表
        List<String> permissions = activeFaculty.getPermissions();
        // 判断是否超级管理系
        if (activeFaculty.getRoleName().equals(Constant.SUPER_ADMINISTRATOR)) {
            // 所有权限
            info.addStringPermission("*:*");
        } else {
            // 授权
            if (null != permissions && permissions.size() > 0) {
                info.addStringPermissions(permissions);
            }
        }
        return info;
    }

}
