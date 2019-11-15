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

    @Query(value = "SELECT distinct * FROM products p order by p.id desc limit 10", nativeQuery = true)
    public List<ProductEntity> get10ProductNew();

    @Query("select distinct b from ProductEntity b where b.name like ?1 or b.category.name like ?1")
    List<ProductEntity> searchProductHome(String search);

    @Query(value = "SELECT distinct * FROM products p order by p.id asc", nativeQuery = true)
    List<ProductEntity> findAllProductByNewASC();

    @Query(value = "SELECT distinct * FROM products p order by p.id desc", nativeQuery = true)
    List<ProductEntity> findAllProductByNewDESC();

    @Query(value = "SELECT distinct * FROM products p order by p.price asc", nativeQuery = true)
    List<ProductEntity> findAllProductByPriceASC();

    @Query(value = "SELECT distinct * FROM products p order by p.price desc", nativeQuery = true)
    List<ProductEntity> findAllProductByPriceDESC();

    @Query(value = "SELECT distinct * FROM products p order by p.name asc", nativeQuery = true)
    List<ProductEntity> findAllProductByNameASC();

    @Query(value = "SELECT distinct * FROM products p order by p.name desc", nativeQuery = true)
    List<ProductEntity> findAllProductByNameDESC();

    @Query(value = "select p.*from products p left join order_details f\n"
            + "on p.id = f.product_id\n"
            + "group by p.id\n"
            + "order by count(f.product_id) desc\n"
            , nativeQuery = true)
    public List<ProductEntity> getAllProductHot();
    
    @Query(value = "select p.* from products p left join favorites f\n"
            + "on p.id = f.product_id\n"
            + "group by p.id\n"
            + "order by count((f.product_id)) desc\n"
            , nativeQuery = true)
    public List<ProductEntity> getAllProductFavorite();
    
    @Query(value = "SELECT distinct p FROM ProductEntity p where p.category.name = ?1")
    List<ProductEntity> findAllProductByCategoryName(String name);
    
    @Query(value = "SELECT distinct p.* from products p join productdetails pd on p.id = pd.product_id where pd.size_id = ?1", nativeQuery = true)
    List<ProductEntity> findAllProductBySizeId(int id);
    
    @Query(value = "SELECT distinct p.* from products p join productdetails pd on p.id = pd.product_id where pd.color_id = ?1", nativeQuery = true)
    List<ProductEntity> findAllProductByColorId(int id);
    
    @Query(value = "SELECT distinct p.* from products p join favorites f on p.id = f.product_id where f.account_id = ?1", nativeQuery = true)
    List<ProductEntity> findAllFavoriteProductByAccountId(int id);
    
    @Query(value = "SELECT * FROM products p where p.price <= 500000 order by p.price", nativeQuery = true)
    List<ProductEntity> searchByMoneyLowerFive();
    
    @Query(value = "SELECT * FROM products p where p.price >= 500000 and p.price <= 1000000 order by p.price", nativeQuery = true)
    List<ProductEntity> searchByMoneyFromFiveToOne();
    
    @Query(value = "SELECT * FROM products p where p.price >= 1000000 and p.price <= 1500000 order by p.price", nativeQuery = true)
    List<ProductEntity> searchByMoneyFromOneToFive();
    
    @Query(value = "SELECT * FROM products p where p.price >= 1500000 and p.price <= 2000000 order by p.price", nativeQuery = true)
    List<ProductEntity> searchByMoneyFromFiveToTwo();
    
    @Query(value = "SELECT * FROM products p where p.price >= 2000000  order by p.price", nativeQuery = true)
    List<ProductEntity> searchByMoneyMoreTwo();
    
    @Query(value = "SELECT * FROM products p where p.category_id = ?1", nativeQuery = true)
    List<ProductEntity> searchByCategoryIdAJ(int id);
    
    @Query(value = "SELECT * FROM products p where p.category_id = ?1 or p.category_id = ?2", nativeQuery = true)
    List<ProductEntity> searchByCategoryIdAJ2(int id1,int id2);
}
