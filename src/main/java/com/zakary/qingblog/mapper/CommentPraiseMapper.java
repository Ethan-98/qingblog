package com.zakary.qingblog.mapper;

import com.zakary.qingblog.domain.CommentPraise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentPraiseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment_praise
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer commentPraiseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment_praise
     *
     * @mbg.generated
     */
    int insert(CommentPraise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment_praise
     *
     * @mbg.generated
     */
    CommentPraise selectByPrimaryKey(Integer commentPraiseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment_praise
     *
     * @mbg.generated
     */
    List<CommentPraise> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment_praise
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CommentPraise record);

    void delete(CommentPraise commentPraise);

    int select(CommentPraise commentPraise);
}