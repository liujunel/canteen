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
 * 食材
 *
 * @author:junle
 * @create:2020/2/22-14:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_material")
public class Material implements Serializable {

    /**
     * 食材类别名称
     */
    @TableField(exist = false)
    private String materialCategoryName;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "material_name")
    private String materialName;

    /**
     * 数量
     */
    @TableField(value = "material_num")
    private Integer materialNum;

    /**
     * 单位
     */
    @TableField(value = "material_unit")
    private String materialUnit;

    /**
     * 食材类别
     */
    @TableField(value = "material_category_id")
    private Integer materialCategoryId;

    /**
     * 保质期
     */
    @TableField(value = "expiration_date")
    private Date expirationDate;

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
     * 食材图片
     */
    @TableField(value = "material_img_path")
    private String materialImgPath;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_MATERIAL_NAME = "material_name";

    public static final String COL_MATERIAL_NUM = "material_num";

    public static final String COL_MATERIAL_UNIT = "material_unit";

    public static final String COL_MATERIAL_CATEGORY_ID = "material_category_id";

    public static final String COL_EXPIRATION_DATE = "expiration_date";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_NOTE = "note";

    public static final String COL_MATERIAL_IMG_PATH = "material_img_path";
}