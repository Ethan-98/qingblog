package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.Label;
import com.zakary.qingblog.domain.LabelList;
import com.zakary.qingblog.mapper.LabelListMapper;
import com.zakary.qingblog.mapper.LabelMapper;
import com.zakary.qingblog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassNameLabelServiceImpl
 * @Description
 * @Author
 * @Date2020/3/21 12:56
 * @Version V1.0
 **/
@Service
@Transactional
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private LabelListMapper labelListMapper;

    @Override
    public List<Label> getAllLabel(){
        return labelMapper.selectAll();
    }

    @Override
    public void addLabel(List<Integer> labels, int blogId) {
        LabelList labelList=new LabelList();
        for(Integer labelId:labels){
            labelList.setLabelId(labelId);
            labelList.setBlogId(blogId);
            labelListMapper.insert(labelList);
        }
    }

    @Override
    public void insertLabel(Label label) {
        labelMapper.insert(label);

    }

    @Override
    public List<Label> selectLabelByBlogId(LabelList labelList) {
        return labelMapper.selectLabelByBlogId(labelList);
    }
}
