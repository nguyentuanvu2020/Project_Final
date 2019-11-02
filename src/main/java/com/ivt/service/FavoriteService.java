package com.ivt.service;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.FavoriteEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.repositories.FavoriteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public int favoriteTotalbyProductId(int id) {
        return favoriteRepository.findCountFavoriteByProductId(id);
    }

    public void addFavorite(FavoriteEntity favorite) {
        favoriteRepository.save(favorite);
    }

    public List<FavoriteEntity> getListFavoriteByProduct(ProductEntity product) {
        return favoriteRepository.findByProduct(product);
    }

    public boolean checkIsFavorite(ProductEntity product, AccountEntity account) {
        if (favoriteRepository.findByProductAndAccount(product, account) == null) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteFavorite(ProductEntity product, AccountEntity account) {
        FavoriteEntity favorite = favoriteRepository.findByProductAndAccount(product, account);
        favoriteRepository.delete(favorite);
    }
}
