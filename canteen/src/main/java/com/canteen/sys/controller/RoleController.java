package com.canteen.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.domain.Role;
import com.canteen.sys.service.RoleService;
import com.canteen.sys.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author:junle
 * @create:2020/2/19-13:49
 */
@RestController
@RequestMapping("role")
@Slf4j
public class RoleController {

    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * 初始化查询角色条件下拉框
     *
     * @return
     */
    @GetMapping("loadAllRole")
    public DataGridView loadAllRole() {
        List<Role> roles = roleService.list();
        return new DataGridView(roles);
    }

    /**
     * 全查询
     *
     * @return
     */
    @GetMapping("roles")
    public DataGridView loadAll(RoleVo roleVo) {
        IPage<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        // 根据角色名查询
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRoleName()), Role.COL_ROLE_NAME, roleVo.getRoleName());
        roleService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     * restful post
     *
     * @param roleVo
     * @return
     */
    @PostMapping("role")
    public ResultObj add(RoleVo roleVo) {
        try {
            // 添加
            roleService.save(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.error(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param roleVo
     * @return
     */
    @PutMapping("role")
    public ResultObj update(RoleVo roleVo) {
        try {
            roleService.updateById(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.error(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 判断角色下是否有教工
     *
     * @param roleId
     * @return
     */
    @GetMapping("queryRoleHasFaculty")
    public ResultObj queryRoleHasFaculty(Integer roleId) {
        List<Integer> facultyNumberLists = roleService.queryRoleHasFaculty(roleId);
        if (null != facultyNumberLists && facultyNumberLists.size() > 0) {
            return new ResultObj(200, "当前角色下含有教工信息,不可删除");
        } else {
            return new ResultObj(-1, "当前角色不含教工信息，可以删除");
        }
    }

    /**
     * 删除
     *
     * @param roleId
     * @return
     */
    @DeleteMapping("role/{roleId}")
    public ResultObj delete(@PathVariable("roleId") Integer roleId) {
        try {
            roleService.removeById(roleId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.error(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 角色分配菜单权限
     * 1. 从中间表中删除权限数据
     * 2. 插入数据
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @PostMapping("assignPermissionByRoleIdAndPermissionId")
    public ResultObj assignPermissionByRoleIdAndPermissionId(Integer roleId, Integer[] permissionIds) {
        try {
            roleService.removeRolePermissionByRoleId(roleId);
            if (null != permissionIds && permissionIds.length > 0) {
                for (Integer permissionId : permissionIds) {
                    roleService.insertRolePermissionByRoleIdAndPermissionId(roleId, permissionId);
                }
            }
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            log.error(ResultObj.DISPATCH_ERROR + e.getMessage());
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
