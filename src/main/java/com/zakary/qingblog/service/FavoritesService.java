package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.Favorites;
import com.zakary.qingblog.domain.FavoritesList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNameFavoritesService
 * @Description
 * @Author
 * @Date2020/3/7 22:33
 * @Version V1.0
 **/

public interface FavoritesService {
    public List<FavoritesList> queryFavoritesListByUserId(int userId);
    public FavoritesList addFavoritesList(FavoritesList favoritesList);
    public void delFavoritesList(FavoritesList favoritesList);
//    public int selectFavoritesListByName(FavoritesList favoritesList);
    public void addFavorites(int userId,Favorites favorites);
    public int selectStarStatus(int userId,Favorites favorites);
    public void delFavorites(int userId,Favorites favorites);
}
