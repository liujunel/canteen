package com.canteen.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.canteen.sys.domain.Faculty;
import com.canteen.sys.domain.Staff;

/**
 * @author:junle
 * @create:2020/2/20-19:48
 */
public interface StaffService extends IService<Staff> {

    /**
     * 处理添加时 输入领导不存在的情况
     *
     * @param leaderName
     * @return
     */
    Staff queryLeaderByLeaderName(String leaderName);
}
