package com.canteen.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.sys.domain.Role;
import com.canteen.sys.mapper.RoleMapper;
import com.canteen.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author:junle
 * @create:2020/2/18-23:53
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    /**
     * 通过教工工号查询角色的id
     * @param facultyNumber
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Integer queryRoleByFacultyNumber(Integer facultyNumber) {
        return this.getBaseMapper().queryRoleByFacultyNumber(facultyNumber);
    }

    /**
     * 教工添加时插入默认角色
     * @param roleId
     * @param facultyNumber
     */
    @Override
    public void insertRoleFacultyByRoleIdAndFacultyNumber(int roleId, Integer facultyNumber) {
        this.getBaseMapper().insertRoleFacultyByRoleIdAndFacultyNumber(roleId, facultyNumber);
    }

    /**
     *  删除角色和教工所关联的数据
     * @param facultyNumber
     */
    @Override
    public void removeRoleFacultyByFacultyNumber(Integer facultyNumber) {
        this.getBaseMapper().removeRoleFacultyByFacultyNumber(facultyNumber);
    }

    /**
     * 判断角色下是否有教工
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> queryRoleHasFaculty(Integer roleId) {
        return this.getBaseMapper().queryRoleHasFaculty(roleId);
    }

    /**
     * 角色分配菜单权限
     * 1. 从中间表中删除权限数据
     * @param roleId
     * @return
     */
    @Override
    public void removeRolePermissionByRoleId(Integer roleId) {
        this.getBaseMapper().removeRolePermissionByRoleId(roleId);
    }

    /**
     * 角色分配菜单权限
     * 2. 插入数据
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Override
    public void insertRolePermissionByRoleIdAndPermissionId(Integer roleId, Integer permissionId) {
        this.getBaseMapper().insertRolePermissionByRoleIdAndPermissionId(roleId, permissionId);
    }

    /**
     * 查询该角色所拥有的权限
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> queryPermissionByRoleId(Integer roleId) {
        return this.getBaseMapper().queryPermissionByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Role entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean save(Role entity) {
        return super.save(entity);
    }
}
