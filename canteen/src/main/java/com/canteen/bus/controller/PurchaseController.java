package com.canteen.bus.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Material;
import com.canteen.bus.domain.MaterialCategory;
import com.canteen.bus.domain.Purchase;
import com.canteen.bus.service.MaterialCategoryService;
import com.canteen.bus.service.MaterialService;
import com.canteen.bus.service.PurchaseService;
import com.canteen.bus.vo.PurchaseVo;
import com.canteen.sys.common.ActiveFaculty;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.common.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 食材采购
 *
 * @author:junle
 * @create:2020/2/22-21:28
 */
@RestController
@RequestMapping("purchase")
@Slf4j
public class PurchaseController {

    @Autowired
    @Lazy
    private PurchaseService purchaseService;

    @Autowired
    @Lazy
    private MaterialService materialService;

    @Autowired
    @Lazy
    private MaterialCategoryService materialCategoryService;

    /**
     * 全查询
     *
     * @param purchaseVo
     * @return
     */
    @GetMapping("purchases")
    public DataGridView loadAll(PurchaseVo purchaseVo) {
        IPage<Purchase> page = new Page<>(purchaseVo.getPage(), purchaseVo.getLimit());
        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>();
        // 1. 根据采购编号
        queryWrapper.eq(StringUtils.isNotBlank(purchaseVo.getPurchaseNo()), Purchase.COL_PURCHASE_NO, purchaseVo.getPurchaseNo());
        // 2. 根据订单创建时间查询
        queryWrapper.ge(null != purchaseVo.getStartTime(), Purchase.COL_CREATE_TIME, purchaseVo.getStartTime());
        queryWrapper.le(null != purchaseVo.getEndTime(), Purchase.COL_CREATE_TIME, purchaseVo.getEndTime());
        // 根据创建时间倒叙排序
        queryWrapper.orderByDesc(Purchase.COL_CREATE_TIME);
        purchaseService.page(page, queryWrapper);
        List<Purchase> purchases = page.getRecords();
        // 处理数据
        for (Purchase purchase : purchases) {
            // 通过id获取食材
            Material material = materialService.getById(purchase.getMaterialId());
            // 设置食材名称
            purchase.setMaterialName(material.getMaterialName());
            // 设置食材类别
            MaterialCategory materialCategory = materialCategoryService.getById(material.getMaterialCategoryId());
            purchase.setMaterialCategory(materialCategory.getMaterialCategoryName());
            // 设置食材类别id
            purchase.setMaterialCategoryId(materialCategory.getId());
        }
        return new DataGridView(page.getTotal(), purchases);
    }

    /**
     * 添加
     *
     * @param purchaseVo
     * @return
     */
    @PostMapping("purchase")
    public Map<String, Object> add(PurchaseVo purchaseVo) {
        Map<String, Object> map = new HashMap<>();
        Integer materialId = purchaseVo.getMaterialId();
        try {
            if (materialId == 0) {
                log.info("添加失败");
                map.put("code", -1);
                map.put("msg", "添加采购失败，请选择需要采购的食材");
                return map;
            }
            if (StringUtils.isBlank(purchaseVo.getPurchaseNo())) {
                String purchaseNo = IdUtil.simpleUUID() + new Date().getTime();
                purchaseVo.setPurchaseNo(purchaseNo);
                map.put("purchaseNo", purchaseNo);
                log.info(purchaseNo);
            } else {
                map.put("purchaseNo", purchaseVo.getPurchaseNo());
            }
            // 获取当前操作人
            ActiveFaculty activeFaculty = (ActiveFaculty) WebUtils.getSession().getAttribute("activeFaculty");
            // 设置操作人
            purchaseVo.setPurchaseAuditor(activeFaculty.getFaculty().getFacultyName());
            purchaseService.save(purchaseVo);
            map.put("code", 200);
            map.put("msg", "添加采购成功");
            return map;
        } catch (Exception e) {
            log.info("添加失败" + e.getMessage());
            map.put("code", -1);
            map.put("msg", "添加采购失败");
            return map;
        }
    }

    /**
     * 更新
     *
     * @param purchaseVo
     * @return
     */
    @PutMapping("purchase")
    public ResultObj update(PurchaseVo purchaseVo) {
        try {
            purchaseService.updateById(purchaseVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("purchase/{id}")
    public ResultObj delete(@PathVariable("id") Integer id) {
        try {
            purchaseService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }
}
