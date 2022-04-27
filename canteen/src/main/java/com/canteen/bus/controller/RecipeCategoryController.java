package com.canteen.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Recipe;
import com.canteen.bus.domain.RecipeCategory;
import com.canteen.bus.service.RecipeCategoryService;
import com.canteen.bus.service.RecipeService;
import com.canteen.bus.vo.RecipeCategoryVo;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 食品菜谱
 *
 * @author:junle
 * @create:2020/2/22-15:47
 */
@RestController
@RequestMapping("recipeCategory")
@Slf4j
public class RecipeCategoryController {

    @Autowired
    @Lazy
    private RecipeCategoryService recipeCategoryService;

    @Autowired
    @Lazy
    private RecipeService recipeService;

    /**
     * 初始化查询菜谱类别条件下拉框
     *
     * @return
     */
    @GetMapping("loadAllRecipeCategory")
    public DataGridView loadAllSection() {
        List<RecipeCategory> sections = recipeCategoryService.list();
        return new DataGridView(sections);
    }

    /**
     * 全查询
     *
     * @param recipeCategoryVo
     * @return
     */
    @GetMapping("recipeCategorys")
    public DataGridView loadAdd(RecipeCategoryVo recipeCategoryVo) {
        IPage<RecipeCategory> page = new Page<>(recipeCategoryVo.getPage(), recipeCategoryVo.getLimit());
        QueryWrapper<RecipeCategory> queryWrapper = new QueryWrapper<>();
        // 1. 食品菜谱类别名称
        queryWrapper.like(StringUtils.isNotBlank(recipeCategoryVo.getRecipeCategoryName()), RecipeCategory.COL_RECIPE_CATEGORY_NAME, recipeCategoryVo.getRecipeCategoryName());
        recipeCategoryService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     * restful post
     *
     * @param recipeCategoryVo
     * @return
     */
    @PostMapping("recipeCategory")
    public ResultObj add(RecipeCategoryVo recipeCategoryVo) {
        try {
            String recipeCategoryName = recipeCategoryVo.getRecipeCategoryName();
            QueryWrapper<RecipeCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(RecipeCategory.COL_RECIPE_CATEGORY_NAME, recipeCategoryName);
            RecipeCategory recipeCategory = recipeCategoryService.getOne(queryWrapper);
            if (null != recipeCategory) {
                return new ResultObj(-1, "添加类别失败，该菜谱类别已经存在，请重新输入");
            }
            // 添加
            recipeCategoryService.save(recipeCategoryVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param recipeCategoryVo
     * @return
     */
    @PutMapping("recipeCategory")
    public ResultObj update(RecipeCategoryVo recipeCategoryVo) {
        try {
            String recipeCategoryName = recipeCategoryVo.getRecipeCategoryName();
            QueryWrapper<RecipeCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(RecipeCategory.COL_RECIPE_CATEGORY_NAME, recipeCategoryName);
            RecipeCategory recipeCategory = recipeCategoryService.getOne(queryWrapper);
            if (null != recipeCategory) {
                return new ResultObj(-1, "更新菜谱类别失败，该菜谱类别已经存在，请重新输入");
            }
            recipeCategoryService.updateById(recipeCategoryVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询当前食品菜谱类别是否存在食品菜谱
     *
     * @param id
     * @return
     */
    @GetMapping("queryHasRecipeById")
    public ResultObj queryHasRecipeById(Integer id) {
        QueryWrapper<Recipe> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != id, Recipe.COL_RECIPE_CATEGORY_ID, id);
        List<Recipe> recipes = recipeService.list(queryWrapper);
        if (null != recipes && recipes.size() > 0) {
            return new ResultObj(-1, "当前菜谱类别存在菜谱, 不允许删除当前菜谱类别");
        } else {
            return new ResultObj(200, "当前菜谱类别不存在菜谱");
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("recipeCategory/{id}")
    public ResultObj delete(@PathVariable("id") Integer id) {
        try {
            recipeCategoryService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }
}
