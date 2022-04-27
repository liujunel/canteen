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
 * 菜谱评论
 *
 * @author:junle
 * @create:2020/2/23-23:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_comment")
public class Comment implements Serializable {

    /**
     * 菜谱名称
     */
    @TableField(exist = false)
    private String recipeName;

    /**
     * 评论人
     */
    @TableField(exist = false)
    private String commentFacultyName;

    public static final String COL_ID = "id";
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 菜谱的id
     */
    @TableField(value = "recipe_id")
    private Integer recipeId;

    /**
     * 评论的内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 评论的时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 评论人的id
     */
    @TableField(value = "comment_faculty_number")
    private Integer commentFacultyNumber;

    private static final long serialVersionUID = 1L;

    public static final String COL_COMMENT_ID = "comment_id";

    public static final String COL_RECIPE_ID = "recipe_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_COMMENT_FACULTY_NUMBER = "comment_faculty_number";
}