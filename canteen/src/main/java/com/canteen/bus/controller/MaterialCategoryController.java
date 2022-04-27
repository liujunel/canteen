package com.canteen.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Material;
import com.canteen.bus.domain.MaterialCategory;
import com.canteen.bus.service.MaterialCategoryService;
import com.canteen.bus.service.MaterialService;
import com.canteen.bus.vo.MaterialCategoryVo;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 食材类别
 *
 * @author:junle
 * @create:2020/2/22-12:40
 */
@RestController
@Slf4j
@RequestMapping("materialCategory")
public class MaterialCategoryController {

    @Autowired
    @Lazy
    private MaterialCategoryService materialCategoryService;

    @Autowired
    @Lazy
    private MaterialService materialService;

    /**
     * 初始化查询食材类别条件下拉框
     *
     * @return
     */
    @GetMapping("loadAllMaterialCategory")
    public DataGridView loadAllSection() {
        List<MaterialCategory> sections = materialCategoryService.list();
        return new DataGridView(sections);
    }

    /**
     * 全查询
     *
     * @param materialCategoryVo
     * @return
     */
    @GetMapping("materialCategorys")
    public DataGridView loadAll(MaterialCategoryVo materialCategoryVo) {
        IPage<MaterialCategory> page = new Page<>(materialCategoryVo.getPage(), materialCategoryVo.getLimit());
        QueryWrapper<MaterialCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(null != materialCategoryVo.getMaterialCategoryName(), MaterialCategory.COL_MATERIAL_CATEGORY_NAME, materialCategoryVo.getMaterialCategoryName());
        materialCategoryService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }


    /**
     * 添加
     * restful post
     *
     * @param materialCategoryVo
     * @return
     */
    @PostMapping("materialCategory")
    public ResultObj add(MaterialCategoryVo materialCategoryVo) {
        try {
            String materialCategoryName = materialCategoryVo.getMaterialCategoryName();
            QueryWrapper<MaterialCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MaterialCategory.COL_MATERIAL_CATEGORY_NAME, materialCategoryName);
            MaterialCategory materialCategory = materialCategoryService.getOne(queryWrapper);
            if (null != materialCategory) {
                return new ResultObj(-1, "添加失败，该食材类别已经存在，请重新输入");
            }
            // 添加
            materialCategoryService.save(materialCategoryVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param materialCategoryVo
     * @return
     */
    @PutMapping("materialCategory")
    public ResultObj update(MaterialCategoryVo materialCategoryVo) {
        try {
            String materialCategoryName = materialCategoryVo.getMaterialCategoryName();
            QueryWrapper<MaterialCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MaterialCategory.COL_MATERIAL_CATEGORY_NAME, materialCategoryName);
            MaterialCategory materialCategory = materialCategoryService.getOne(queryWrapper);
            if (null != materialCategory) {
                return new ResultObj(-1, "更新失败，该食材类别已经存在，请重新输入");
            }
            materialCategoryService.updateById(materialCategoryVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询当前食材类别是否存在食材
     *
     * @param id
     * @return
     */
    @GetMapping("queryHasMaterialById")
    public ResultObj queryHasMaterialById(Integer id) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != id, Material.COL_MATERIAL_CATEGORY_ID, id);
        List<Material> materials = materialService.list(queryWrapper);
        if (null != materials && materials.size() > 0) {
            return new ResultObj(-1, "当前食材类别存在食材, 不允许删除当前食材类别");
        } else {
            return new ResultObj(200, "当前食材类别不存在食材");
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("materialCategory/{id}")
    public ResultObj delete(@PathVariable("id") Integer id) {
        try {
            materialCategoryService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }

}
