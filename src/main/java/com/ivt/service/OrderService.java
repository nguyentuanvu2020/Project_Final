package com.ivt.service;

import com.ivt.entities.CustomerEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.repositories.CustomerRepository;
import com.ivt.repositories.OrderDetailRepository;
import com.ivt.repositories.OrderRepository;
import com.ivt.repositories.ProductDetailRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
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

    @Autowired
    private MailService mailService;
    
    @Transactional(rollbackFor = Exception.class)
    public void AddOrder(OrderEntity order,List<ProductDetailEntity> listProductDetailStore) throws MessagingException, FileNotFoundException, IOException{
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
//        mailService.sendEmail(orderSaved);
    }
    public List<OrderEntity> getAllOrderByAccountId(int accountId){
        return orderRepository.getAllOrderByAccountId(accountId);
    }

//    code của hiệp
    public List<OrderEntity> getAllOrderProcessing() {
        //status = "'"+status+"'";
        return (List<OrderEntity>) orderRepository.getAllOrderByStatus();
    }

    public int getTotalOrderProcessing() {
        return (int) orderRepository.counOrderEntityProcessing();
    }

    public List<OrderEntity> getListByDate(String fromDate, String toDate) {
        fromDate = fromDate + " 00:00:00";
        toDate = toDate + " 23:59:59";
        return (List<OrderEntity>) orderRepository.searchByDate(fromDate, toDate);
    }
    
    public OrderEntity getOrderByID(int id){
        return orderRepository.findOne(id);
    }
}
