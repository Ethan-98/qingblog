package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.Blog;

/**
 * @ClassNameBlogService
 * @Description
 * @Author
 * @Date2020/2/12 11:44
 * @Version V1.0
 **/
public interface BlogService {
    public int addBlog(Blog blog);
    public int deleteBlog(int blogId);
}
