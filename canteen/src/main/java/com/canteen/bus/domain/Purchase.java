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
 * 食材采购
 *
 * @author:junle
 * @create:2020/2/22-21:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_purchase")
public class Purchase implements Serializable {

    /**
     * 食材名称
     */
    @TableField(exist = false)
    private String materialName;

    /**
     * 食材类别
     */
    @TableField(exist = false)
    private String materialCategory;

    /**
     * 食材类别id
     */
    @TableField(exist = false)
    private Integer materialCategoryId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 采购编号(同一单保持一致)
     */
    @TableField(value = "purchase_no")
    private String purchaseNo;

    /**
     * 食材id
     */
    @TableField(value = "material_id")
    private Integer materialId;

    /**
     * 采购数量
     */
    @TableField(value = "purchase_num")
    private Integer purchaseNum;

    /**
     * 预计采购价格
     */
    @TableField(value = "purchase_price")
    private Double purchasePrice;

    /**
     * 采购单位
     */
    @TableField(value = "purchase_unit")
    private String purchaseUnit;

    /**
     * 供应商
     */
    @TableField(value = "purchase_company")
    private String purchaseCompany;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 采购单创建人
     */
    @TableField(value = "purchase_auditor")
    private String purchaseAuditor;

    /**
     * 状态（0，已放弃，1，待采购，2采购中，3，已入库）
     */
    @TableField(value = "purchase_status")
    private String purchaseStatus;

    /**
     * 备注
     */
    @TableField(value = "note")
    private String note;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PURCHASE_NO = "purchase_no";

    public static final String COL_MATERIAL_ID = "material_id";

    public static final String COL_PURCHASE_NUM = "purchase_num";

    public static final String COL_PURCHASE_PRICE = "purchase_price";

    public static final String COL_PURCHASE_UNIT = "purchase_unit";

    public static final String COL_PURCHASE_COMPANY = "purchase_company";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_PURCHASE_AUDITOR = "purchase_auditor";

    public static final String COL_PURCHASE_STATUS = "purchase_status";

    public static final String COL_NOTE = "note";
}