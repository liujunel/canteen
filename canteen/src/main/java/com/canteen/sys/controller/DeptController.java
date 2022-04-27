package com.canteen.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.domain.Dept;
import com.canteen.sys.domain.Staff;
import com.canteen.sys.service.DeptService;
import com.canteen.sys.service.StaffService;
import com.canteen.sys.vo.DeptVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 *
 * @author:junle
 * @create:2020/2/20-19:39
 */
@RestController
@RequestMapping("dept")
@Slf4j
public class DeptController {

    @Autowired
    @Lazy
    private DeptService deptService;

    @Autowired
    @Lazy
    private StaffService staffService;

    /**
     * 初始化查询部门条件下拉框
     *
     * @return
     */
    @GetMapping("loadAllDept")
    public DataGridView loadAllSection() {
        List<Dept> depts = deptService.list();
        return new DataGridView(depts);
    }

    /**
     * 全查询
     *
     * @param deptVo
     * @return
     */
    @GetMapping("depts")
    public DataGridView loadAll(DeptVo deptVo) {
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(null != deptVo.getDeptName(), Dept.COL_DEPT_NAME, deptVo.getDeptName());
        deptService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     * restful post
     *
     * @param deptVo
     * @return
     */
    @PostMapping("dept")
    public ResultObj add(DeptVo deptVo) {
        try {
            String deptName = deptVo.getDeptName();
            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Dept.COL_DEPT_NAME, deptName);
            Dept dept = deptService.getOne(queryWrapper);
            if (null != dept) {
                return new ResultObj(-1, "添加失败，该饭堂部门已经存在，请重新输入");
            }
            // 添加
            deptService.save(deptVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param deptVo
     * @return
     */
    @PutMapping("dept")
    public ResultObj update(DeptVo deptVo) {
        try {
            String deptName = deptVo.getDeptName();
            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Dept.COL_DEPT_NAME, deptName);
            Dept dept = deptService.getOne(queryWrapper);
            if (null != dept && dept.getDeptId() != deptVo.getDeptId()) {
                return new ResultObj(-1, "更新失败，该饭堂部门已经存在，请重新输入");
            }
            deptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     *
     * @param deptId
     * @return
     */
    @DeleteMapping("dept/{deptId}")
    public ResultObj delete(@PathVariable("deptId") Integer deptId) {
        try {
            deptService.removeById(deptId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询当前部门是否存在员工
     *
     * @param deptId
     * @return
     */
    @GetMapping("queryDeptHasStaffByDeptId")
    public ResultObj queryDeptHasStaffByDeptId(Integer deptId) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != deptId, Staff.COL_STAFF_DEPT_ID, deptId);
        List<Staff> staffs = staffService.list(queryWrapper);
        if (null != staffs && staffs.size() > 0) {
            return new ResultObj(-1, "当前饭堂部门存在饭堂员工, 不允许删除该饭堂部门");
        } else {
            return new ResultObj(200, "当前饭堂部门不存在饭堂员工");
        }
    }
}
