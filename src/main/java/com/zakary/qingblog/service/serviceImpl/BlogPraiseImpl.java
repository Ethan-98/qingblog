package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.BlogPraise;
import com.zakary.qingblog.mapper.BlogMapper;
import com.zakary.qingblog.mapper.BlogPraiseMapper;
import com.zakary.qingblog.service.BlogPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassNameBlogPraiseImpl
 * @Description
 * @Author
 * @Date2020/3/10 20:26
 * @Version V1.0
 **/
@Service
@Transactional
public class BlogPraiseImpl implements BlogPraiseService {
    @Autowired
    private BlogPraiseMapper blogPraiseMapper;

    @Override
    public void insertBlogPraise(BlogPraise blogPraise){
        blogPraiseMapper.insert(blogPraise);
    }

    @Override
    public void delBlogPraise(BlogPraise blogPraise){
        blogPraiseMapper.delBlogPraiseByUserIdBlogId(blogPraise);
    }

    @Override
    public int selectBlogPraise(BlogPraise blogPraise){
        return (blogPraiseMapper.selectByUserIdBlogId(blogPraise));
    }
}
