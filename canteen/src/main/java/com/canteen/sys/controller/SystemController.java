package com.canteen.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统路由
 *
 * @author:junle
 * @create:2020/2/15-21:08
 */
@Controller
@RequestMapping("sys")
public class SystemController {

    /**
     * 跳转到系统的登录页
     *
     * @return
     */
    @GetMapping("toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 跳转到系统的首页
     *
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "system/index/index";
    }

    /**
     * 跳转到系统的主页main
     *
     * @return
     */
    @GetMapping("toDeskManager")
    public String toDeskManager() {
        return "system/index/main";
    }

    /**
     * 跳转到图标管理页
     *
     * @return
     */
    @RequiresPermissions("icon:view")
    @GetMapping("toIconManager")
    public String toIconManager() {
        return "system/icon/icon";
    }

    /**
     * 跳转到系别管理页
     *
     * @return
     */
    @RequiresPermissions("section:view")
    @GetMapping("toSectionManager")
    public String toSectionManager() {
        return "system/section/sectionManager";
    }

    /**
     * 跳转到教工管理页
     *
     * @return
     */
    @RequiresPermissions("faculty:view")
    @GetMapping("toFacultyManager")
    public String toFacultyManager() {
        return "system/faculty/facultyManager";
    }

    /**
     * 跳转到角色管理页
     *
     * @return
     */
    @RequiresPermissions("role:view")
    @GetMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /**
     * 跳转到日志管理页
     *
     * @return
     */
    @RequiresPermissions("loginInfo:view")
    @GetMapping("toLoginInfoManager")
    public String toLoginInfoManager() {
        return "system/loginInfo/loginInfoManager";
    }

    /**
     * 跳转到饭堂员工部门管理页
     *
     * @return
     */
    @RequiresPermissions("dept:view")
    @GetMapping("toDeptManager")
    public String toDeptManager() {
        return "system/dept/deptManager";
    }

    /**
     * 跳转到饭堂员工管理页
     *
     * @return
     */
    @RequiresPermissions("staff:view")
    @GetMapping("toStaffManager")
    public String toStaffManager() {
        return "system/staff/staffManager";
    }

}
