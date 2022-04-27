package com.canteen.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:junle
 * @create:2020/2/18-23:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_permission")
public class Permission implements Serializable {

    /**
     * 是否选中 用于layui 回显数据
     */
    @TableField(exist = false)
    @JsonProperty("LAY_CHECKED")
    private Boolean check = false;

    /**
     * 菜单编号
     */
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 父级菜单编号
     */
    @TableField(value = "permission_pid")
    private Integer permissionPid;

    /**
     * 权限类型【menu】
     */
    @TableField(value = "permission_type")
    private String permissionType;

    /**
     * 菜单名称
     */
    @TableField(value = "permission_name")
    private String permissionName;

    /**
     * 菜单图标
     */
    @TableField(value = "permission_icon")
    private String permissionIcon;

    /**
     * 菜单跳转地址
     */
    @TableField(value = "permission_href")
    private String permissionHref;

    /**
     * 是否展开【0 不展开 1展开】
     */
    @TableField(value = "permission_spread")
    private Integer permissionSpread;

    /**
     * 权限编码【预留】
     */
    @TableField(value = "permission_percode")
    private String permissionPercode;

    /**
     * 排序
     */
    @TableField(value = "permission_order_num")
    private Integer permissionOrderNum;

    private static final long serialVersionUID = 2953111918329405670L;

    public static final String COL_PERMISSION_ID = "permission_id";

    public static final String COL_PERMISSION_PID = "permission_pid";

    public static final String COL_PERMISSION_TYPE = "permission_type";

    public static final String COL_PERMISSION_NAME = "permission_name";

    public static final String COL_PERMISSION_ICON = "permission_icon";

    public static final String COL_PERMISSION_HREF = "permission_href";

    public static final String COL_PERMISSION_SPREAD = "permission_spread";

    public static final String COL_PERMISSION_PERCODE = "permission_percode";

    public static final String COL_PERMISSION_ORDER_NUM = "permission_order_num";
}