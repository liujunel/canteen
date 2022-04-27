package com.canteen.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜谱类别
 *
 * @author:junle
 * @create:2020/2/22-15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_recipe_category")
public class RecipeCategory implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜谱类别名称
     */
    @TableField(value = "recipe_category_name")
    private String recipeCategoryName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_RECIPE_CATEGORY_NAME = "recipe_category_name";
}