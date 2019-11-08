/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.repositories.OrderDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author phand
 */
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository detailRepository;

    public List<OrderDetailEntity> findByOrder(OrderEntity order) {
        return (List<OrderDetailEntity>) detailRepository.findByOrder(order);
    }
}
