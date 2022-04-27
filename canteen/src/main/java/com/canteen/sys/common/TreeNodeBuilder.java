package com.canteen.sys.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 *
 * @author:junle
 * @create:2020/2/15-23:46
 */
public class TreeNodeBuilder {

    /**
     * 把没有层级关系的集合变成有层级关系
     *
     * @param treeNodes
     * @param topPid
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topPid) {
        // 构造层级
        List<TreeNode> nodes = new ArrayList<>();

        for (TreeNode tn1 : treeNodes) {
            // 将第一级菜单排除出去
            if (tn1.getPid() == topPid) {
                nodes.add(tn1);
            }
            for (TreeNode tn2 : treeNodes) {
                // 将第二级菜单变成第一级菜单的子菜单
                if (tn1.getId() == tn2.getPid()) {
                    tn1.getChildren().add(tn2);
                }
            }
        }
        return nodes;
    }
}
