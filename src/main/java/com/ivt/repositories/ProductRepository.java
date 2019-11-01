/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.ProductEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Query(value = "SELECT distinct p FROM ProductEntity p INNER JOIN FETCH p.listImageProductDetail pi")
    public List<ProductEntity> getAllProductAndImage();

    @Query(value = "SELECT distinct p FROM ProductEntity p INNER JOIN FETCH p.listImageProductDetail pi where p.id = ?1")
    public ProductEntity findWholeProductById(int id);

//    code của hiệp
    @Query(value = "SELECT distinct p FROM ProductEntity p INNER JOIN FETCH p.listProductDetail pd")
    public List<ProductEntity> getAll();

    @Query(value = "SELECT p FROM ProductEntity p JOIN p.listPromotion pm WHERE pm.id = ?1")
    public List<ProductEntity> getByPromotionId(int id);
    
}
