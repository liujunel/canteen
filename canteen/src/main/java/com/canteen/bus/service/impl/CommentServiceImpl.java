package com.canteen.bus.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.mapper.CommentMapper;
import com.canteen.bus.domain.Comment;
import com.canteen.bus.service.CommentService;

/**
 * @author:junlejunle
 * @create:2020/2/23-23:22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}


