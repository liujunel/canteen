package com.canteen.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canteen.bus.domain.Material;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:
 * @create:2020/2/22-14:01
 */
public interface MaterialMapper extends BaseMapper<Material> {
    /**
     * 当日食材库存统计
     * @return
     */
    List<Map<String, Object>> todayStatistics();

    /**
     * 周食材出库统计
     * @return
     */
    List<Map<String, Object>> weekOutstorageStatistics(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}