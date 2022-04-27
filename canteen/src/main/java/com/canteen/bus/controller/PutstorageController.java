package com.canteen.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Material;
import com.canteen.bus.domain.MaterialCategory;
import com.canteen.bus.domain.Putstorage;
import com.canteen.bus.service.MaterialCategoryService;
import com.canteen.bus.service.MaterialService;
import com.canteen.bus.service.PutstorageService;
import com.canteen.bus.vo.PutstorageVo;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 食材入库
 *
 * @author:junle
 * @create:2020/2/23-12:36
 */
@RestController
@RequestMapping("putstorage")
@Slf4j
public class PutstorageController {

    @Autowired
    @Lazy
    private PutstorageService putstorageService;

    @Autowired
    @Lazy
    private MaterialService materialService;

    @Autowired
    @Lazy
    private MaterialCategoryService materialCategoryService;

    /**
     * 全查询
     *
     * @param putstorageVo
     * @return
     */
    @GetMapping("putstorages")
    public DataGridView loadAll(PutstorageVo putstorageVo) {
        IPage<Putstorage> page = new Page<>(putstorageVo.getPage(), putstorageVo.getLimit());
        QueryWrapper<Putstorage> queryWrapper = new QueryWrapper<>();
        // 1. 入库编号
        queryWrapper.like(StringUtils.isNotBlank(putstorageVo.getPutstorageNo()), Putstorage.COL_PUTSTORAGE_NO, putstorageVo.getPutstorageNo());
        // 2. 入库日期
        queryWrapper.ge(null != putstorageVo.getStartTime(), Putstorage.COL_CREATE_TIME, putstorageVo.getStartTime());
        queryWrapper.le(null != putstorageVo.getEndTime(), Putstorage.COL_CREATE_TIME, putstorageVo.getEndTime());
        // 3. 通过采购单ID倒叙
        queryWrapper.orderByDesc(Putstorage.COL_PURCHASE_ID);
        putstorageService.page(page, queryWrapper);
        // 处理数据
        List<Putstorage> putstorages = page.getRecords();
        for (Putstorage putstorage : putstorages) {
            // 获取食材
            Material material = materialService.getById(putstorage.getMaterialId());
            // 获取食材类别
            MaterialCategory category = materialCategoryService.getById(material.getMaterialCategoryId());
            // 设置类别
            putstorage.setMaterialCategoryName(category.getMaterialCategoryName());
            // 设置食材名称
            putstorage.setMaterialName(material.getMaterialName());
            // 设置食材类别id
            putstorage.setMaterialCategoryId(category.getId());
        }
        return new DataGridView(page.getTotal(), putstorages);
    }

    /**
     * 更新
     *
     * @param putstorageVo
     * @return
     */
    @PutMapping("putstorage")
    public ResultObj update(PutstorageVo putstorageVo) {
        try {
            putstorageService.updateById(putstorageVo);
            if (putstorageVo.getPutstorageStatus().equals("2")) {
                return new ResultObj(200, "审核采购单成功，食材已入库");
            } else {
                return ResultObj.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }
}
