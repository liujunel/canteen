package com.canteen.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canteen.sys.domain.Faculty;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author:junle
 * @create:2020/2/18-23:10
 */
public interface FacultyMapper extends BaseMapper<Faculty> {
    /**
     * 处理添加时 输入领导不存在的情况
     * @param leaderName
     * @return
     */
    Faculty queryLeaderByLeaderName(@Param("leaderName") String leaderName);
}