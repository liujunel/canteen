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
 * @create:2020/2/20-19:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_staff")
public class Staff implements Serializable {

    /**
     * 部门姓名
     */
    @TableField(exist = false)
    private String deptName;

    /**
     * 领导姓名
     */
    @TableField(exist = false)
    private String leaderName;

    /**
     * 饭堂员工id
     */
    @TableId(value = "staff_id", type = IdType.AUTO)
    private Integer staffId;

    /**
     * 饭堂员工姓名
     */
    @TableField(value = "staff_name")
    private String staffName;

    /**
     * 饭堂员工地址
     */
    @TableField(value = "staff_address")
    private String staffAddress;

    /**
     * 饭堂员工性别
     */
    @TableField(value = "staff_sex")
    private String staffSex;

    /**
     * 饭堂员工备注
     */
    @TableField(value = "staff_remark")
    private String staffRemark;

    /**
     * 饭堂员工入职时间
     */
    @TableField(value = "staff_hiredate")
    private Date staffHiredate;

    /**
     * 饭堂员工上级领导id[工作流要使用]
     */
    @TableField(value = "staff_mgr")
    private Integer staffMgr;

    /**
     * 饭堂员工职位
     */
    @TableField(value = "staff_position")
    private String staffPosition;

    /**
     * 饭堂员工部门id
     */
    @TableField(value = "staff_dept_id")
    private Integer staffDeptId;

    /**
     * 饭堂员工头像
     */
    @TableField(value = "staff_img_path")
    private String staffImgPath;

    private static final long serialVersionUID = 798729776122403335L;

    public static final String COL_STAFF_ID = "staff_id";

    public static final String COL_STAFF_NAME = "staff_name";

    public static final String COL_STAFF_ADDRESS = "staff_address";

    public static final String COL_STAFF_SEX = "staff_sex";

    public static final String COL_STAFF_REMARK = "staff_remark";

    public static final String COL_STAFF_HIREDATE = "staff_hiredate";

    public static final String COL_STAFF_MGR = "staff_mgr";

    public static final String COL_STAFF_POSITION = "staff_position";

    public static final String COL_STAFF_DEPT_ID = "staff_dept_id";

    public static final String COL_STAFF_IMG_PATH = "staff_img_path";
}