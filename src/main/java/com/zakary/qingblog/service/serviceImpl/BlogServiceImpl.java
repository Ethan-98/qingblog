package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.domain.Page;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.mapper.BlogMapper;
import com.zakary.qingblog.mapper.UserMapper;
import com.zakary.qingblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @ClassNameBlogServiceImpl
 * @Description
 * @Author
 * @Date2020/2/12 11:46
 * @Version V1.0
 **/


@Service
@Transactional(rollbackFor = Exception.class)
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int addBlog(Blog blog) {
        blog.setReleaseDate(new Date());
        blog.setViews(0);
//        System.out.println("传入时Content："+blog.getBlogContent());
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

    @Override
    public Blog queryBlogInfo(int blogId){
        if(blogMapper.selectByPrimaryKey(blogId)==null){
            throw new BusinessException("博客不存在！");
        }
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public List<Blog> queryBlogListIntro(int userId){
        return blogMapper.selectBlogListByUserId(userId);
    }

    @Override
    public List<Blog> queryAllBlogListIntro(Page page){
        return blogMapper.selectAll(page);
    }

    @Override
    public void addViewsByBlogId(int blogId){
        blogMapper.updateViewsByBlogId(blogId);
    }
}
