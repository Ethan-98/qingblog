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
    public void addBlog(int userId, String title, String text) {
        Blog blog=new Blog();
        blog.setUserId(userId);
        blog.setBlogTitle(title);
        blog.setBlogContent(text.getBytes());
        blog.setReleaseDate(new Date());
        blog.setViews(0);
        blogMapper.insert(blog);
    }
}
