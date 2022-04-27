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
import com.canteen.sys.vo.StaffVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理
 *
 * @author:junle
 * @create:2020/2/20-20:05
 */
@RestController
@RequestMapping("staff")
@Slf4j
public class StaffController {

    @Autowired
    @Lazy
    private StaffService staffService;

    @Autowired
    @Lazy
    private DeptService deptService;

    @GetMapping("staffs")
    public DataGridView loadAll(StaffVo staffVo) {
        IPage<Staff> page = new Page<>(staffVo.getPage(), staffVo.getLimit());
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        // 1. 部门
        if (null != staffVo.getStaffDeptId() && staffVo.getStaffDeptId() != 0) {
            queryWrapper.eq(Staff.COL_STAFF_DEPT_ID, staffVo.getStaffDeptId());
        }
        // 2. 领导
        if (StringUtils.isNotBlank(staffVo.getLeaderName())) {
            QueryWrapper<Staff> leaderQueryWrapper = new QueryWrapper<>();
            leaderQueryWrapper.like(Staff.COL_STAFF_NAME, staffVo.getLeaderName());
            Staff leader = staffService.getOne(leaderQueryWrapper);
            queryWrapper.eq(Staff.COL_STAFF_MGR, leader.getStaffId());
        }
        // 3. 日期
        queryWrapper.ge(null != staffVo.getStartTime(), Staff.COL_STAFF_HIREDATE, staffVo.getStartTime());
        queryWrapper.le(null != staffVo.getEndTime(), Staff.COL_STAFF_HIREDATE, staffVo.getEndTime());
        // 4. 职位
        queryWrapper.like(StringUtils.isNotBlank(staffVo.getStaffPosition()), Staff.COL_STAFF_POSITION, staffVo.getStaffPosition());
        // 5. 姓名
        queryWrapper.like(StringUtils.isNotBlank(staffVo.getStaffName()), Staff.COL_STAFF_NAME, staffVo.getStaffName());
        staffService.page(page, queryWrapper);
        // 对查询出来的数据进行处理
        List<Staff> staffs = page.getRecords();
        // 对部门、领导进行处理
        for (Staff staff : staffs) {
            Integer deptId = staff.getStaffDeptId();
            Dept dept = deptService.getById(deptId);
            staff.setDeptName(dept.getDeptName());

            Integer leaderId = staff.getStaffMgr();
            if (null != leaderId) {
                Staff leader = null;
                try {
                    leader = staffService.getById(leaderId);
                    staff.setLeaderName(leader.getStaffName());
                } catch (Exception e) {
                    log.info("员工：【" + staff.getStaffName() + "】无领导 " + e.getMessage());
                    staff.setLeaderName("暂时无领导");
                }
            } else {
                staff.setLeaderName("无领导");
            }

        }
        return new DataGridView(page.getTotal(), staffs);
    }

    /**
     * 处理添加时 输入领导不存在的情况
     *
     * @param leaderName
     * @return
     */
    @GetMapping("queryLeaderByLeaderName")
    public ResultObj queryLeaderByLeaderName(String leaderName) {
        Staff leader = staffService.queryLeaderByLeaderName(leaderName);
        if (null != leader) {
            return new ResultObj(200, "该领导存在");
        } else {
            return new ResultObj(-1, "领导【" + leaderName + "】不存在, 请重新输入");
        }
    }

    /**
     * 添加
     * restful post
     *
     * @param staffVo
     * @return
     */
    @PostMapping("staff")
    public ResultObj add(StaffVo staffVo) {
        try {

            // 处理图片（将临时图片更为名字 去掉_temp）
            String imgPath = staffVo.getStaffImgPath();
            if (StringUtils.isNotBlank(imgPath) && imgPath.endsWith("_temp")) {
                imgPath = FileUploadController.renameFile(imgPath);
                staffVo.setStaffImgPath(imgPath);
            }
            if (staffVo.getStaffDeptId() == 0) {
                throw new Exception("请选择部门");
            }
            // 处理领导
            if (StringUtils.isNotBlank(staffVo.getLeaderName())) {
                Staff leader = staffService.queryLeaderByLeaderName(staffVo.getLeaderName());
                staffVo.setStaffMgr(leader.getStaffId());
            }
            // 保存
            staffService.save(staffVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.ADD_ERROR.getMsg() + e.getMessage());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新
     *
     * @param staffVo
     * @return
     */
    @PutMapping("staff")
    public ResultObj update(StaffVo staffVo) {
        try {
            // 处理图片（将临时图片更为名字 去掉_temp）
            String imgPath = staffVo.getStaffImgPath();
            if (!(StringUtils.isNotBlank(imgPath) && imgPath.equals("default.jpg"))) {
                if (imgPath.endsWith("_temp")) {
                    // 改名
                    imgPath = FileUploadController.renameFile(imgPath);
                    staffVo.setStaffImgPath(imgPath);
                    // 删除原来的图片
                    String oldImgPathName = staffService.getById(staffVo.getStaffId()).getStaffImgPath();
                    FileUploadController.removeFileByPath(oldImgPathName);
                }
            }

            // 处理领导
            if (StringUtils.isNotBlank(staffVo.getLeaderName())) {
                Staff leader = staffService.queryLeaderByLeaderName(staffVo.getLeaderName());
                if (null != leader) {
                    staffVo.setStaffMgr(leader.getStaffId());
                }
            }
            staffService.updateById(staffVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.UPDATE_ERROR.getMsg() + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     *
     * @param staffId
     * @return
     */
    @DeleteMapping("staff/{staffId}")
    public ResultObj delete(@PathVariable("staffId") Integer staffId) {
        try {
            // 删除图片
            FileUploadController.removeFileByPath(staffService.getById(staffId).getStaffImgPath());
            staffService.removeById(staffId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR.getMsg() + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }
}
