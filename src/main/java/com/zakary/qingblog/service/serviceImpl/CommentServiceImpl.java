package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.Comment;
import com.zakary.qingblog.domain.CommentPraise;
import com.zakary.qingblog.mapper.CommentMapper;
import com.zakary.qingblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassNameCommentServiceImpl
 * @Description
 * @Author
 * @Date2020/4/4 13:57
 * @Version V1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insert(Comment comment) {
        Date date=new Date();
        comment.setCommentDate(date);
        commentMapper.insert(comment);
    }

    @Override
    public List<Map<String,Object>> selectAllByBlogId(int blogId, int pageNo, int pageSize) {
        return commentMapper.selectAllByBlogId(blogId,pageNo,pageSize);
    }

    @Override
    public List<Map<String, Object>> selectChildCommentByParentId(int parentCommentId, int pageNo, int pageSize) {
        return commentMapper.selectChildCommentByParentId(parentCommentId,pageNo,pageSize);
    }

    @Override
    public Map<String,Object> selectCommentByCommentId(int commentId) {
        return commentMapper.selectByCommentByCommentId(commentId);
    }
}


