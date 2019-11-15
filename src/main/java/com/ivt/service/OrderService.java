package com.ivt.service;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.CustomerEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.repositories.AccountRepository;
import com.ivt.repositories.CustomerRepository;
import com.ivt.repositories.OrderDetailRepository;
import com.ivt.repositories.OrderRepository;
import com.ivt.repositories.ProductDetailRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
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

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(rollbackFor = Exception.class)
    public void AddOrder(OrderEntity order, List<ProductDetailEntity> listProductDetailStore) throws MessagingException, FileNotFoundException, IOException {
        CustomerEntity customer = order.getCustomer();
        CustomerEntity customerData = customerRepository.findByEmail(customer.getEmail());
        if (customerData != null && customerData.getId() > 0) {
            customer.setId(customerData.getId());
        }
        if (customer.getAccount() == null) {
            AccountEntity accountData = accountRepository.findByEmail(customer.getEmail());
            if (accountData != null && accountData.getId() > 0) {
                customer.setAccount(accountData);
            }
        }
        CustomerEntity customerSaved = customerRepository.save(customer);
        order.setCustomer(customerSaved);
        OrderEntity orderSaved = orderRepository.save(order);
        List<OrderDetailEntity> listOrderDetail = orderSaved.getListOrderDetail();
        for (OrderDetailEntity item : listOrderDetail) {
            orderDetailRepository.save(item);
        }
        for (ProductDetailEntity productDetailEntity : listProductDetailStore) {
            productDetailRepository.save(productDetailEntity);
        }
        mailService.sendNewOrderMailPage(orderSaved);
    }

    public OrderEntity findOrderById(int orderId) {
        OrderEntity order = orderRepository.findOne(orderId);
        order.setListOrderDetail(orderDetailRepository.findByOrder(order));
        return order;
    }

    public List<OrderEntity> getAllOrderByAccountId(int accountId) {
        return orderRepository.getAllOrderByAccountId(accountId);
    }

    public CustomerEntity findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

//    code của hiệp
    public List<OrderEntity> getAllOrderProcessing() {
        //status = "'"+status+"'";
        return (List<OrderEntity>) orderRepository.getAllOrderByStatus();
    }
//

    public int getTotalOrderProcessing() {
        return (int) orderRepository.counOrderEntityProcessing();
    }

//
    public List<OrderEntity> getListByDate(String fromDate, String toDate) {
        fromDate = fromDate + " 00:00:00";
        toDate = toDate + " 23:59:59";
        return (List<OrderEntity>) orderRepository.searchByDate(fromDate, toDate);
    }

//
    public OrderEntity getOrderByID(int id) {
        return orderRepository.findOne(id);
    }

//
    public int getTotalOrderShipping() {
        LocalDate date = LocalDate.now();
        String fromDate = date.getYear() + "-" + date.getMonthValue() + "-01 " + "00:00:00";
        String toDate = date.getYear() + "-" + date.getMonthValue() + "-" + date.lengthOfMonth() + " 23:59:59";
        return (int) orderRepository.getAllOrderShipping(fromDate, toDate);
    }

//  get order by status
    public List<OrderEntity> getAllOrderByStatusParameter(String status) {
        //status = "'"+status+"'";
        return (List<OrderEntity>) orderRepository.getAllOrderByStatusParameter(status);
    }

    //get order by status cancel
    public List<OrderEntity> getAllOrderByStatusCancel(String status) {
        //status = "'"+status+"'";
        return (List<OrderEntity>) orderRepository.getAllOrderByStatusCancel(status);
    }

    //get totalprice in this month
    public double getTotalPriceInThisMonth() {
        try {
            LocalDate date = LocalDate.now();
            String fromDate = date.getYear() + "-" + date.getMonthValue() + "-01 " + "00:00:00";
            String toDate = date.getYear() + "-" + date.getMonthValue() + "-" + date.lengthOfMonth() + " 23:59:59";
            return orderRepository.getTotalPrice(fromDate, toDate);
        } catch (Exception e) {
            return 0;
        }
    }

    // get total order paid
    public int getTotalPaid() {
        return orderRepository.getTotalOrderPaid();
    }
// update order

    public boolean updateOrder(OrderEntity order) {
        OrderEntity o = orderRepository.save(order);
        if (o.getId() != 0 && o.getId() > 0) {
            return true;
        } else {
            return true;
        }
    }

    // get order between date
    public List<OrderEntity> getBetween(Date sd, Date ed) {
        return (List<OrderEntity>) orderRepository.findByOrderDateBetween(sd, ed);
    }

    // get order between date an stauts
    public List<OrderEntity> getBetweenLike(Date sd, Date ed, String status) {
        return (List<OrderEntity>) orderRepository.findByOrderDateBetweenAndOrderStatusLike(sd, ed, status);
    }

    ///code search by vu
    public List<OrderEntity> getAllOrderByAccountId2(int accountId) {
        return orderRepository.getAllOrderByAccountId2(accountId);
    }

    public List<OrderEntity> getAllOrderByAccountId3(int accountId) {
        return orderRepository.getAllOrderByAccountId3(accountId);
    }

    public List<OrderEntity> getAllOrderByAccountId4(int accountId) {
        return orderRepository.getAllOrderByAccountId4(accountId);
    }

    public List<OrderEntity> getAllOrderByAccountId5(int accountId) {
        return orderRepository.getAllOrderByAccountId5(accountId);
    }
}
