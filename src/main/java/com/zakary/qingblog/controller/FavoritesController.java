package com.zakary.qingblog.controller;

import com.sun.xml.internal.bind.v2.TODO;
import com.zakary.qingblog.domain.Favorites;
import com.zakary.qingblog.domain.FavoritesList;
import com.zakary.qingblog.service.FavoritesService;
import com.zakary.qingblog.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassNameFavoritesController
 * @Description
 * @Author
 * @Date2020/3/7 22:29
 * @Version V1.0
 **/
@Controller
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    /**
     *@description: 查看此用户所有收藏文件夹
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/3/7 23:03
    */
    @RequestMapping(value = "/getFavoritesList",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult getFavoritesList(HttpServletRequest request){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        return JSONResult.ok(favoritesService.queryFavoritesListByUserId(userId));
    }
    //TODO:这里返回值只会返回id为1，sql返回值有误
    /**
     *@description: 新建收藏文件夹
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/3/7 23:03
    */
    @RequestMapping(value = "/addFavoritesList",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addFavoritesList(HttpServletRequest request, @RequestBody FavoritesList favoritesList){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        favoritesList.setUserId(userId);
        return JSONResult.ok(favoritesService.addFavoritesList(favoritesList));
    }
    /**
     *@description: 删除收藏文件夹,同时删除该文件夹下所有收藏
     *@param:  * @param favoritesId
     *@return:
     *@Author: Zakary
     *@date: 2020/3/8 11:50
    */
    @RequestMapping(value = "/delFavoritesList",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult delFavoritesList(@RequestBody FavoritesList favoritesList){
        favoritesService.delFavoritesList(favoritesList);
        return JSONResult.ok();
    }
    /**
     *@description: 添加一个收藏
     *@param:  * @param favoritesId,blogId,blogTitle
     *@return: null
     *@Author: Zakary
     *@date: 2020/3/9 22:26
    */
    @RequestMapping(value = "/addFavorites",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addFavorites(HttpServletRequest request,@RequestBody Favorites favorites){
//        System.out.println(favorites);
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        favoritesService.addFavorites(userId,favorites);
        return JSONResult.ok();
    }

    /**
     *@description: 查询是否已经收藏
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/3/9 23:22
    */
    @RequestMapping(value = "/selectStarStatus",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult selectStarStatus(HttpServletRequest request,@RequestBody Favorites favorites){
//        System.out.println("BLOGID============"+favorites.getBlogId());
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        return JSONResult.ok(favoritesService.selectStarStatus(userId,favorites));
    }
    /**
     *@description: 取消某收藏
     *@param:  * @param blogId
     *@return:
     *@Author: Zakary
     *@date: 2020/3/10 0:02
    */
    @RequestMapping(value = "/delFavorites",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult delFavorites(HttpServletRequest request,@RequestBody Favorites favorites){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        favoritesService.delFavorites(userId,favorites);
        return JSONResult.ok();
    }
}
