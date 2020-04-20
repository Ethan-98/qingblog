package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.Comment;
import com.zakary.qingblog.domain.CommentPraise;

import java.util.List;
import java.util.Map;

/**
 * @ClassNameCommentService
 * @Description
 * @Author
 * @Date2020/4/4 13:56
 * @Version V1.0
 **/
public interface CommentService {
    void insert(Comment comment);
    List<Map<String,Object>> selectAllByBlogId(int blogId, int pageNo, int pageSize);
    List<Map<String,Object>> selectChildCommentByParentId(int parentCommentId, int pageNo, int pageSize);
    Map<String,Object> selectCommentByCommentId(int commentId);
}
