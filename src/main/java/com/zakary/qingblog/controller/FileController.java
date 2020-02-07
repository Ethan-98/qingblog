package com.zakary.qingblog.controller;

import com.zakary.qingblog.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassNameFileController
 * @Description
 * @Author
 * @Date2020/2/7 19:45
 * @Version V1.0
 **/

@Controller
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/upLoadFile")
    @ResponseBody
    public JSONResult upLoadFile(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return JSONResult.errorException("文件为空！");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "/profileImg/";
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return JSONResult.ok("success");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return JSONResult.errorException("上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.errorException("上传失败");
        }
    }
}
