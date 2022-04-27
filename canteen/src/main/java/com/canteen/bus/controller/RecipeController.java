package com.canteen.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Recipe;
import com.canteen.bus.service.RecipeCategoryService;
import com.canteen.bus.service.RecipeService;
import com.canteen.bus.vo.RecipeVo;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜谱
 *
 * @author:junle
 * @create:2020/2/22-16:36
 */
@RestController
@RequestMapping("recipe")
@Slf4j
public class RecipeController {

    @Autowired
    @Lazy
    private RecipeService recipeService;

    @Autowired
    @Lazy
    private RecipeCategoryService recipeCategoryService;

    /**
     * 全查询
     *
     * @param recipeVo
     * @return
     */
    @GetMapping("recipes")
    public DataGridView loadAll(RecipeVo recipeVo) {
        IPage<Recipe> page = new Page<>(recipeVo.getPage(), recipeVo.getLimit());
        QueryWrapper<Recipe> queryWrapper = new QueryWrapper<>();
        // 1. 菜谱类别
        if (null != recipeVo.getRecipeCategoryId() && recipeVo.getRecipeCategoryId() != 0) {
            queryWrapper.eq(Recipe.COL_RECIPE_CATEGORY_ID, recipeVo.getRecipeCategoryId());
        }
        // 2. 菜谱名称
        queryWrapper.like(StringUtils.isNotBlank(recipeVo.getRecipeName()), Recipe.COL_RECIPE_NAME, recipeVo.getRecipeName());
        // 3. 创建日期
        queryWrapper.ge(null != recipeVo.getStartTime(), Recipe.COL_CREATE_TIME, recipeVo.getStartTime());
        queryWrapper.le(null != recipeVo.getEndTime(), Recipe.COL_CREATE_TIME, recipeVo.getEndTime());
        recipeService.page(page, queryWrapper);
        // 数据处理
        List<Recipe> recipes = page.getRecords();
        for (Recipe recipe : recipes) {
            recipe.setRecipeCategoryName(recipeCategoryService.getById(recipe.getRecipeCategoryId()).getRecipeCategoryName());
        }
        return new DataGridView(page.getTotal(), recipes);
    }

    /**
     * 添加
     * restful post
     *
     * @param recipeVo
     * @return
     */
    @PostMapping("recipe")
    public ResultObj add(RecipeVo recipeVo) {
        try {
            if (recipeVo.getRecipeCategoryId() == 0) {
                throw new Exception("请选择菜谱类别");
            }
            // 保存
            recipeService.save(recipeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param recipeVo
     * @return
     */
    @PutMapping("recipe")
    public ResultObj update(RecipeVo recipeVo) {
        try {
            if (recipeVo.getRecipeCategoryId() == 0) {
                throw new Exception("请选择菜谱类别");
            }
            recipeService.updateById(recipeVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("recipe/{id}")
    public ResultObj delete(@PathVariable("id") Integer id) {
        try {
            recipeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }

}
