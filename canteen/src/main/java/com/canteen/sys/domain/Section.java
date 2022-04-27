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
@TableName(value = "tb_section")
public class Section implements Serializable {
    /**
     * 系号
     */
    @TableId(value = "section_id", type = IdType.AUTO)
    private Integer sectionId;

    /**
     * 系别
     */
    @TableField(value = "section_name")
    private String sectionName;

    /**
     * 系别所在地址
     */
    @TableField(value = "section_address")
    private String sectionAddress;

    /**
     * 系别联系热线
     */
    @TableField(value = "section_iphone")
    private String sectionIphone;

    /**
     * 系别备注
     */
    @TableField(value = "section_note")
    private String sectionNote;

    private static final long serialVersionUID = 1L;

    public static final String COL_SECTION_ID = "section_id";

    public static final String COL_SECTION_NAME = "section_name";

    public static final String COL_SECTION_ADDRESS = "section_address";

    public static final String COL_SECTION_IPHONE = "section_iphone";

    public static final String COL_SECTION_NOTE = "section_note";
}