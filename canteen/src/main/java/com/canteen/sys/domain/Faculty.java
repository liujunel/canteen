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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author:junle
 * @create:2020/2/18-23:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_faculty")
public class Faculty implements Serializable {

    /**
     * 角色名
     */
    @TableField(exist = false)
    private String facultyRoleName;

    /**
     * 领导
     */
    @TableField(exist = false)
    private String facultyLeaderName;

    /**
     * 系别
     */
    @TableField(exist = false)
    private String facultySectionName;

    /**
     * 教工工号
     */
    @TableId(value = "faculty_number", type = IdType.AUTO)
    private Integer facultyNumber;

    /**
     * 教工姓名
     */
    @TableField(value = "faculty_name")
    private String facultyName;

    /**
     * 教工地址
     */
    @TableField(value = "faculty_address")
    private String facultyAddress;

    /**
     * 教工性别
     */
    @TableField(value = "faculty_sex")
    private String facultySex;

    /**
     * 教工密码
     */
    @TableField(value = "faculty_password")
    private String facultyPassword;

    /**
     * 教工入职时间
     */
    @TableField(value = "faculty_hiredate")
    private Date facultyHiredate;

    /**
     * 教工直系领导
     */
    @TableField(value = "faculty_mgr")
    private Integer facultyMgr;

    /**
     * 教工系别
     */
    @TableField(value = "faculty_section_id")
    private Integer facultySectionId;

    /**
     * 教工头像
     */
    @TableField(value = "faculty_img_path")
    private String facultyImgPath;

    private static final long serialVersionUID = -3553949013617493391L;

    public static final String COL_FACULTY_NUMBER = "faculty_number";

    public static final String COL_FACULTY_NAME = "faculty_name";

    public static final String COL_FACULTY_ADDRESS = "faculty_address";

    public static final String COL_FACULTY_SEX = "faculty_sex";

    public static final String COL_FACULTY_PASSWORD = "faculty_password";

    public static final String COL_FACULTY_HIREDATE = "faculty_hiredate";

    public static final String COL_FACULTY_MGR = "faculty_mgr";

    public static final String COL_FACULTY_SECTION_ID = "faculty_section_id";

    public static final String COL_FACULTY_IMG_PATH = "faculty_img_path";
}