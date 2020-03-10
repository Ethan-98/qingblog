package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.domain.Page;

import java.util.List;

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
    public int updateBlog(Blog blog);
    public Blog queryBlogInfo(int blogId);
    public List<Blog> queryBlogListIntro(int userId);
    public List<Blog> queryAllBlogListIntro(Page page);
    public void addViewsByBlogId(int blogId);
}
