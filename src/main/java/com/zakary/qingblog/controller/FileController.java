package com.zakary.qingblog.controller;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.utils.JSONResult;
import org.apache.tomcat.jni.FileInfo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private GridFSBucket gridFSBucket;

    Logger logger = LoggerFactory.getLogger(FileController.class);




    @RequestMapping("/upLoadFile")
    @ResponseBody
    public Map upLoadFile(HttpServletRequest request,@RequestParam(value = "editormd-image-file") MultipartFile file) throws IOException {
        logger.info("【FileController】 fileName={},fileOrginNmae={},fileSize={}", file.getName(), file.getOriginalFilename(), file.getSize());
        logger.info(request.getContextPath());
        ObjectId objectId = gridFsTemplate.store(file.getInputStream(),file.getOriginalFilename(),"image");
//        String fileName = file.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        String newFileName = new Date() + "." + suffix;
//        File localFile = new File("D:\\OneDrive\\Code\\IDEA\\qingblog\\src\\main\\resources\\static\\image\\profileImg", newFileName);
//        file.transferTo(localFile);
//        logger.info(localFile.getAbsolutePath());
//        return null;
        Map<String,Object> map = new HashMap<>();
        map.put("success",1);
        map.put("message ","成功");
        map.put("url","http://localhost:8080/qingblog/img?id="+objectId.toString());
        return map;
    }
    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public void getImg(HttpServletResponse response,String id) throws IOException {
        String userId = "10003";
        Criteria criteria = new Criteria();
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(criteria.andOperator(Criteria.where("_id").is(id),Criteria.where("filename").regex("^.*"+userId+".*$"))));
        if(gridFSFile==null){
            throw new BusinessException("无权访问");
        }
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = gridFsResource.getInputStream();
        int count = 0;
        byte[] buffer = new byte[1024 * 8];
        while ((count = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
            outputStream.flush();
        }
    }
}
