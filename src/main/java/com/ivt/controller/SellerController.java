/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.service.OrderDetailService;
import com.ivt.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author phand
 */
@Controller
@RequestMapping(value = "/management/seller")
public class SellerController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/processing-orders")
    public String viewListOrderProcessing(Model model) {
        List<OrderEntity> ListProcessing = new ArrayList<OrderEntity>();
        ListProcessing = orderService.getAllOrderProcessing();
        model.addAttribute("processingOders", ListProcessing);
        return "management/seller/list-order-processing";
    }

    @RequestMapping("/search-by-date")
    public String searchByDate(Model model,@RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate) {
//        fromDate = fromDate + " 00:00:00";
//        toDate = toDate + " 23:59:59";
        List<OrderEntity> ListProcessing = new ArrayList<OrderEntity>();
        ListProcessing = orderService.getListByDate(fromDate, toDate);
        if (ListProcessing.size() > 0) {
            model.addAttribute("processingOders",ListProcessing);
            return "management/seller/ajaxDate";
        } else {
            return "nodata";
        }
    }

    @RequestMapping("/order-detail/{ordernum}")
    public String viewOrderDetail(Model model, @PathVariable("ordernum") int ordernumber) {
        OrderEntity order = orderService.getOrderByID(ordernumber);
        if (order.getId() > 0) {
            List<OrderDetailEntity> orderdetails = orderDetailService.getDetailByID(order);
            order.setListOrderDetail(orderdetails);
            model.addAttribute("orderDetail", order);
            return "management/seller/order-detail";
        } else {
            return "redirect:../management/seller/list-order-processing";
        }
    }
}
