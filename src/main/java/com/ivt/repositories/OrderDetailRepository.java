/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Integer>{
    List<OrderDetailEntity> findByOrder(OrderEntity order);
}
