/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.service.OrderService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author phand
 */
@Controller
@RequestMapping("/management")
public class ManagementCotroller {
    
    @Autowired 
    private OrderService orderService;
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewManagementHome(Model model,HttpSession session) {
        model.addAttribute("orderProcessing", orderService.getTotalOrderProcessing());
        session.removeAttribute("listdetail");
        session.removeAttribute("listDetail");
        session.removeAttribute("images");
        return "management/index";
    }
}
