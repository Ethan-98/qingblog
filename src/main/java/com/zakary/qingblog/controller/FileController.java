package com.zakary.qingblog.controller;

import com.zakary.qingblog.utils.JSONResult;
import org.apache.tomcat.jni.FileInfo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

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




    @RequestMapping("/upLoadFile")
    @ResponseBody
    public FileInfo upLoadFile(HttpServletRequest request,@RequestParam(value = "editormd-image-file") MultipartFile file) throws IOException {
        logger.info("【FileController】 fileName={},fileOrginNmae={},fileSize={}", file.getName(), file.getOriginalFilename(), file.getSize());
        logger.info(request.getContextPath());
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = new Date() + "." + suffix;

        File localFile = new File("D:\\OneDrive\\Code\\IDEA\\qingblog\\src\\main\\resources\\static\\image\\profileImg", newFileName);
        file.transferTo(localFile);
        logger.info(localFile.getAbsolutePath());
        return new FileInfo(1, "上传成功", request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf(\"/\"))+\"/upload/\"+newFileName);
    }
}
