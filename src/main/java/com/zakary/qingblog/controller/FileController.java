package com.zakary.qingblog.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zakary.qingblog.utils.JSONResult;
import org.apache.tomcat.jni.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
        String newFileName = System.currentTimeMillis()+request.getSession().getAttribute("userId").toString() + "." + suffix;

//        File localFile = new File(newFileName);
//        file.transferTo(localFile);
        ObjectId objectId = gridFsTemplate.store(file.getInputStream(),fileName,"image");
        String id = objectId.toString(); //这个id是查找文件用的，可以存在mysql里
        System.out.println(id);
//        logger.info(localFile.getAbsolutePath());
        Map<String,Object> map=new HashMap<>();
        map.put("success",1);
        map.put("message","success");
        map.put("url","http://localhost:8080/qingblog/img?id="+id);
        return map;
    }


    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public void getImg(HttpServletRequest request,HttpServletResponse response, @NotNull String id) throws IOException{
        Criteria criteria=new Criteria();
        GridFSFile gridFSFile=gridFsTemplate.findOne(new Query(criteria.andOperator(Criteria.where("_id").is(id),Criteria.where("filename").regex("^.*"+request.getSession().getAttribute("userId").toString()+".*$"))));
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

}
