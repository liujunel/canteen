package com.canteen.sys.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.sys.mapper.FacultyMapper;
import com.canteen.sys.domain.Faculty;
import com.canteen.sys.service.FacultyService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author:junle
 * @create:2020/2/18-23:10
 */
@Service
@Transactional
public class FacultyServiceImpl extends ServiceImpl<FacultyMapper, Faculty> implements FacultyService{

    @Override
    @Transactional(readOnly = true)
    public Faculty getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Faculty entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean save(Faculty entity) {
        return super.save(entity);
    }

    /**
     * 处理添加时 输入领导不存在的情况
     * @param leaderName
     * @return
     */
    @Override
    public Faculty queryLeaderByLeaderName(String leaderName) {
        return this.getBaseMapper().queryLeaderByLeaderName(leaderName);
    }
}
