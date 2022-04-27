package com.canteen.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canteen.sys.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author:junle
 * @create:2020/2/18-23:53
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过教工工号查询角色的id
     * @param facultyNumber
     * @return
     */
    Integer queryRoleByFacultyNumber(@Param("facultyNumber") Integer facultyNumber);

    /**
     * 教工添加时插入默认角色
     * @param roleId
     * @param facultyNumber
     */
    void insertRoleFacultyByRoleIdAndFacultyNumber(@Param("roleId") int roleId,@Param("facultyNumber") Integer facultyNumber);

    /**
     *  删除角色和教工所关联的数据
     * @param facultyNumber
     */
    void removeRoleFacultyByFacultyNumber(@Param("facultyNumber") Integer facultyNumber);

    /**
     * 判断角色下是否有教工
     * @param roleId
     * @return
     */
    List<Integer> queryRoleHasFaculty(@Param("roleId") Integer roleId);

    /**
     * 角色分配菜单权限
     * 1. 从中间表中删除权限数据
     * @param roleId
     * @return
     */
    void removeRolePermissionByRoleId(@Param("roleId") Integer roleId);

    /**
     * 角色分配菜单权限
     * 2. 插入数据
     * @param roleId
     * @param permissionIds
     * @return
     */
    void insertRolePermissionByRoleIdAndPermissionId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 查询该角色所拥有的权限
     * @param roleId
     * @return
     */
    List<Integer> queryPermissionByRoleId(@Param("roleId") Integer roleId);
}