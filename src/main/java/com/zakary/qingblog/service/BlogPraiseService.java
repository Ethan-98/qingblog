package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.domain.BlogPraise;

/**
 * @ClassNameBlogPraiseService
 * @Description
 * @Author
 * @Date2020/3/10 20:23
 * @Version V1.0
 **/
public interface BlogPraiseService {
    public void insertBlogPraise(BlogPraise blogPraise);
    public void delBlogPraise(BlogPraise blogPraise);
    public int selectBlogPraise(BlogPraise blogPraise);
}
