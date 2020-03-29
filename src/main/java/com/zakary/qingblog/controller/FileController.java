package com.zakary.qingblog.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.mapper.UserMapper;
import com.zakary.qingblog.service.LoginService;
import com.zakary.qingblog.utils.JSONResult;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.jni.FileInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @ClassNameFileController
 * @Description
 * @Author
 * @Date2020/2/7 19:45
 * @Version V1.0
 **/

@Controller
public class FileController {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private LoginService loginService;

    Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private GridFSBucket gridFSBucket;


    @RequestMapping("/upLoadImg")
    @ResponseBody
    public Map upLoadImg(HttpServletRequest request,@RequestParam(value = "editormd-image-file") MultipartFile file) throws IOException {
        logger.info("【FileController】 fileName={},fileOrginNmae={},fileSize={}", file.getName(), file.getOriginalFilename(), file.getSize());
        logger.info(request.getContextPath());
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        String newFileName = System.currentTimeMillis()+request.getSession().getAttribute("userId").toString() + "." + suffix;

//        File localFile = new File(newFileName);
//        file.transferTo(localFile);
        ObjectId objectId = gridFsTemplate.store(file.getInputStream(),fileName,"image");
        String id = objectId.toString(); //这个id是查找文件用的，可以存在mysql里
        System.out.println(id);
//        logger.info(localFile.getAbsolutePath());
        Map<String,Object> map=new HashMap<>();
        map.put("success",1);
        map.put("message","success");
        map.put("url","/qingblog/img?id="+id);
        return map;
    }

    @RequestMapping(value = "/upLoadImgArray",method=RequestMethod.POST)
    @ResponseBody
    public JSONResult upLoadImgArray(@RequestParam(value = "files") MultipartFile[] formdata,
                                                    @RequestParam(value = "positons") Integer[] positions) throws IOException {
        List<Map<String,Object>> list = new ArrayList<>();
        System.out.println(formdata.length);
        try {
            for (int i = 0; i < formdata.length; i++) {
                String fileName = formdata[i].getOriginalFilename();
                ObjectId objectId = gridFsTemplate.store(formdata[i].getInputStream(), fileName, "image");
                String id = objectId.toString(); //这个id是查找文件用的，可以存在mysql里
                System.out.println(id);
//            List<Object> temp = new ArrayList<>();
//            temp.add(positions[i]);
////            temp.add("/qingblog/img?id="+id);
                Map<String, Object> map = new HashMap<>();
                map.put("success", 1);
                map.put("message", fileName);
                map.put("position", positions[i]);
                map.put("url", "http://localhost:8080/qingblog/img?id=" + id);
                list.add(map);
            }
        }
        catch (Exception e){
            logger.info(e.getMessage());
            throw new BusinessException("存储出错！");
        }
        return JSONResult.ok(list);
    }
    /**
     *@description: 上传头像
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/2/28 16:13
    */
    @RequestMapping(value = "/upLoadProfile",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult upLoadProfile(@RequestParam(value = "file") MultipartFile formdata,HttpServletRequest request)throws IOException{
        String fileName = formdata.getOriginalFilename();
        ObjectId objectId = gridFsTemplate.store(formdata.getInputStream(),fileName,"image");
        String id = objectId.toString(); //这个id是查找文件用的，可以存在mysql里
        System.out.println(id);
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
//        int userId=10001;
        loginService.setProfile(userId,id);
        return JSONResult.ok();
    }

    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public void getImg(HttpServletRequest request,HttpServletResponse response, @NotNull String id) throws IOException{
        Criteria criteria=new Criteria();
//        GridFSFile gridFSFile=gridFsTemplate.findOne(new Query(criteria.andOperator(Criteria.where("_id").is(id),Criteria.where("filename").regex("^.*"+request.getSession().getAttribute("userId").toString()+".*$"))));
        GridFSFile gridFSFile=gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        GridFSDownloadStream gridFSDownloadStream=gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource=new GridFsResource(gridFSFile,gridFSDownloadStream);
        OutputStream outputStream=response.getOutputStream();
        InputStream inputStream=gridFsResource.getInputStream();
        int count=0;
        byte[] buffer=new byte[1024*8];
        while((count=inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,count);
            outputStream.flush();
        }
    }
    /**
     *@description: 删除图片
     *@param:  * @param objectID
     *@return: 删除信息，需要知道文件是否存在所以需要返回值
     *@Author: Zakary
     *@date: 2020/2/12 19:30
    */
    @RequestMapping(value = "/delImg",method = RequestMethod.GET)
    public JSONResult delImg(@RequestParam String id){
        GridFSFile gridFSFile=gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if(gridFSFile==null) {
            return JSONResult.errorMsg("文件不存在");
        }
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
        return JSONResult.ok();
    }

}
