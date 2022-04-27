package com.canteen.sys.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.sys.mapper.PermissionMapper;
import com.canteen.sys.domain.Permission;
import com.canteen.sys.service.PermissionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:junle
 * @create:2020/2/18-23:39
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {


    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Permission entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Permission getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean save(Permission entity) {
        return super.save(entity);
    }

    /**
     * 通过pid查询子菜单
     * @param permissionMenu
     * @return
     */
    @Override
    public List<Integer> queryPermissionByPid(List<Integer> permissionMenu) {
        return this.getBaseMapper().queryPermissionByPid(permissionMenu);
    }
}

