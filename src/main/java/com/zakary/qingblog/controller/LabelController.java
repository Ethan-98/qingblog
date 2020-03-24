package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.Label;
import com.zakary.qingblog.domain.LabelList;
import com.zakary.qingblog.service.LabelService;
import com.zakary.qingblog.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassNameLabelController
 * @Description
 * @Author
 * @Date2020/3/20 22:11
 * @Version V1.0
 **/
@Controller
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "/getAllLabel",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult getAllLabel(){
        return JSONResult.ok(labelService.getAllLabel());
    }
    /**
     *@description: 给文章插入标签
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/3/23 22:28
    */
    @RequestMapping(value = "/addLabel",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addLabel(@RequestBody Map<String,Object> map){
//        System.out.println(map);
        List<Integer> labels=new ArrayList<Integer>();
        labels=(ArrayList<Integer>)map.get("selectedLabel");
//        int[] label=(int[]) map.get("selectedLabel");
        int blogId=(int)map.get("blogId");
        labelService.addLabel(labels,blogId);
        return JSONResult.ok();
    }
    /**
     *@description: 在标签列表加入自定义标签
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/3/23 22:29
    */
    @RequestMapping(value ="/insertLabel",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult insertLabel(@RequestBody Label label){
        labelService.insertLabel(label);
        return JSONResult.ok();
    }

    /**
     *@description: 查询某篇博客的所有标签
     *@param:  * @param blogId
     *@return: List<Label>
     *@Author: Zakary
     *@date: 2020/3/24 14:53
    */
    @RequestMapping(value = "/selectLabelByBlogId",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult selectLabelByBlogId(@RequestBody LabelList labelList){
//        System.out.println(labelList.getBlogId());
//        labelService.selectLabelByBlogId(labelList);
        return JSONResult.ok(labelService.selectLabelByBlogId(labelList));
    }
}
