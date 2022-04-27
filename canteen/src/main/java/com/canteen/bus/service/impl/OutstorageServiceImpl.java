package com.canteen.bus.service.impl;

import com.canteen.bus.domain.Material;
import com.canteen.bus.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.domain.Outstorage;
import com.canteen.bus.mapper.OutstorageMapper;
import com.canteen.bus.service.OutstorageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:junlejunle
 * @create:2020/2/23-15:09
 */
@Service
@Transactional
public class OutstorageServiceImpl extends ServiceImpl<OutstorageMapper, Outstorage> implements OutstorageService {

    @Autowired
    @Lazy
    private MaterialMapper materialMapper;

    @Override
    public Outstorage getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(Outstorage entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Outstorage entity) {
        String status = entity.getOutstorageStatus();
        if (status.equals("2")) {
            // 出库
            // 获取食材
            Material material = materialMapper.selectById(entity.getMaterialId());
            // 获取食材库存
            Integer materialNum = material.getMaterialNum();
            // 获取出库数量
            Integer outstorageNum = entity.getOutstorageNum();
            if (materialNum < outstorageNum) {
                return false;
            }
            // 修改食材库存
            material.setMaterialNum(materialNum - outstorageNum);
            // 保存食材库存
            materialMapper.updateById(material);
        }
        return super.updateById(entity);
    }
}

