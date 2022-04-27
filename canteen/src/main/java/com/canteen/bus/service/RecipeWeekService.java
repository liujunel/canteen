package com.canteen.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.canteen.bus.domain.RecipeWeek;

import java.io.Serializable;
import java.util.List;

/**
 * @author:junle
 * @create:2020/3/24-13:14
 */
public interface RecipeWeekService extends IService<RecipeWeek> {
    // 批量发布
    void savePre(List<Integer> idList, String week);
}
