package com.canteen.sys.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理树节点
 *
 * @author:junle
 * @create:2020/2/15-23:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    /**
     * 使用id 和 pid 控制菜单的层级关系
     */
    private Integer id;
    @JsonProperty("parentId")
    private Integer pid;

    /**
     * layui 渲染菜单的属性
     */
    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private List<TreeNode> children = new ArrayList<>();

    /**
     * 分配权限时需要的json字段
     */
    private String checkArr = "0"; // 0代表未选中，1 选中

    /**
     * dtree 复选树 分配权限所需要的构造方法
     *
     * @param id
     * @param pid
     * @param title
     * @param spread
     * @param checkArr
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }

    /**
     * 首页左边导航树的构造器
     * children 需要自己组装
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    /**
     * dtree部门层级结构
     *
     * @param id
     * @param pid
     * @param title
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
    }
}
