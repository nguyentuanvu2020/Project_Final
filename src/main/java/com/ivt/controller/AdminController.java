
package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

//    @RequestMapping("/home")
//    public String viewHome(Model model) {
//        model.addAttribute("message", "Admin Home Page");
//        return "admin/home";
//    }
    @RequestMapping("/home")
    public String viewHome(Model model) {
        model.addAttribute("message", "Admin Home Page");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (principal instanceof AccountEntity) {
            String currentUserName = ((AccountEntity) principal).getName();
            // principal
            model.addAttribute("username", principal);
        }
        return "/management/admin/home";
    }

    @RequestMapping("/add-account")
    public String viewAddAccount(){
        return "/management/admin/add-account";
    }
    
    @RequestMapping("/update-role")
    public String viewUpdateRoleAcount(){
        return "/management/admin/update-role-account";
    }
    
    @RequestMapping("/active-account")
    public String viewActiveAcount(){
        return "/management/admin/active-account";
    }
    
    @RequestMapping("/block-account")
    public String viewBlockAcount(){
        return "/management/admin/block-account";
    }
}
