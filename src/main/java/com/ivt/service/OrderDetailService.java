/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.repositories.OrderDetailRepository;
import com.ivt.repositories.ProductRepository;
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
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetailEntity> findByOrder(OrderEntity order) {
        List<OrderDetailEntity> listOrderDetail = orderDetailRepository.findByOrder(order);
        for (OrderDetailEntity orderDetail : listOrderDetail) {
            ProductEntity product = productRepository.findWholeProductById(orderDetail.getProduct().getId());
            orderDetail.setProduct(product);
        }
        return listOrderDetail;
    }

    public OrderDetailEntity findOrderDetailById(int id) {
        return orderDetailRepository.findOne(id);
    }

    public void addOrderDetail(OrderDetailEntity orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}
