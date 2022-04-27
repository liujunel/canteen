package com.canteen.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.domain.Purchase;
import com.canteen.bus.domain.Putstorage;
import com.canteen.bus.mapper.PurchaseMapper;
import com.canteen.bus.mapper.PutstorageMapper;
import com.canteen.bus.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author:junlejunle
 * @create:2020/2/22-21:27
 */
@Service
@Transactional()
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {

    @Autowired
    @Lazy
    private PutstorageMapper putstorageMapper;

    /**
     * 采购单添加--入库单同时也添加
     * @param entity
     * @return
     */
    @Override
    public boolean save(Purchase entity) {
        boolean flag = super.save(entity);
        // 手动添加入库单
        Putstorage putstorage = new Putstorage();
        // 设置no
        putstorage.setPutstorageNo(entity.getPurchaseNo());
        // 设置食材id
        putstorage.setMaterialId(entity.getMaterialId());
        // 设置食材数量
        putstorage.setPutstorageNum(entity.getPurchaseNum());
        // 设置采购的价格
        putstorage.setPutstoragePrice(entity.getPurchasePrice());
        // 小计
        putstorage.setPutstorageMoney(putstorage.getPutstorageNum() * putstorage.getPutstoragePrice());
        // 设置单位
        putstorage.setPutstorageUnit(entity.getPurchaseUnit());
        // 设置供应商
        putstorage.setPurchaseCompany(entity.getPurchaseCompany());
        // 设置采购者
        putstorage.setPurchaseUser(entity.getPurchaseAuditor());
        // 设置采购单
        putstorage.setPurchaseId(entity.getId());
        // 设置状态
        putstorage.setPutstorageStatus(entity.getPurchaseStatus());
        // 设置备注
        putstorage.setNote(entity.getNote());
        // 保存
        int insert = putstorageMapper.insert(putstorage);

        return flag;
    }

    /**
     * 采购单更新
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(Purchase entity) {
        return super.updateById(entity);
    }

    @Override
    public Purchase getById(Serializable id) {
        return super.getById(id);
    }

    /**
     * 删除采购单时，将入库单也同时删除
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        // 找到采购单对象
        Purchase purchase = this.getBaseMapper().selectById(id);
        // 找到入库单对象
        QueryWrapper<Putstorage> queryWrapper = new QueryWrapper<>();
        // 通过条件查找 1.purchaseNo
        queryWrapper.eq(Putstorage.COL_PUTSTORAGE_NO, purchase.getPurchaseNo());
        // 2. 采购单id
        queryWrapper.eq(Putstorage.COL_PURCHASE_ID, purchase.getId());
        // 3. 食材id
        queryWrapper.eq(Putstorage.COL_MATERIAL_ID, purchase.getMaterialId());
        // 找到入库单
        Putstorage putstorage = putstorageMapper.selectOne(queryWrapper);
        // 删除
        putstorageMapper.deleteById(putstorage.getId());
        boolean flag = super.removeById(id);
        return flag;
    }
}
