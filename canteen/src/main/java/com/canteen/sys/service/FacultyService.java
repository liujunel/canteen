package com.canteen.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.canteen.sys.domain.Faculty;

/**
 * @author:junle
 * @create:2020/2/18-23:10
 */
public interface FacultyService extends IService<Faculty> {

    /**
     * 处理添加时 输入领导不存在的情况
     *
     * @param leaderName
     * @return
     */
    Faculty queryLeaderByLeaderName(String leaderName);
}
