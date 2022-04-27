package com.canteen.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_dept")
public class Dept implements Serializable {
    /**
     * 饭堂部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
     * 饭堂部门名
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 饭堂部门地址
     */
    @TableField(value = "dept_address")
    private String deptAddress;

    /**
     * 饭堂部门联系热线
     */
    @TableField(value = "dept_iphone")
    private String deptIphone;

    /**
     * 饭堂部门备注
     */
    @TableField(value = "dept_note")
    private String deptNote;

    private static final long serialVersionUID = 1L;

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_DEPT_NAME = "dept_name";

    public static final String COL_DEPT_ADDRESS = "dept_address";

    public static final String COL_DEPT_IPHONE = "dept_iphone";

    public static final String COL_DEPT_NOTE = "dept_note";
}