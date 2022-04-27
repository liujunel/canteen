package com.canteen.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.canteen.sys.common.*;
import com.canteen.sys.domain.Permission;
import com.canteen.sys.service.PermissionService;
import com.canteen.sys.service.RoleService;
import com.canteen.sys.vo.PermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 *
 * @author:junle
 * @create:2020/2/15-22:50
 */
@RestController
@RequestMapping("menu")
@Slf4j
public class MenuController {


    /**
     * permissionService 管理菜单
     */
    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * 加载主页的左侧菜单 动态配置
     *
     * @return
     */
    @GetMapping("loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(PermissionVo permissionVo) {
        List<Permission> menuLists = null;
        // 查询所有的菜单
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        // 设置只能查询菜单
        queryWrapper.eq("permission_type", Constant.TYPE_MENU);
        // 排序
        queryWrapper.orderByAsc("permission_order_num");
        // 判断是否能超级管理员
        ActiveFaculty activeFaculty = (ActiveFaculty) WebUtils.getSession().getAttribute("activeFaculty");
        if (activeFaculty.getRoleName().equals(Constant.SUPER_ADMINISTRATOR)) {
            // 超级管理员直接给权限
            menuLists = permissionService.list(queryWrapper);
        } else {
            /**
             * 根据教工ID + 角色 + 权限 去查询
             */
            // 1. 获取当前登录人的id
            Integer facultyNumber = activeFaculty.getFaculty().getFacultyNumber();
            // 2. 通过当前登录人的id进行查询获取角色id
            Integer roleId = roleService.queryRoleByFacultyNumber(facultyNumber);
            // 3. 通过角色id获取权限
            List<Integer> permissionMenu = roleService.queryPermissionByRoleId(roleId);
            // 4. 查询菜单
            if (null != permissionMenu && permissionMenu.size() > 0) {
                // 通过根菜单id获取子菜单
                List<Integer> childrenList = permissionService.queryPermissionByPid(permissionMenu);
                if (null != childrenList && childrenList.size() > 0) {
                    // 将子菜单的id加入到父菜单的id中
                    permissionMenu.addAll(childrenList);
                }
                // 通过条件查询 权限Id 在这里面的(根菜单)
                queryWrapper.in(Permission.COL_PERMISSION_ID, permissionMenu);
                menuLists = permissionService.list(queryWrapper);
            } else {
                menuLists = new ArrayList<>();
            }
        }
        // 构造菜单的json
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for (Permission p : menuLists) {
            Integer id = p.getPermissionId();
            Integer pid = p.getPermissionPid();
            String title = p.getPermissionName();
            String icon = p.getPermissionIcon();
            String href = p.getPermissionHref();
            Boolean spread = p.getPermissionSpread().equals(Constant.OPEN_TRUE) ? true : false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
        }
        // 构造菜单的层级关系
        List<TreeNode> build = TreeNodeBuilder.build(treeNodes, 1);
        return new DataGridView(build);
    }

    /**
     * 角色分配菜单权限
     * 获取所有根节点的菜单
     *
     * @return
     */
    @GetMapping("loadAllRootMenus")
    public DataGridView loadAllRoot(Integer roleId) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        // 查询所有根节点的菜单
        queryWrapper.eq(true, Permission.COL_PERMISSION_PID, 1);
        List<Permission> permissionList = permissionService.list(queryWrapper);

        // 查询该角色所拥有的权限
        List<Integer> permissionRoleLists = roleService.queryPermissionByRoleId(roleId);

        // 遍历为了回显数据
        for (Permission permission : permissionList) {
            for (Integer permissionRoleId : permissionRoleLists) {
                // 如果根据角色Id查询出来的权限id和所有的权限id相等就选中
                if (permission.getPermissionId() == permissionRoleId) {
                    permission.setCheck(true);
                }
            }
        }

        return new DataGridView(permissionList);
    }


}
