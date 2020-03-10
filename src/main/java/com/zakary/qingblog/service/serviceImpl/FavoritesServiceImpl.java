package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.Favorites;
import com.zakary.qingblog.domain.FavoritesList;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.mapper.FavoritesListMapper;
import com.zakary.qingblog.mapper.FavoritesMapper;
import com.zakary.qingblog.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassNameFavoritesServiceImpl
 * @Description
 * @Author
 * @Date2020/3/7 22:34
 * @Version V1.0
 **/
@Service
@Transactional
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesListMapper favoritesListMapper;
    @Autowired
    private FavoritesMapper favoritesMapper;

    @Override
    public List<FavoritesList> queryFavoritesListByUserId(int userId){
        return favoritesListMapper.selectFavoritesListByUserId(userId);
    }

    @Override
    public FavoritesList addFavoritesList(FavoritesList favoritesList){
        if(favoritesListMapper.selectFavoritesListByName(favoritesList)!=0){
            throw new BusinessException("文件夹名称重复！");
        }
        int favoritesId=favoritesListMapper.insertFavoritesList(favoritesList);
        return favoritesListMapper.selectFavoritesListByFavoritesId(favoritesId);
    }

    @Override
    public void delFavoritesList(FavoritesList favoritesList){
        int favoritesId=favoritesList.getFavoritesId();
        System.out.println(favoritesId);
        //删除此收藏文件夹下的所有博客
        favoritesMapper.delFavoritesByFavoritesId(favoritesId);
        //删除此收藏文件夹
        favoritesListMapper.delFavoritesList(favoritesId);
    }

//    @Override
//    public int selectFavoritesListByName(FavoritesList favoritesList){
//        return  favoritesListMapper.selectFavoritesListByName(favoritesList);
//    }

    @Override
    public void addFavorites(int userId,Favorites favorites){
        if(selectStarStatus(userId,favorites)>0){
            throw new BusinessException("此博客已收藏！");
        }
        favoritesMapper.insert(favorites);
    }

    @Override
    public int selectStarStatus(int userId,Favorites favorites){
        return favoritesMapper.selectStarStatus(userId,favorites.getBlogId());
    }

    @Override
    public void delFavorites(int userId,Favorites favorites){
        int favoritesId=favoritesMapper.selectFavoritesIdByUserIdBlogId(userId,favorites.getBlogId());
        favoritesMapper.deleteByPrimaryKey(favoritesId,favorites.getBlogId());
    }
}
