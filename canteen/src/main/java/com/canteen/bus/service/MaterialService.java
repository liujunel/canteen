package com.canteen.bus.service;

import com.canteen.bus.domain.Material;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:junle
 * @create:2020/2/22-13:27
 */
public interface MaterialService extends IService<Material> {

    /**
     * 当日食材库存统计
     * @return
     */
    List<Map<String, Object>> todayStatistics();

    /**
     * 周食材出库统计
     * @return
     */
    List<Map<String, Object>> weekOutstorageStatistics(Date startTime, Date endTime);
}


