/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.AccountRoleEntity;
import com.ivt.enums.AccountRole;
import com.ivt.service.AccountService;
import com.ivt.service.OrderService;
import com.ivt.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private RoleService roleService;

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
                ac.setAccountRoles(roleService.getAllRoleByAcountId(ac.getId()));
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal instanceof AccountEntity) {
                    List<AccountRoleEntity> listRole = ((AccountEntity) principal).getAccountRoles();
                    for (AccountRoleEntity item : listRole) {
                        if (item.getRole().equals(AccountRole.ROLE_ADMIN)) {
                            model.addAttribute("account", ac);
                            model.addAttribute("action", "update-profile");
                            model.addAttribute("roles", roleService.getAllRole());
                            return "management/info/profile";
                        }
                    }
                } else {
                    return "redirect:/home";
                }
            } else {
                return "redirect:/home";
            }
        } catch (Exception e) {
            return viewManagementHome(model, session);
        }
        return viewManagementHome(model, session);
    }
    
    @RequestMapping(value = "/update-profile", method = RequestMethod.POST)
    public String updateProfile(Model model, HttpSession session,
            @ModelAttribute("account") AccountEntity ac,
            @RequestParam(name = "role", required = false) int roleId[]) {
        if ("".equals(ac.getEmail()) || ac.getBirthDate() == null
                || "".equals(ac.getName()) || "".equals(ac.getPhoneNumber())) {
            model.addAttribute("info", "Please enter full info!!!");
            return "management/admin/list-account";
        }
        try {
            List<AccountRoleEntity> roles = new ArrayList<>();
            for (int i : roleId) {
                AccountRoleEntity role = roleService.getRoleById(i);
                roles.add(role);
            }
            if (ac.getId() > 0) {
                AccountEntity a = accountService.findAccountById(ac.getId());
                a.setAccountRoles(roles);
                a.setBirthDate(ac.getBirthDate());
                a.setAddress(ac.getAddress());
                a.setName(ac.getName());
                a.setPhoneNumber(ac.getPhoneNumber());
                accountService.addNewAccount(a);
            }
            model.addAttribute("info", "updated");
            return "redirect:../admin/list-account";
        } catch (Exception e) {
            return "management/admin/list-account";
        }
    }
}
