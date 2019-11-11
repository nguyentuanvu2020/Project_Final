/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivt.repositories;

import com.ivt.entities.OrderEntity;
import com.ivt.enums.OrderStatus;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer>{
    @Query(value = "SELECT * FROM orders o join customers c on o.customer_id = c.id where c.account_id = ?1 order by o.id",nativeQuery = true)
    List<OrderEntity> getAllOrderByAccountId(int accountId);
//    code của hiệp
// order processing
    
    @Query(value = "SELECT * FROM project_final.orders where orderStatus like 'PROCESSING'", nativeQuery = true)
    List<OrderEntity> getAllOrderByStatus();
//order sending

    @Query(value = "SELECT count(id) FROM project_final.orders where orderDate between ?1 and ?2 and orderStatus like 'SHIPPING'", nativeQuery = true)
    int getAllOrderShipping(String from, String to);

    @Query(value = "select count(id) from project_final.orders where orderStatus like 'PROCESSING' ", nativeQuery = true)
    int counOrderEntityProcessing();

//    @Query(value = "select o from OrderEntity o where o.orderDate between ?1 and ?2 and o.orderStatus = 'PROCESSING'")
//    List<OrderEntity> searchByDate(String fromDate, String toDate);
    @Query(value = "SELECT * FROM project_final.orders where orderDate between ?1 and ?2 "
            + "and orderStatus = 'PROCESSING'", nativeQuery = true)
    List<OrderEntity> searchByDate(String fromDate, String toDate);

    @Query(value = "select o from OrderEntity o where o.orderStatus = ?1")
    List<OrderEntity> getAllOrderByStatus(OrderStatus s);

//    @Query(value = "SELECT * FROM orders o join customers c on o.customer_id = c.id where c.account_id = ?1", nativeQuery = true)
//    List<OrderEntity> getAllOrderByAccountId(int accountId);
    
    @Query(value = "SELECT * FROM project_final.orders where orderStatus like ?1", nativeQuery = true)
    List<OrderEntity> getAllOrderByStatusParameter(String status);

    //get totalprice in this month
    @Query(value = "SELECT SUM(totalPrice) FROM project_final.orders where orderDate between ?1 and ?2 and orderStatus like 'PAID'", nativeQuery = true)
    double getTotalPrice(String from, String to);
    
    @Query(value = "select count(o.id) from orders o where o.orderStatus = 'PAID'", nativeQuery = true)
    int getTotalOrderPaid();
    // get betwen date
    List<OrderEntity> findByOrderDateBetween(Date sd, Date ed);
    // get betwen date adn status
    List<OrderEntity> findByOrderDateBetweenAndOrderStatusLike(Date sd, Date ed,String status);
}
