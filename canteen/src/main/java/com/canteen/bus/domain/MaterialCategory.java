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
 * 食材类别
 *
 * @author:junle
 * @create:2020/2/22-12:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_material_category")
public class MaterialCategory implements Serializable {
    public static final String COL_IS_SHOW = "is_show";
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 食材类别名称
     */
    @TableField(value = "material_category_name")
    private String materialCategoryName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_MATERIAL_CATEGORY_NAME = "material_category_name";
}