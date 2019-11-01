/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.entities.OrderEntity;
import com.ivt.enums.OrderStatus;
import com.ivt.repositories.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author phand
 */
@Controller
@RequestMapping(value = "/management/seller")
public class SellerController {
    @Autowired
    private OrderRepository orderRepository;
    
    @RequestMapping("/processing-orders")
    public String viewListOrderProcessing(Model model){
        List<OrderEntity> ListProcessing = new ArrayList<OrderEntity>();
        ListProcessing = orderRepository.getAllOrderByStatus(OrderStatus.PROCESSING);
        model.addAttribute("processingOders", ListProcessing); 
        return "management/seller/list-order-processing";
    }
}
