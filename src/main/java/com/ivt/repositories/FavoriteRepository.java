
package com.ivt.repositories;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.FavoriteEntity;
import com.ivt.entities.ProductEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends CrudRepository<FavoriteEntity, Integer>{
    @Query(value = "select count(f.id) from favorites f where product_id = ?1",nativeQuery = true)
    int findCountFavoriteByProductId(int productId);
    
    List<FavoriteEntity> findByProduct(ProductEntity product);
    
    FavoriteEntity findByProductAndAccount(ProductEntity product,AccountEntity account);
}
