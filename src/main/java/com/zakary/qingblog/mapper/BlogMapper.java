package com.zakary.qingblog.mapper;

import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.domain.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer blogId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog
     *
     * @mbg.generated
     */
    int insert(Blog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog
     *
     * @mbg.generated
     */
    Blog selectByPrimaryKey(Integer blogId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog
     *
     * @mbg.generated
     */
    List<Blog> selectAll(Page page);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Blog record);

    List<Blog> selectBlogListByUserId(int userId);

    void updateViewsByBlogId(int blogId);
}