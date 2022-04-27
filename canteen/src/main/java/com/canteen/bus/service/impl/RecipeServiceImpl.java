package com.canteen.bus.service.impl;

import com.canteen.sys.controller.FileUploadController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.mapper.RecipeMapper;
import com.canteen.bus.domain.Recipe;
import com.canteen.bus.service.RecipeService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author:junlejunle
 * @create:2020/2/22-16:21
 */
@Service
@Transactional
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe> implements RecipeService{

    @Override
    @Transactional(readOnly = true)
    public Recipe getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        FileUploadController.removeFileByPath(this.getBaseMapper().selectById(id).getRecipeImgPath());
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Recipe entity) {
        // 处理图片（将临时图片更为名字 去掉_temp）
        String imgPath = entity.getRecipeImgPath();
        if (!(StringUtils.isNotBlank(imgPath) && imgPath.equals("default.jpg"))) {
            if (imgPath.endsWith("_temp")) {
                // 改名
                imgPath = FileUploadController.renameFile(imgPath);
                entity.setRecipeImgPath(imgPath);
                // 删除原来的图片
                String oldImgPathName = this.getBaseMapper().selectById(entity.getId()).getRecipeImgPath();
                FileUploadController.removeFileByPath(oldImgPathName);
            }
        }
        return super.updateById(entity);
    }

    @Override
    public boolean save(Recipe entity) {
        // 处理图片（将临时图片更为名字 去掉_temp）
        String imgPath = entity.getRecipeImgPath();
        if (StringUtils.isNotBlank(imgPath) && imgPath.endsWith("_temp")) {
            imgPath = FileUploadController.renameFile(imgPath);
            entity.setRecipeImgPath(imgPath);
        }
        return super.save(entity);
    }

}
