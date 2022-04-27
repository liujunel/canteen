package com.canteen.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.canteen.sys.domain.Permission;

import java.util.List;

/**
 * @author:junle
 * @create:2020/2/18-23:39
 */
public interface PermissionService extends IService<Permission> {


    /**
     * 通过pid查询子菜单
     * @param permissionMenu
     * @return
     */
    List<Integer> queryPermissionByPid(List<Integer> permissionMenu);
}

