package com.zakary.qingblog.mapper;

import com.zakary.qingblog.domain.Favorites;
import com.zakary.qingblog.domain.FavoritesList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassNameFavoritesListMapper
 * @Description
 * @Author
 * @Date2020/3/7 22:21
 * @Version V1.0
 **/
@Mapper
@Repository
public interface FavoritesListMapper {
    public List<FavoritesList> selectFavoritesListByUserId(int userId);
    public int insertFavoritesList(FavoritesList favoritesList);
    public FavoritesList selectFavoritesListByFavoritesId(@Param("favoritesId") int favoritesId);
    public void  delFavoritesList(int favoritesId);
    public int selectFavoritesListByName(FavoritesList favoritesList);
}
