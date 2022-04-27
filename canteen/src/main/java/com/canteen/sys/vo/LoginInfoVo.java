package com.canteen.sys.vo;

import com.canteen.sys.domain.LoginInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author:junle
 * @create:2020/2/15-22:58
 */
@Data
@EqualsAndHashCode(callSuper = false) // 去除继承的鬼东西，记得要加上
public class LoginInfoVo extends LoginInfo {
    /**
     * 需要给初始值 从第一页开始
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer limit = 10;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 批量删除的ids
     */
    private Integer[] ids;
}
