package com.canteen.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json数据实体
 *
 * @author:junle
 * @create:2020/2/15-22:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    /**
     * 状态码
     */
    private Integer code = 0;

    /**
     * 提示信息
     */
    private String msg = "";

    /**
     * 查询的总条数
     */
    private Long count = 0L;

    /**
     * 查询到的数据实体
     */
    private Object data;

    /**
     * 构造方法--仅需要data
     *
     * @param data
     */
    public DataGridView(Object data) {
        this.data = data;
    }

    /**
     * 构造方法--layui需要的格式
     *
     * @param count
     * @param data
     */
    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridView(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
}
