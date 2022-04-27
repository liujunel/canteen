package com.canteen.sys.vo;

import com.canteen.sys.domain.Faculty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author:junle
 * @create:2020/2/15-22:58
 */
@Data
@EqualsAndHashCode(callSuper = false) // 去除继承的鬼东西，记得要加上
public class FacultyVo extends Faculty {
    /**
     * 需要给初始值 从第一页开始
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer limit = 10;

    /**
     * 领导
     */
    private String leaderName;

    /**
     * 角色roleId
     */
    private Integer roleId;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
