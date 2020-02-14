package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.mapper.BlogMapper;
import com.zakary.qingblog.mapper.UserMapper;
import com.zakary.qingblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassNameBlogServiceImpl
 * @Description
 * @Author
 * @Date2020/2/12 11:46
 * @Version V1.0
 **/


@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int addBlog(Blog blog) {
        blog.setReleaseDate(new Date());
        blog.setViews(0);
        return blogMapper.insert(blog);
    }

    @Override
    public int deleteBlog(int blogId){
        return blogMapper.deleteByPrimaryKey(blogId);
    }

    @Override
    public int updateBlog(Blog blog){
        blog.setReleaseDate(new Date());
        return blogMapper.updateByPrimaryKey(blog);
    }
}
