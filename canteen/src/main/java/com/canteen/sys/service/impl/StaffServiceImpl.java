package com.canteen.sys.service.impl;

import com.canteen.sys.domain.Faculty;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.sys.domain.Staff;
import com.canteen.sys.mapper.StaffMapper;
import com.canteen.sys.service.StaffService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author:junle
 * @create:2020/2/20-19:48
 */
@Service
@Transactional
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService{

    @Override
    public boolean save(Staff entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Staff entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Staff getById(Serializable id) {
        return super.getById(id);
    }

    /**
     * 处理添加时 输入领导不存在的情况
     *
     * @param leaderName
     * @return
     */
    @Override
    public Staff queryLeaderByLeaderName(String leaderName) {
        return this.getBaseMapper().queryLeaderByLeaderName(leaderName);
    }
}
