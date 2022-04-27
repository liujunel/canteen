package com.canteen.sys.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.sys.domain.Dept;
import com.canteen.sys.mapper.DeptMapper;
import com.canteen.sys.service.DeptService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author:junle
 * @create:2020/2/20-18:41
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

    @Override
    public boolean updateById(Dept entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(Dept entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Dept getById(Serializable id) {
        return super.getById(id);
    }
}
