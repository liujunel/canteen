package com.canteen.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canteen.bus.domain.Comment;
import com.canteen.bus.domain.Recipe;
import com.canteen.bus.service.CommentService;
import com.canteen.bus.service.RecipeService;
import com.canteen.bus.vo.CommentVo;
import com.canteen.sys.common.ActiveFaculty;
import com.canteen.sys.common.DataGridView;
import com.canteen.sys.common.ResultObj;
import com.canteen.sys.common.WebUtils;
import com.canteen.sys.domain.Faculty;
import com.canteen.sys.service.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论管理
 *
 * @author:junle
 * @create:2020/2/23-23:25
 */
@RestController
@RequestMapping("comment")
@Slf4j
public class CommentController {

    @Autowired
    @Lazy
    private CommentService commentService;

    @Autowired
    @Lazy
    private RecipeService recipeService;

    @Autowired
    @Lazy
    private FacultyService facultyService;

    /**
     * 全查询
     *
     * @param commentVo
     * @return
     */
    @GetMapping("comments")
    public DataGridView loadAll(CommentVo commentVo) {
        IPage<Comment> page = new Page<>(commentVo.getPage(), commentVo.getLimit());
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        // 1. 在菜谱公布页面对某条菜谱进行查询
        queryWrapper.eq(null != commentVo.getRecipeId(), Comment.COL_RECIPE_ID, commentVo.getRecipeId());
        // 2. 根据时间
        queryWrapper.ge(null != commentVo.getStartTime(), Comment.COL_CREATE_TIME, commentVo.getStartTime());
        queryWrapper.le(null != commentVo.getEndTime(), Comment.COL_CREATE_TIME, commentVo.getEndTime());
        // 3 排序
        queryWrapper.orderByDesc(Comment.COL_CREATE_TIME);
        commentService.page(page, queryWrapper);
        List<Comment> comments = page.getRecords();
        for (Comment comment : comments) {
            // 设置评论人
            Faculty faculty = facultyService.getById(comment.getCommentFacultyNumber());
            comment.setCommentFacultyName(faculty.getFacultyName());
            // 设置菜谱名称
            Recipe recipe = recipeService.getById(comment.getRecipeId());
            comment.setRecipeName(recipe.getRecipeName());
        }
        return new DataGridView(page.getTotal(), comments);
    }

    /**
     * 评论
     *
     * @param commentVo
     * @return
     */
    @PostMapping("comment")
    public ResultObj add(CommentVo commentVo) {
        try {
            // 设置评论的时间
            commentVo.setCreateTime(new Date());
            // 设置评论人
            ActiveFaculty activeFaculty = (ActiveFaculty) WebUtils.getSession().getAttribute("activeFaculty");
            commentVo.setCommentFacultyNumber(activeFaculty.getFaculty().getFacultyNumber());
            commentService.save(commentVo);
            return new ResultObj(200, "评论成功");
        } catch (Exception e) {
            log.info("评论失败" + e.getMessage());
            return new ResultObj(-1, "评论失败");
        }
    }

    /**
     * Restful 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("comment/{id}")
    public ResultObj delete(@PathVariable("id") Integer id) {
        boolean flag = commentService.removeById(id);
        return flag == true ? ResultObj.DELETE_SUCCESS : ResultObj.DELETE_ERROR;
    }

    /**
     * 批量删除
     *
     * @param commentVo
     * @return
     */
    @PostMapping("commentss")
    public ResultObj batchDeleteLoginInfoByIds(CommentVo commentVo) {
        List<Serializable> idList = new ArrayList<>();
        Integer[] ids = commentVo.getIds();
        for (Serializable id : ids) {
            idList.add(id);
        }
        try {
            commentService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            log.info(ResultObj.DELETE_ERROR + e.getMessage());
            return ResultObj.DELETE_ERROR;
        }
    }

}
