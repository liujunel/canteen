package com.canteen.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.domain.Faculty;
import com.canteen.sys.domain.Section;
import com.canteen.sys.service.FacultyService;
import com.canteen.sys.service.SectionService;
import com.canteen.sys.vo.SectionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系别管理
 *
 * @author:junle
 * @create:2020/2/19-9:40
 */
@RestController
@RequestMapping("section")
@Slf4j
public class SectionController {

    @Autowired
    @Lazy
    private SectionService sectionService;

    @Autowired
    @Lazy
    private FacultyService facultyService;

    /**
     * 初始化查询系别条件下拉框
     *
     * @return
     */
    @GetMapping("loadAllSection")
    public DataGridView loadAllSection() {
        List<Section> sections = sectionService.list();
        return new DataGridView(sections);
    }

    /**
     * 全查询
     *
     * @param sectionVo
     * @return
     */
    @GetMapping("sections")
    public DataGridView loadAll(SectionVo sectionVo) {
        IPage<Section> page = new Page<>(sectionVo.getPage(), sectionVo.getLimit());
        QueryWrapper<Section> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(null != sectionVo.getSectionName(), "section_name", sectionVo.getSectionName());
        sectionService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     * restful post
     *
     * @param sectionVo
     * @return
     */
    @PostMapping("section")
    public ResultObj add(SectionVo sectionVo) {
        try {
            String sectionName = sectionVo.getSectionName();
            QueryWrapper<Section> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Section.COL_SECTION_NAME, sectionName);
            Section section = sectionService.getOne(queryWrapper);
            if (null != section) {
                return new ResultObj(-1, "该系别已经存在，请重新输入");
            }
            // 添加
            sectionService.save(sectionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param sectionVo
     * @return
     */
    @PutMapping("section")
    public ResultObj update(SectionVo sectionVo) {
        try {
            String sectionName = sectionVo.getSectionName();
            QueryWrapper<Section> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Section.COL_SECTION_NAME, sectionName);
            Section section = sectionService.getOne(queryWrapper);
            if (null != section && section.getSectionId() != sectionVo.getSectionId()) {
                return new ResultObj(-1, "该系别已经存在，请重新输入");
            }
            sectionService.updateById(sectionVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     *
     * @param sectionId
     * @return
     */
    @DeleteMapping("section/{sectionId}")
    public ResultObj delete(@PathVariable("sectionId") Integer sectionId) {
        try {
            sectionService.removeById(sectionId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询当前系别是否存在教工
     *
     * @param sectionId
     * @return
     */
    @GetMapping("querySectionHasFacultyBySectionId")
    public ResultObj querySectionHasFacultyBySectionId(Integer sectionId) {
        QueryWrapper<Faculty> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != sectionId, "faculty_section_id", sectionId);
        List<Faculty> faculties = facultyService.list(queryWrapper);
        if (null != faculties && faculties.size() > 0) {
            return new ResultObj(-1, "当前系别存在教工, 请先删除教工再删除当前系别");
        } else {
            return new ResultObj(200, "当前系别不存在教工");
        }
    }
}
