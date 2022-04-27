package com.canteen.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.canteen.sys.common.ActiveFaculty;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.common.WebUtils;
import com.canteen.sys.domain.LoginInfo;
import com.canteen.sys.domain.Role;
import com.canteen.sys.service.LoginInfoService;
import com.canteen.sys.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 登录
 *
 * @author:junle
 * @create:2020/2/15-21:53
 */
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController {

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    @Lazy
    private LoginInfoService loginInfoService;

    /**
     * 使用shiro登录
     *
     * @param facultyNumber
     * @param facultyPassword
     * @return
     */
    @PostMapping("login")
    public ResultObj login(String facultyNumber, String facultyPassword, String code) {
        // 获取session中的验证码
        String codeSession = (String) WebUtils.getSession().getAttribute("code");
        // TODO 开启验证码
        if (StringUtils.isNotBlank(code) && code.equals(codeSession)) {

        } else {
            return new ResultObj(-1, "验证码不正确");
        }
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 进行登录
        AuthenticationToken token = new UsernamePasswordToken(facultyNumber, facultyPassword);
        try {
            // 登录
            subject.login(token);
            // 也就是 UserRealm执行SimpleAuthenticationInfo()返回的结果
            ActiveFaculty activeFaculty = (ActiveFaculty) subject.getPrincipal();
            // 根据教工工号查询角色id
            Integer roleId = roleService.queryRoleByFacultyNumber(activeFaculty.getFaculty().getFacultyNumber());
            // 根据角色id查询角色名称
            Role role = roleService.getById(roleId);
            // 将角色设置
            activeFaculty.setRoleName(role.getRoleName());
            // 将该用户放在session中
            WebUtils.getSession().setAttribute("activeFaculty", activeFaculty);
            log.info("用户" + activeFaculty.getFaculty().getFacultyNumber() + "登录成功");
            // 记录登录日志
            LoginInfo loginInfo = new LoginInfo();
            // 记录登录名称
            loginInfo.setLoginName(activeFaculty.getFaculty().getFacultyNumber() + "-" + activeFaculty.getFaculty().getFacultyName() + "-" + role.getRoleName());
            // 记录登录ip
            loginInfo.setLoginIp(WebUtils.getRequest().getRemoteAddr());
            // 记录登录时间
            loginInfo.setLoginTime(new Date());
            // 保存
            loginInfoService.save(loginInfo);

            return ResultObj.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            log.error("用户" + facultyNumber + "登录失败，用户名或密码不正确", e.getMessage());
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }

    /**
     * 生成验证码
     * 需要maven依赖hutool
     * 不懂请看hutool官网
     * 宽度 116
     * 高度 36
     *
     * @param response
     */
    @GetMapping("getCode")
    public void getCode(HttpServletResponse response) throws IOException {
        HttpSession session = WebUtils.getSession();
        // 创建验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 5);
        // 获取验证码
        String code = circleCaptcha.getCode();
        // 生成验证码
        log.info("生成的验证码：" + code + "--Time:" + new Date().getTime());
        // 将code放在session中
        session.setAttribute("code", code);
        // 将code写出去
        ServletOutputStream outputStream = response.getOutputStream();
        circleCaptcha.write(outputStream);
        outputStream.close();
    }

    /**
     * 注销
     */
    @GetMapping("logout")
    public void logout() {
        WebUtils.getSession().invalidate();
    }
}
