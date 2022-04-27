package com.canteen.bus.vo;

import com.canteen.bus.domain.RecipeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author:
 * @create:2020/2/22-12:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RecipeCategoryVo extends RecipeCategory {
    /**
     * 需要给初始值 从第一页开始
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer limit = 10;
}
