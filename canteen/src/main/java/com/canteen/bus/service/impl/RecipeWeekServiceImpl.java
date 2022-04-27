package com.canteen.bus.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.domain.RecipeWeek;
import com.canteen.bus.mapper.RecipeWeekMapper;
import com.canteen.bus.service.RecipeWeekService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author:junlejunle
 * @create:2020/3/24-13:14
 */
@Service
@Transactional
public class RecipeWeekServiceImpl extends ServiceImpl<RecipeWeekMapper, RecipeWeek> implements RecipeWeekService{

    @Override
    public void savePre(List<Integer> idList, String week) {
        for (Integer id : idList) {
            this.save(new RecipeWeek(null ,id, week));
        }
    }
}
