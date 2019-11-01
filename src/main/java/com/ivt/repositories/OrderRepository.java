/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.OrderEntity;
import com.ivt.enums.OrderStatus;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer>{
    
//    code của hiệp
    @Query(value = "select o from OrderEntity o where o.orderStatus = ?1")
    List<OrderEntity> getAllOrderByStatus(OrderStatus s);
    
    
    @Query(value = "SELECT * FROM orders o join customers c on o.customer_id = c.id where c.account_id = ?1",nativeQuery = true)
    List<OrderEntity> getAllOrderByAccountId(int accountId);
}
