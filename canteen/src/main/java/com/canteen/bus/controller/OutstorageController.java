package com.canteen.bus.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Material;
import com.canteen.bus.domain.MaterialCategory;
import com.canteen.bus.domain.Outstorage;
import com.canteen.bus.service.MaterialCategoryService;
import com.canteen.bus.service.MaterialService;
import com.canteen.bus.service.OutstorageService;
import com.canteen.bus.vo.OutstorageVo;
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
 * 食材出库
 *
 * @author:junle
 * @create:2020/2/23-15:10
 */

@RestController
@RequestMapping("outstorage")
@Slf4j
public class OutstorageController {

    @Autowired
    @Lazy
    private OutstorageService outstorageService;

    @Autowired
    @Lazy
    private MaterialService materialService;

    @Autowired
    @Lazy
    private MaterialCategoryService materialCategoryService;

    @GetMapping("outstorages")
    public DataGridView loadAll(OutstorageVo outstorageVo) {
        IPage<Outstorage> page = new Page<>(outstorageVo.getPage(), outstorageVo.getLimit());
        QueryWrapper<Outstorage> queryWrapper = new QueryWrapper<>();
        // 条件
        // 1. 出库编号
        queryWrapper.like(StringUtils.isNotBlank(outstorageVo.getOutstorageNo()), Outstorage.COL_OUTSTORAGE_NO, outstorageVo.getOutstorageNo());
        // 2. 出库日期
        queryWrapper.ge(null != outstorageVo.getStartTime(), Outstorage.COL_OUTSTORAGE_TIME, outstorageVo.getStartTime());
        queryWrapper.le(null != outstorageVo.getEndTime(), Outstorage.COL_OUTSTORAGE_TIME, outstorageVo.getEndTime());
        // 3. 排序
        queryWrapper.orderByDesc(OutstorageVo.COL_ID);
        outstorageService.page(page, queryWrapper);
        // 处理数据
        List<Outstorage> outstorages = page.getRecords();
        for (Outstorage outstorage : outstorages) {
            // 获取食材
            Material material = materialService.getById(outstorage.getMaterialId());
            // 获取食材类别
            MaterialCategory category = materialCategoryService.getById(material.getMaterialCategoryId());
            // 设置食材名称
            outstorage.setMaterialName(material.getMaterialName());
            // 设置食材类别id
            outstorage.setMaterialCategoryId(category.getId());
            // 设置食材类别名称
            outstorage.setMaterialCategoryName(category.getMaterialCategoryName());
        }
        return new DataGridView(page.getTotal(), outstorages);
    }

    /**
     * 添加
     *
     * @param outstorageVo
     * @return
     */
    @PostMapping("outstorage")
    public Map<String, Object> add(OutstorageVo outstorageVo) {
        Map<String, Object> map = new HashMap<>();
        Integer materialId = outstorageVo.getMaterialId();
        try {
            if (materialId == 0) {
                log.info("出库失败");
                map.put("code", -1);
                map.put("msg", "食材出库失败，请选择出库的食材");
                return map;
            }
            if (StringUtils.isBlank(outstorageVo.getOutstorageNo())) {
                String outstorageNo = IdUtil.simpleUUID() + new Date().getTime();
                outstorageVo.setOutstorageNo(outstorageNo);
                map.put("outstorageNo", outstorageNo);
                log.info(outstorageNo);
            } else {
                map.put("outstorageNo", outstorageVo.getOutstorageNo());
            }
            // 获取当前操作人
            ActiveFaculty activeFaculty = (ActiveFaculty) WebUtils.getSession().getAttribute("activeFaculty");
            // 设置操作人
            outstorageVo.setOutstorageUser(activeFaculty.getFaculty().getFacultyName());
            outstorageService.save(outstorageVo);
            map.put("code", 200);
            map.put("msg", "食材出库中");
            return map;
        } catch (Exception e) {
            log.info("添加失败" + e.getMessage());
            map.put("code", -1);
            map.put("msg", "食材出库失败");
            return map;
        }
    }

    /**
     * 更新
     *
     * @param outstorageVo
     * @return
     */
    @PutMapping("outstorage")
    public ResultObj update(OutstorageVo outstorageVo) {
        try {
            String status = outstorageVo.getOutstorageStatus();
            boolean flag = outstorageService.updateById(outstorageVo);
            if (flag && status.equals("2")) {
                return new ResultObj(200, "食材出库成功");
            } else if (flag && status.equals("1")) {
                return ResultObj.UPDATE_SUCCESS;
            } else {
                return new ResultObj(-1, "食材出库失败，可能库存数量不足，无法出库");
            }
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
    @DeleteMapping("outstorage/{id}")
    public ResultObj delete(@PathVariable("id") Integer id) {
        try {
            outstorageService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }
}
