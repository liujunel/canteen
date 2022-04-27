package com.canteen.bus.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.mapper.RecipeCategoryMapper;
import com.canteen.bus.domain.RecipeCategory;
import com.canteen.bus.service.RecipeCategoryService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author:junlejunle
 * @create:2020/2/22-15:47
 */
@Service
@Transactional
public class RecipeCategoryServiceImpl extends ServiceImpl<RecipeCategoryMapper, RecipeCategory> implements RecipeCategoryService{

    @Override
    public RecipeCategory getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean updateById(RecipeCategory entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(RecipeCategory entity) {
        return super.save(entity);
    }
}
