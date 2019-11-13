/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.service.AccountService;
import com.ivt.service.OrderService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewManagementHome(Model model, HttpSession session) {
        model.addAttribute("orderProcessing", orderService.getTotalOrderProcessing());
        model.addAttribute("orderShipping", orderService.getTotalOrderShipping());
        model.addAttribute("totalPrice", orderService.getTotalPriceInThisMonth());
        model.addAttribute("totalOrderPaid", orderService.getTotalPaid());
        session.removeAttribute("listdetail");
        session.removeAttribute("listDetail");
        session.removeAttribute("images");
        session.removeAttribute("order");
        return "management/index";
    }

    @RequestMapping(value = {"/profile/{accountId}"}, method = RequestMethod.GET)
    public String viewProfile(Model model, HttpSession session,
            @PathVariable("accountId") int accountId) {
        try {
            if (accountId > 0) {
                AccountEntity ac = accountService.findAccountById(accountId);
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (ac.getId() == ((AccountEntity) principal).getId()) {
                    model.addAttribute("account", ac);
                    model.addAttribute("action", "update-profile");
                    return "management/info/profile";
                } else {
                    return viewManagementHome(model, session);
                }
            } else {
                return viewManagementHome(model, session);
            }
        } catch (Exception e) {
            return viewManagementHome(model, session);
        }
    }
    
    
    @RequestMapping(value = "/update-profile", method = RequestMethod.POST)
    public String updateProfile(Model model, HttpSession session,
            @ModelAttribute("account") AccountEntity ac) {
        if(ac.getId() > 0){
            accountService.addNewAccount(ac);
        }
        return viewManagementHome(model, session);
    }
}
