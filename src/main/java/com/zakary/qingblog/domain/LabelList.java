package com.zakary.qingblog.domain;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class LabelList implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column label_list.label_id
     *
     * @mbg.generated
     */
    private Integer labelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column label_list.blog_id
     *
     * @mbg.generated
     */
    private Integer blogId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table label_list
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column label_list.label_id
     *
     * @return the value of label_list.label_id
     *
     * @mbg.generated
     */
    public Integer getLabelId() {
        return labelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column label_list.label_id
     *
     * @param labelId the value for label_list.label_id
     *
     * @mbg.generated
     */
    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column label_list.blog_id
     *
     * @return the value of label_list.blog_id
     *
     * @mbg.generated
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column label_list.blog_id
     *
     * @param blogId the value for label_list.blog_id
     *
     * @mbg.generated
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table label_list
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", labelId=").append(labelId);
        sb.append(", blogId=").append(blogId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}