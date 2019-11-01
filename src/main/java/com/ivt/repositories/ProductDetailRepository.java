/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.ColorEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.ProductEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetailEntity, Integer> {

    List<ProductDetailEntity> findByProduct(ProductEntity product);

    @Query(value = "select * from productdetails where product_id = ?1 and color_id = ?2", nativeQuery = true)
    List<ProductDetailEntity> getProductDetailByProductIdAndColorId(int productId, int colorId);

    @Query(value = "SELECT * FROM productdetails where product_id = ?1 and color_id = ?2 and size_id = ?3", nativeQuery = true)
    ProductDetailEntity getProductDetailByProductIdAndColorIdAndSizeId(int productId, int colorId, int sizeId);
    
}
