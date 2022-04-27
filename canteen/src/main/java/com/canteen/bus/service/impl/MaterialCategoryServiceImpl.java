package com.canteen.bus.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.domain.MaterialCategory;
import com.canteen.bus.mapper.MaterialCategoryMapper;
import com.canteen.bus.service.MaterialCategoryService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:junlejunle
 * @create:2020/2/22-12:08
 */
@Service
@Transactional
public class MaterialCategoryServiceImpl extends ServiceImpl<MaterialCategoryMapper, MaterialCategory> implements MaterialCategoryService {

    @Override
    public MaterialCategory getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(MaterialCategory entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean save(MaterialCategory entity) {
        return super.save(entity);
    }
}

