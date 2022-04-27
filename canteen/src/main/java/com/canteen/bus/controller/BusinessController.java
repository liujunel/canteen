package com.canteen.bus.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端路由
 *
 * @author:junle
 * @create:2020/2/22-12:21
 */
@Controller
@RequestMapping("bus")
public class BusinessController {

    /**
     * 跳转到食材类别管理
     *
     * @return
     */
    @RequiresPermissions("materialCategory:view")
    @GetMapping("toMaterialCategoryManager")
    public String toMaterialCategoryManager() {
        return "business/material/materialCategoryManager";
    }

    /**
     * 跳转到食材库存管理
     *
     * @return
     */
    @RequiresPermissions("material:view")
    @GetMapping("toMaterialManager")
    public String toMaterialManager() {
        return "business/material/materialManager";
    }

    /**
     * 跳转到食品菜谱类别管理
     *
     * @return
     */
    @RequiresPermissions("recipeCategory:view")
    @GetMapping("toRecipeCategoryManager")
    public String toRecipeCategoryManager() {
        return "business/recipe/recipeCategoryManager";
    }

    /**
     * 跳转到食品菜谱管理
     *
     * @return
     */
    @RequiresPermissions("recipe:view")
    @GetMapping("toRecipeManager")
    public String toRecipeManager() {
        return "business/recipe/recipeManager";
    }

    /**
     * 跳转到食材采购管理
     *
     * @return
     */
    @RequiresPermissions("purchase:view")
    @GetMapping("toPurchaseManager")
    public String toPurchaseManager() {
        return "business/purchase/purchaseManager";
    }

    /**
     * 跳转到入库管理
     *
     * @return
     */
    @RequiresPermissions("putStorage:view")
    @GetMapping("toPutStorageManager")
    public String toPutStorageManager() {
        return "business/storage/putStorageManager";
    }

    /**
     * 跳转到出库管理
     *
     * @return
     */
    @RequiresPermissions("outStorage:view")
    @GetMapping("toOutStorageManager")
    public String toOutStorageManager() {
        return "business/storage/outStorageManager";
    }

    /**
     * 跳转到当日食材库存统计
     *
     * @return
     */
    @RequiresPermissions("todayStatistics:view")
    @GetMapping("toTodayStatistics")
    public String toTodayStatistics() {
        return "business/statistics/todayStatistics";
    }

    /**
     * 跳转到某时间段食材出库统计
     *
     * @return
     */
    @RequiresPermissions("outstorageStatistics:view")
    @GetMapping("toOutstorageStatistics")
    public String weekOutstorageStatistics() {
        return "business/statistics/weekOutstorageStatistics";
    }

    /**
     * 菜谱信息公布管理
     *
     * @return
     */
    @RequiresPermissions("viewRecipeManager:view")
    @GetMapping("toRecipeViewManager")
    public String toRecipeViewManager() {
        return "business/recipe/recipeViewManager";
    }

    /**
     * 菜谱信息公布评论管理
     *
     * @return
     */
    @RequiresPermissions("viewComment:view")
    @GetMapping("toViewCommentManager")
    public String toViewCommentManager() {
        return "business/recipe/viewCommentManager";
    }

    /**
     * 菜谱信息公布评论
     *
     * @return
     */
    @GetMapping("toRecipeView")
    public String toRecipeView() {
        return "business/recipe/recipeView";
    }
}
