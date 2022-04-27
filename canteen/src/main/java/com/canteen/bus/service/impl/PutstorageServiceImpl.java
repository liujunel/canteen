package com.canteen.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.domain.Material;
import com.canteen.bus.domain.Purchase;
import com.canteen.bus.domain.Putstorage;
import com.canteen.bus.mapper.MaterialMapper;
import com.canteen.bus.mapper.PurchaseMapper;
import com.canteen.bus.mapper.PutstorageMapper;
import com.canteen.bus.service.PutstorageService;
import com.canteen.sys.common.ActiveFaculty;
import com.canteen.sys.common.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author:junlejunle
 * @create:2020/2/23-10:16
 */
@Service
@Transactional
public class PutstorageServiceImpl extends ServiceImpl<PutstorageMapper, Putstorage> implements PutstorageService {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public Putstorage getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(Putstorage entity) {
        return super.save(entity);
    }

    /**
     * 更新入库
     * 1. 判断入库单的状态 1 采购中 2 入库
     * 采购中 -- 只修改数据
     * 入库 -- 向库存添加数量
     *
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(Putstorage entity) {
        String status = entity.getPutstorageStatus();
        // 手动设置小计
        entity.setPutstorageMoney(entity.getPutstorageNum() * entity.getPutstoragePrice());
        if (status.equals("2")) {
            // --处理自己
            // 获取当前操作人
            ActiveFaculty activeFaculty = (ActiveFaculty) WebUtils.getSession().getAttribute("activeFaculty");
            // 设置入库操作人
            entity.setPutstorageUser(activeFaculty.getFaculty().getFacultyName());
            // --处理库存
            // 获取食材
            Material material = materialMapper.selectById(entity.getMaterialId());
            // 添加库存  ==  之前的库存 + 入库的数量
            material.setMaterialNum(material.getMaterialNum() + entity.getPutstorageNum());
            // 更新库存
            materialMapper.updateById(material);
            // --处理采购单
            // 获取采购单
            QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>();
            // 1. 通过 no
            queryWrapper.eq(Purchase.COL_PURCHASE_NO, entity.getPutstorageNo());
            // 2. 通过 食材id
            queryWrapper.eq(Purchase.COL_MATERIAL_ID, entity.getMaterialId());
            // 3. 通过 采购单id
            queryWrapper.eq(Purchase.COL_ID, entity.getPurchaseId());
            Purchase purchase = purchaseMapper.selectOne(queryWrapper);
            // 修改采购单的状态 --3 入库
            purchase.setPurchaseStatus("3");
            purchaseMapper.updateById(purchase);
            return super.updateById(entity);
        } else {
            // 状态为采购中--只修改数据
            return super.updateById(entity);
        }
    }
}

