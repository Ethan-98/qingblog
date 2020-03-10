package com.zakary.qingblog.domain;

import org.springframework.stereotype.Repository;

/**
 * @ClassNameFavoritesList
 * @Description
 * @Author
 * @Date2020/3/7 22:15
 * @Version V1.0
 **/
@Repository
public class FavoritesList {
    private Integer favoritesId;
    private Integer userId;
    private String favoritesName;

    @Override
    public String toString() {
        return "FavoritesList{" +
                "favoritesId=" + favoritesId +
                ", userId=" + userId +
                ", favoritesName='" + favoritesName + '\'' +
                '}';
    }

    public Integer getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Integer favoritesId) {
        this.favoritesId = favoritesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFavoritesName() {
        return favoritesName;
    }

    public void setFavoritesName(String favoritesName) {
        this.favoritesName = favoritesName;
    }

}
