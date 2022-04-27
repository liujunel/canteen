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
 * 食材入库
 *
 * @author:junle
 * @create:2020/2/23-12:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_putstorage")
public class Putstorage implements Serializable {


    /**
     * 食材名称
     */
    @TableField(exist = false)
    private String materialName;

    /**
     * 食材类别
     */
    @TableField(exist = false)
    private String materialCategoryName;

    /**
     * 食材类别id
     */
    @TableField(exist = false)
    private Integer materialCategoryId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 入库编号(同一单保持一致)
     */
    @TableField(value = "putstorage_no")
    private String putstorageNo;

    /**
     * 食材id
     */
    @TableField(value = "material_id")
    private Integer materialId;

    /**
     * 数量
     */
    @TableField(value = "putstorage_num")
    private Integer putstorageNum;

    /**
     * 采购的价格
     */
    @TableField(value = "putstorage_price")
    private Double putstoragePrice;

    /**
     * 小计
     */
    @TableField(value = "putstorage_money")
    private Double putstorageMoney;

    /**
     * 单位
     */
    @TableField(value = "putstorage_unit")
    private String putstorageUnit;

    /**
     * 生产单位
     */
    @TableField(value = "purchase_company")
    private String purchaseCompany;

    /**
     * 采购者
     */
    @TableField(value = "purchase_user")
    private String purchaseUser;

    /**
     * 采购单
     */
    @TableField(value = "purchase_id")
    private Integer purchaseId;

    /**
     * 入库接收者
     */
    @TableField(value = "putstorage_user")
    private String putstorageUser;

    /**
     * 入库时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 状态（0，已放弃，1采购中，2已入库）
     */
    @TableField(value = "putstorage_status")
    private String putstorageStatus;

    /**
     * 备注
     */
    @TableField(value = "note")
    private String note;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PUTSTORAGE_NO = "putstorage_no";

    public static final String COL_MATERIAL_ID = "material_id";

    public static final String COL_PUTSTORAGE_NUM = "putstorage_num";

    public static final String COL_PUTSTORAGE_PRICE = "putstorage_price";

    public static final String COL_PUTSTORAGE_MONEY = "putstorage_money";

    public static final String COL_PUTSTORAGE_UNIT = "putstorage_unit";

    public static final String COL_PURCHASE_COMPANY = "purchase_company";

    public static final String COL_PURCHASE_USER = "purchase_user";

    public static final String COL_PURCHASE_ID = "purchase_id";

    public static final String COL_PUTSTORAGE_USER = "putstorage_user";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_PUTSTORAGE_STATUS = "putstorage_status";

    public static final String COL_NOTE = "note";
}