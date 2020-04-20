package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.CommentPraise;
import com.zakary.qingblog.mapper.CommentMapper;
import com.zakary.qingblog.mapper.CommentPraiseMapper;
import com.zakary.qingblog.service.CommentPraiseService;
import com.zakary.qingblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassNameCommentPraiseServiceImpl
 * @Description
 * @Author
 * @Date2020/4/5 22:17
 * @Version V1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentPraiseServiceImpl implements CommentPraiseService {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public void insert(CommentPraise commentPraise) {
        commentPraiseMapper.insert(commentPraise);
    }

    @Override
    public void delete(CommentPraise commentPraise) {
        commentPraiseMapper.delete(commentPraise);
    }

    @Override
    public int select(CommentPraise commentPraise) {
        return commentPraiseMapper.select(commentPraise);
    }
}
