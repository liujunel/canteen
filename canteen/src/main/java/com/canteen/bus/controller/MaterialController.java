package com.canteen.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Material;
import com.canteen.bus.domain.MaterialCategory;
import com.canteen.bus.service.MaterialCategoryService;
import com.canteen.bus.service.MaterialService;
import com.canteen.bus.vo.MaterialVo;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 食材
 *
 * @author:junle
 * @create:2020/2/22-13:44
 */
@RestController
@RequestMapping("material")
@Slf4j
public class MaterialController {

    @Autowired
    @Lazy
    private MaterialService materialService;

    @Autowired
    @Lazy
    private MaterialCategoryService materialCategoryService;

    /**
     * 二级联动 通过食材类别查询该类别下的所有食材
     *
     * @param categoryId
     * @return
     */
    @GetMapping("loadAllMaterialByCategoryId")
    public DataGridView loadAllMaterialByCategoryId(Integer categoryId) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != categoryId, Material.COL_MATERIAL_CATEGORY_ID, categoryId);
        List<Material> materials = materialService.list(queryWrapper);
        return new DataGridView(materials);
    }

    /**
     * 全查询
     *
     * @param materialVo
     * @return
     */
    @GetMapping("materials")
    public DataGridView loadAll(MaterialVo materialVo) {
        IPage<Material> page = new Page<>(materialVo.getPage(), materialVo.getLimit());
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        // 1.食品类别
        if (null != materialVo.getMaterialCategoryId() && materialVo.getMaterialCategoryId() != 0) {
            queryWrapper.eq(Material.COL_MATERIAL_CATEGORY_ID, materialVo.getMaterialCategoryId());
        }
        // 2.食品名称
        queryWrapper.like(StringUtils.isNotBlank(materialVo.getMaterialName()), Material.COL_MATERIAL_NAME, materialVo.getMaterialName());
        // 3.保质期
        queryWrapper.ge(null != materialVo.getStartTime(), Material.COL_EXPIRATION_DATE, materialVo.getStartTime());
        queryWrapper.le(null != materialVo.getEndTime(), Material.COL_EXPIRATION_DATE, materialVo.getEndTime());
        materialService.page(page, queryWrapper);
        // 处理数据
        List<Material> materials = page.getRecords();
        for (Material material : materials) {
            MaterialCategory category = materialCategoryService.getById(material.getMaterialCategoryId());
            material.setMaterialCategoryName(category.getMaterialCategoryName());
        }
        return new DataGridView(page.getTotal(), materials);
    }

    /**
     * 添加
     * restful post
     *
     * @param materialVo
     * @return
     */
    @PostMapping("material")
    public ResultObj add(MaterialVo materialVo) {
        try {
            String materialName = materialVo.getMaterialName();
            QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Material.COL_MATERIAL_NAME, materialName);
            Material material = materialService.getOne(queryWrapper);
            if (null != material) {
                return new ResultObj(-1, "添加食材失败，该食材已经存在，请重新输入");
            }
            if (materialVo.getMaterialCategoryId() == 0) {
                throw new Exception("请选择食材类别");
            }
            // 保存
            materialService.save(materialVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param materialVo
     * @return
     */
    @PutMapping("material")
    public ResultObj update(MaterialVo materialVo) {
        try {
            String materialName = materialVo.getMaterialName();
            QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Material.COL_MATERIAL_NAME, materialName);
            Material material = materialService.getOne(queryWrapper);
            if (null != material) {
                return new ResultObj(-1, "更新失败，该食材已经存在，请重新输入");
            }
            if (materialVo.getMaterialCategoryId() == 0) {
                throw new Exception("请选择食材类别");
            }
            materialService.updateById(materialVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("material/{id}")
    public ResultObj delete(@PathVariable("id") Integer id) {
        try {
            materialService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 当日食材库存统计
     *
     * @return
     */
    @GetMapping("todayStatistics")
    public DataGridView todayStatistics() {
        List<Map<String, Object>> statistics = materialService.todayStatistics();
        return new DataGridView(statistics);
    }

    /**
     * 周食材出库统计
     *
     * @return
     */
    @GetMapping("weekOutstorageStatistics")
    public DataGridView weekOutstorageStatistics(Date startTime, Date endTime) {
        List<Map<String, Object>> statistics = materialService.weekOutstorageStatistics(startTime, endTime);
        for (Map<String, Object> statistic : statistics) {
            Integer id = (Integer) statistic.get("name");
            Material material = materialService.getById(id);
            statistic.put("name", material.getMaterialName());
        }
        return new DataGridView(statistics);
    }
}
