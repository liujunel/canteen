package com.canteen.sys.common;

/**
 * 常量接口
 *
 * @author:junle
 * @create:2020/2/15-21:59
 */
public interface Constant {

    /**
     * 状态码
     */
    public static final Integer OK = 200;
    public static final Integer ERROR = -1;

    /**
     * 菜单权限类型
     */
    public static final String TYPE_MENU = "menu";
    public static final String TYPE_PERMISSION = "permission";

    /**
     * 菜单是否展开
     */
    public static final Integer OPEN_TRUE = 1;
    public static final Integer OPEN_FALSE = 0;

    /**
     * 可用状态
     */
    public static final Object AVAILABLE_TRUE = 1;
    public static final Object AVAILABLE_FALSE = 0;

    /**
     * 角色类型
     */
    public static final String SUPER_ADMINISTRATOR = "超级管理员";

}

