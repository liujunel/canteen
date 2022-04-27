package com.canteen.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.domain.LoginInfo;
import com.canteen.sys.service.LoginInfoService;
import com.canteen.sys.vo.LoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录信息查询管理
 *
 * @author:junle
 * @create:2020/2/20-13:23
 */
@RestController
@RequestMapping("loginInfo")
@Slf4j
public class LoginInfoController {

    @Autowired
    @Lazy
    private LoginInfoService loginInfoService;

    @GetMapping("loginInfos")
    public DataGridView loadAll(LoginInfoVo loginInfoVo) {

        IPage<LoginInfo> page = new Page<>(loginInfoVo.getPage(), loginInfoVo.getLimit());
        QueryWrapper<LoginInfo> queryWrapper = new QueryWrapper<>();
        // 1. 登录名（包括工号、姓名、角色）
        queryWrapper.like(StringUtils.isNotBlank(loginInfoVo.getLoginName()), LoginInfo.COL_LOGIN_NAME, loginInfoVo.getLoginName());
        // 2. ip
        queryWrapper.like(StringUtils.isNotBlank(loginInfoVo.getLoginIp()), LoginInfo.COL_LOGIN_IP, loginInfoVo.getLoginIp());
        // 3. 登录时间
        queryWrapper.ge(null != loginInfoVo.getStartTime(), LoginInfo.COL_LOGIN_TIME, loginInfoVo.getStartTime());
        queryWrapper.le(null != loginInfoVo.getEndTime(), LoginInfo.COL_LOGIN_TIME, loginInfoVo.getEndTime());
        // 4. 排序
        queryWrapper.orderByDesc(LoginInfo.COL_ID);
        loginInfoService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * Restful 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("loginInfo/{id}")
    public ResultObj deleteLogInfoById(@PathVariable("id") Integer id) {
        boolean flag = loginInfoService.removeById(id);
        return flag == true ? ResultObj.DELETE_SUCCESS : ResultObj.DELETE_ERROR;
    }

    /**
     * 批量删除
     *
     * @param loginInfoVo
     * @return
     */
    @PostMapping("loginInfoss")
    public ResultObj batchDeleteLoginInfoByIds(LoginInfoVo loginInfoVo) {
        List<Serializable> idList = new ArrayList<>();
        Integer[] ids = loginInfoVo.getIds();
        for (Serializable id : ids) {
            idList.add(id);
        }
        try {
            loginInfoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.error(ResultObj.DELETE_ERROR + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }
}
