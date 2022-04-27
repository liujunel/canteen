package com.canteen.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.sys.domain.Section;
import com.canteen.sys.mapper.SectionMapper;
import com.canteen.sys.service.SectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 *
 * @author:junle
 * @create:2020/2/19-9:42
 */
@Service
@Transactional
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Section> implements SectionService{

    @Override
    @Transactional(readOnly = true)
    public Section getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean save(Section entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Section entity) {
        return super.updateById(entity);
    }
}
