package com.canteen.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜谱
 *
 * @author:junle
 * @create:2020/2/22-16:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_recipe")
public class Recipe implements Serializable {

    @TableField(exist = false)
    private String recipeCategoryName;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜品名称
     */
    @TableField(value = "recipe_name")
    private String recipeName;

    /**
     * 菜品价格
     */
    @TableField(value = "recipe_price")
    private Long recipePrice;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    @TableField(value = "note")
    private String note;

    /**
     * 菜品类别
     */
    @TableField(value = "recipe_category_id")
    private Integer recipeCategoryId;

    /**
     * 菜品图片
     */
    @TableField(value = "recipe_img_path")
    private String recipeImgPath;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_RECIPE_NAME = "recipe_name";

    public static final String COL_RECIPE_PRICE = "recipe_price";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_NOTE = "note";

    public static final String COL_RECIPE_CATEGORY_ID = "recipe_category_id";

    public static final String COL_RECIPE_IMG_PATH = "recipe_img_path";
}