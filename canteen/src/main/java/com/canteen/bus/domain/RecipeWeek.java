package com.canteen.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author:junle
 * @create:2020/3/24-13:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_recipe_week")
public class RecipeWeek implements Serializable {

    /**
     * 菜品类别名
     */
    @TableField(exist = false)
    private String recipeCategoryName;

    /**
     * 菜品名称
     */
    @TableField(exist = false)
    private String recipeName;

    /**
     * 菜品价格
     */
    @TableField(exist = false)
    private Long recipePrice;

    /**
     * 创建时间
     */
    @TableField(exist = false)
    private Date createTime;

    /**
     * 菜品图片
     */
    @TableField(exist = false)
    private String recipeImgPath;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜谱的id
     */
    @TableField(value = "recipe_id")
    private Integer recipeId;

    /**
     * 星期【1 星期一 2 星期二 3 星期三 4 星期四 5 星期五 6 星期六 7 星期日】
     */
    @TableField(value = "view_week")
    private String viewWeek;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_RECIPE_ID = "recipe_id";

    public static final String COL_VIEW_WEEK = "view_week";
    
    public RecipeWeek(Integer id, Integer recipeId, String viewWeek) {
        this.id = id;
        this.recipeId = recipeId;
        this.viewWeek = viewWeek;
    }
}