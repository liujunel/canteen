package com.canteen.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canteen.sys.domain.Staff;
import org.apache.ibatis.annotations.Param;

/**
 * @author:junle
 * @create:2020/2/20-19:48
 */
public interface StaffMapper extends BaseMapper<Staff> {
    /**
     * 处理添加时 输入领导不存在的情况
     *
     * @param leaderName
     * @return
     */
    Staff queryLeaderByLeaderName(@Param("leaderName") String leaderName);
}