package com.canteen.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Recipe;
import com.canteen.bus.domain.RecipeCategory;
import com.canteen.bus.domain.RecipeWeek;
import com.canteen.bus.service.RecipeCategoryService;
import com.canteen.bus.service.RecipeService;
import com.canteen.bus.service.RecipeWeekService;
import com.canteen.bus.vo.RecipeVo;
import com.canteen.bus.vo.RecipeWeekVo;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜谱查看
 *
 * @author:junle
 * @create:2020/3/24-12:05
 */
@RestController
@RequestMapping("recipeView")
@Slf4j
public class RecipeViewController {

    @Autowired
    @Lazy
    private RecipeService recipeService;

    @Autowired
    @Lazy
    private RecipeWeekService recipeWeekService;

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
        IPage<RecipeWeek> page = new Page<>(recipeVo.getPage(), recipeVo.getLimit());
        QueryWrapper<RecipeWeek> queryWrapper = new QueryWrapper<>();
        // 默认星期一
        if (recipeVo.getWeek() != null) {
            queryWrapper.eq(RecipeWeek.COL_VIEW_WEEK, recipeVo.getWeek());
        } else {
            queryWrapper.eq(RecipeWeek.COL_VIEW_WEEK, "1");
        }
        recipeWeekService.page(page, queryWrapper);
        // 数据处理
        List<RecipeWeek> recipesWeek = page.getRecords();
        for (RecipeWeek recipeWeek : recipesWeek) {
            // 获取菜谱的id
            Integer recipeId = recipeWeek.getRecipeId();
            // 获取菜谱对象
            Recipe recipe = recipeService.getById(recipeId);
            // 获取菜谱对象的类别id
            Integer recipeCategoryId = recipe.getRecipeCategoryId();
            // 获取菜谱类别
            RecipeCategory category = recipeCategoryService.getById(recipeCategoryId);
            // 构造数据
            // 菜谱类别名
            recipeWeek.setRecipeCategoryName(category.getRecipeCategoryName());
            // 菜谱名称
            recipeWeek.setRecipeName(recipe.getRecipeName());
            // 菜谱价格
            recipeWeek.setRecipePrice(recipe.getRecipePrice());
            // 菜谱创建时间
            recipeWeek.setCreateTime(recipe.getCreateTime());
            // 菜谱图片
            recipeWeek.setRecipeImgPath(recipe.getRecipeImgPath());
        }
        return new DataGridView(page.getTotal(), recipesWeek);
    }

    /**
     * 添加时间菜谱发布（通过时间）
     *
     * @param recipeWeekVo
     * @return
     */
    @PostMapping("public")
    public ResultObj add(RecipeWeekVo recipeWeekVo) {
        List<Integer> idList = new ArrayList<>();
        Integer[] ids = recipeWeekVo.getIds();
        String week = recipeWeekVo.getViewWeek();
        for (Integer id : ids) {
            idList.add(id);
        }
        try {
            recipeWeekService.savePre(idList, week);
            // 保存
            return new ResultObj(200, "菜谱信息公布成功，请到【菜谱信息公布处查看公布的菜谱信息】");
        } catch (Exception e) {
            log.info("菜谱信息公布失败" + e.getMessage());
            return new ResultObj(-1, "菜谱信息公布失败");
        }
    }

    /**
     * 批量删除
     *
     * @param recipeWeekVo
     * @return
     */
    @PostMapping("recipeViewss")
    public ResultObj batchDeleteByIds(RecipeWeekVo recipeWeekVo) {
        List<Serializable> idList = new ArrayList<>();
        Integer[] ids = recipeWeekVo.getIds();
        for (Serializable id : ids) {
            idList.add(id);
        }
        try {
            recipeWeekService.removeByIds(idList);
            return new ResultObj(200, "批量下架成功");
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR + e.getMessage());
            return new ResultObj(-1, "批量下架失败");
        }
    }
}
