package com.ivt.repositories;

import com.ivt.entities.ProductEntity;
import com.ivt.entities.ReviewEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findByProduct(ProductEntity product);
    
    @Query(value = "select count(r.id) from reviews r where product_id = ?1",nativeQuery = true)
    int CountReviewByProductId(int productId);
    
    @Query(value = "select avg(r.rate) from reviews r where product_id = ?1 group by product_id",nativeQuery = true)
    double AvgRateReviewByProductId(int productId);
}
