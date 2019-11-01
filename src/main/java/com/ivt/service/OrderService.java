
package com.ivt.service;

import com.ivt.entities.CustomerEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.enums.OrderStatus;
import com.ivt.repositories.CustomerRepository;
import com.ivt.repositories.OrderDetailRepository;
import com.ivt.repositories.OrderRepository;
import com.ivt.repositories.ProductDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    private ProductDetailRepository productDetailRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Transactional(rollbackFor = Exception.class)
    public OrderEntity AddOrder(OrderEntity order,List<ProductDetailEntity> listProductDetailStore){
        CustomerEntity customerSaved = customerRepository.save(order.getCustomer());
        order.setCustomer(customerSaved);
        OrderEntity orderSaved = orderRepository.save(order);
        List<OrderDetailEntity> listOrderDetail = orderSaved.getListOrderDetail();
        for (OrderDetailEntity item : listOrderDetail) {
            orderDetailRepository.save(item);
        }
        for (ProductDetailEntity productDetailEntity : listProductDetailStore) {
            productDetailRepository.save(productDetailEntity);
        }
        return orderSaved;
    }
    
//    code của hiệp
    public List<OrderEntity> getAllOrderProcessing(OrderStatus status){
        return orderRepository.getAllOrderByStatus(status);
    }
}
