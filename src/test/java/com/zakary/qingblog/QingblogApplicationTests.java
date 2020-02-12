package com.zakary.qingblog;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.service.LoginService;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class QingblogApplicationTests {

    @Autowired
    private LoginService loginService;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;

    private Logger logger;
    @Test
    public void contextLoads() {
    }

    @Test
    public void loginTest1(){
        User user=new User();
        user.setUserMail("xxx@gmail.com");
        user.setUserPassword("12345678");
        loginService.login(user);
    }
    @Test
    public  void register(){
        User user=new User();
        user.setUserName("test1");
        user.setUserPassword("12345678");
        user.setUserState(0);
        user.setUserMail("xxx1@gmail.com");
        user.setUserTel("12345678");
        loginService.register(user);
    }
    @Test
    public void addFile() throws FileNotFoundException {
        File file = new File("D:\\bg.jpg");
        String fileName = "1245678987654334567-10003.jpg";
        ObjectId objectId = gridFsTemplate.store(new FileInputStream(file),fileName,"image");
        String id = objectId.toString(); //这个id是查找文件用的，可以存在mysql里
        System.out.println(id);
    }
    @Test
    public void getFile() throws IOException {
        String id = "5e3f9d5f5f7ca45b15b68324";
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        File file = new File("D:\\temp.jpg");
//        FileUtils.copyInputStreamToFile(gridFsResource.getInputStream(),file);
    }
    @Test
    public void delFile(){
        gridFsTemplate.delete(Query.query(Criteria.where("_id").ne(null)));
    }
}
