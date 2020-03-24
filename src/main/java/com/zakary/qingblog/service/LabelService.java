package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.Label;
import com.zakary.qingblog.domain.LabelList;

import java.util.List;

/**
 * @ClassNameLabelService
 * @Description
 * @Author
 * @Date2020/3/21 12:55
 * @Version V1.0
 **/
public interface LabelService {
    public List<Label> getAllLabel();
    public void addLabel(List<Integer> labels,int blogId);
    public void insertLabel(Label label);
    public List<Label> selectLabelByBlogId(LabelList labelList);
}
