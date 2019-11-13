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

    @Query(value = "SELECT distinct p FROM ProductEntity p INNER JOIN FETCH p.listImageProductDetail pi order by p.id desc")
    public List<ProductEntity> getAllProductAndImage();

    @Query(value = "SELECT distinct p FROM ProductEntity p INNER JOIN FETCH p.listImageProductDetail pi where p.id = ?1")
    public ProductEntity findWholeProductById(int id);

    @Query(value = "SELECT  p FROM ProductEntity p INNER JOIN  OrderDetailEntity od on p.id = od.product.id where od.id = ?1")
    public ProductEntity findProductByOrderDetailId(int id);

//    code của hiệp
    @Query(value = "SELECT distinct p FROM ProductEntity p INNER JOIN FETCH p.listProductDetail pd")
    public List<ProductEntity> getAll();

    @Query(value = "SELECT p FROM ProductEntity p JOIN p.listPromotion pm WHERE pm.id = ?1")
    public List<ProductEntity> getByPromotionId(int id);

    //Home query
    @Query(value = "select p.* from products p left join favorites f\n"
            + "on p.id = f.product_id\n"
            + "group by p.id\n"
            + "order by count((f.product_id)) desc\n"
            + "limit 6", nativeQuery = true)
    public List<ProductEntity> get6ProductFavorite();

    @Query(value = "select p.*from products p left join order_details f\n"
            + "on p.id = f.product_id\n"
            + "group by p.id\n"
            + "order by count(f.product_id) desc\n"
            + "limit 10;", nativeQuery = true)
    public List<ProductEntity> get10ProductHot();
    
    @Query(value = "SELECT distinct * FROM products p order by p.id desc limit 10",nativeQuery = true)
    public List<ProductEntity> get10ProductNew();

}
