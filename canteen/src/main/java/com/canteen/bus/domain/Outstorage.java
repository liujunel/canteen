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
 * 食材出库
 *
 * @author:junle
 * @create:2020/2/23-15:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_outstorage")
public class Outstorage implements Serializable {

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
     * 出库编号(同一单保持一致)
     */
    @TableField(value = "outstorage_no")
    private String outstorageNo;

    /**
     * 食材id
     */
    @TableField(value = "material_id")
    private Integer materialId;

    /**
     * 出库数量
     */
    @TableField(value = "outstorage_num")
    private Integer outstorageNum;

    /**
     * 出库单位
     */
    @TableField(value = "outstorage_unit")
    private String outstorageUnit;

    /**
     * 出库原因
     */
    @TableField(value = "outstorage_reason")
    private String outstorageReason;

    /**
     * 出库负责人
     */
    @TableField(value = "outstorage_user")
    private String outstorageUser;

    /**
     * 收货人
     */
    @TableField(value = "receiver")
    private String receiver;

    /**
     * 出库时间
     */
    @TableField(value = "outstorage_time")
    private Date outstorageTime;

    /**
     * 备注
     */
    @TableField(value = "note")
    private String note;

    /**
     * 出库状态（1 出库中 2 已出库）
     */
    @TableField(value = "outstorage_status")
    private String outstorageStatus;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_OUTSTORAGE_NO = "outstorage_no";

    public static final String COL_MATERIAL_ID = "material_id";

    public static final String COL_OUTSTORAGE_NUM = "outstorage_num";

    public static final String COL_OUTSTORAGE_UNIT = "outstorage_unit";

    public static final String COL_OUTSTORAGE_REASON = "outstorage_reason";

    public static final String COL_OUTSTORAGE_USER = "outstorage_user";

    public static final String COL_RECEIVER = "receiver";

    public static final String COL_OUTSTORAGE_TIME = "outstorage_time";

    public static final String COL_NOTE = "note";

    public static final String COL_OUTSTORAGE_STATUS = "outstorage_status";
}