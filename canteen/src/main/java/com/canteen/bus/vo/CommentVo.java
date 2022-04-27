package com.canteen.bus.vo;

import com.canteen.bus.domain.Comment;
import com.canteen.bus.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author:
 * @create:2020/2/22-12:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CommentVo extends Comment {
    /**
     * 需要给初始值 从第一页开始
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer limit = 10;

    private Date startTime;

    private Date endTime;

    public Integer[] ids;

}
