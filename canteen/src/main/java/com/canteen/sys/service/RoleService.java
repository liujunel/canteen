package com.canteen.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.canteen.sys.domain.Role;

import java.util.List;

/**
 * @author:junlejunle
 * @create:2020/2/18-23:53
 */
public interface RoleService extends IService<Role> {
    /**
     * 通过教工工号查询角色的id
     *
     * @param facultyNumber
     * @return
     */
    Integer queryRoleByFacultyNumber(Integer facultyNumber);

    /**
     * 教工添加时插入默认角色
     * @param roleId
     * @param facultyNumber
     */
    void insertRoleFacultyByRoleIdAndFacultyNumber(int roleId, Integer facultyNumber);

    /**
     *  删除角色和教工所关联的数据
     * @param facultyNumber
     */
    void removeRoleFacultyByFacultyNumber(Integer facultyNumber);

    /**
     * 判断角色下是否有教工
     * @param roleId
     * @return
     */
    List<Integer> queryRoleHasFaculty(Integer roleId);

    /**
     * 角色分配菜单权限
     * 1. 从中间表中删除权限数据
     * @param roleId
     * @return
     */
    void removeRolePermissionByRoleId(Integer roleId);

    /**
     * 角色分配菜单权限
     * 2. 插入数据
     * @param roleId
     * @param permissionIds
     * @return
     */
    void insertRolePermissionByRoleIdAndPermissionId(Integer roleId, Integer permissionId);

    /**
     * 查询该角色所拥有的权限
     * @param roleId
     * @return
     */
    List<Integer> queryPermissionByRoleId(Integer roleId);
}
