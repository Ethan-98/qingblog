package com.zakary.qingblog;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zakary.qingblog.controller.FileController;
import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.domain.FavoritesList;
import com.zakary.qingblog.domain.LabelList;
import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.service.BlogService;
import com.zakary.qingblog.service.FavoritesService;
import com.zakary.qingblog.service.LabelService;
import com.zakary.qingblog.service.LoginService;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
import com.zakary.qingblog.utils.JSONResult;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

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
    @Autowired
    private BlogService blogService;
    @Autowired
    private FileController fileController;
    @Autowired
    private FavoritesService favoritesService;
    @Autowired
    private LabelService labelService;

    private Logger logger= LoggerFactory.getLogger(QingblogApplication.class);
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
        File file = new File("D:/OneDrive/Code/IDEA/qingblog/src/main/resources/static/image/profileImg/test.jpg");
        String fileName = String.valueOf(System.currentTimeMillis());
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

    @Test
    public void download(){
        logger.info("准备下载文件....");
        Query query = Query.query(Criteria.where("_id").is("5e428efba367144a1c0276be"));
        // 查询单个文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        if (gridFSFile == null) {
            return;
        }

        String fileName = gridFSFile.getFilename().replace(",", "");
        String contentType = gridFSFile.getMetadata().get("_contentType").toString();

        // 通知浏览器进行文件下载

        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource resource = new GridFsResource(gridFSFile, gridFSDownloadStream);

    }

    @Test
    public void addBlog(){
        int userId=1;
        Blog blog=new Blog();
        blog.setUserId(userId);
        blog.setBlogTitle("rrrr");
        blog.setBlogContent("VGVzdA==".getBytes());
        blogService.addBlog(blog);
        System.out.println(blogService.queryBlogInfo(blog.getBlogId()));
        System.out.println(new String(blogService.queryBlogInfo(blog.getBlogId()).getBlogContent()));
    }

    @Test
    public void delImg(){
        String id="5e43c5a64f0fa35869b29c60";
        GridFSFile gridFSFile=gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if(gridFSFile==null) {
            throw new BusinessException("文件不存在");
        }
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
    }

    @Test
    public void delBlog(){
        Blog blog=new Blog();
        blog.setBlogId(9);
        String imgs[]={"5e43db0db8babd2544f062a9"};
        blogService.deleteBlog(blog.getBlogId());
        for(String img:imgs){
            fileController.delImg(img);
        }
    }

    @Test
    public void updateBlog(){
        Blog blog=new Blog();
        blog.setBlogId(8);
        blog.setBlogTitle("testyyyyy");
        blog.setBlogContent("TTTTTTTTTTTTTTTTTTTTT".getBytes());
        blogService.updateBlog(blog);
        System.out.println(blog.getBlogId());
    }
    @Test
    public void viewBlog(){
        System.out.println(blogService.queryBlogInfo(8));
    }

    @Test
    public void viewBlogList(){
        List<Blog> blogs=blogService.queryBlogListIntro(1);
        System.out.println(blogs.size());
    }
    @Test
    public void testObject(){
    }
    @Test
    public void selectFavoritesList(){
        int userId=10001;
        logger.info(favoritesService.queryFavoritesListByUserId(userId).toString());
    }
    @Test
    public void addFavoritesList(){
        int userId=10001;
        FavoritesList favoritesList=new FavoritesList();
        favoritesList.setFavoritesName("C++");
        favoritesList.setUserId(10001);
        favoritesService.addFavoritesList(favoritesList);
    }
    @Test
    public void selectLabelByBlogId(){
        LabelList labelList=new LabelList();
        labelList.setBlogId(74);
        labelService.selectLabelByBlogId(labelList);
    }
}
