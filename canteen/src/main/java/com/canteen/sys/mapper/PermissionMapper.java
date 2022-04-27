package com.canteen.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canteen.sys.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:junle
 * @create:2020/2/18-23:52
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 通过pid查询子菜单
     * @param permissionMenu
     * @return
     */
    List<Integer> queryPermissionByPid(@Param("permissionMenu") List<Integer> permissionMenu);
}