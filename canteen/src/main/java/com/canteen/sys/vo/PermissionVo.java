package com.canteen.sys.vo;

import com.canteen.sys.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:junle
 * @create:2020/2/15-22:58
 */
@Data
@EqualsAndHashCode(callSuper = false) // 去除继承的鬼东西，记得要加上
public class PermissionVo extends Permission {
    /**
     * 需要给初始值 从第一页开始
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer limit = 10;

}
