package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.CommentPraise;
import com.zakary.qingblog.service.CommentService;

/**
 * @ClassNameCommentPraiseService
 * @Description
 * @Author
 * @Date2020/4/5 22:15
 * @Version V1.0
 **/
public interface CommentPraiseService {
    void insert(CommentPraise commentPraise);
    void delete(CommentPraise commentPraise);
    int select(CommentPraise commentPraise);
}
