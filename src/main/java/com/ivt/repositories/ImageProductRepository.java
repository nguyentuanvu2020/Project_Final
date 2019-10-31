/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.ImageProductEntity;
import com.ivt.entities.ProductEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepository extends CrudRepository<ImageProductEntity, Integer> {

    List<ImageProductEntity> findByProduct(ProductEntity product);

    @Query(value = "select distinct b from ImageProductEntity b where b.product.id = ?1")
    List<ImageProductEntity> findByProductId(int productId);
    
//    code của hiệp
    
//    List<ImageProductEntity> findByProduct(ProductEntity product);
    
//    @Query(value = "delete from product_images where name = ?1")
//    void deleteByNameImage(String name);
    
    List<ImageProductEntity> deleteByName(String name);
    
    List<ImageProductEntity> findByName(String name);
}
