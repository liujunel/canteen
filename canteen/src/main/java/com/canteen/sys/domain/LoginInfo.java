package com.canteen.sys.domain;

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
 * @create:2020/2/20-13:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_login_info")
public class LoginInfo implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录教工工号和姓名
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 登录ip
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    private static final long serialVersionUID = 7338835812682766477L;

    public static final String COL_ID = "id";

    public static final String COL_LOGIN_NAME = "login_name";

    public static final String COL_LOGIN_IP = "login_ip";

    public static final String COL_LOGIN_TIME = "login_time";
}