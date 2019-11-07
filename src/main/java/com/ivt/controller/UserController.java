package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.enums.Gender;
import com.ivt.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/account")
    public String viewInfo(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("account", principal);
        model.addAttribute("genders", Gender.values());
        model.addAttribute("action", "update-data");
        return "user/account";
    }

    @RequestMapping("/update-data")
    public String updateData(Model model, @ModelAttribute("account") AccountEntity account) {
        accountService.updateAccount(account);
        return "redirect:/logout";
    }

    @RequestMapping("/change-password")
    public String viewChangePassword(Model model, @RequestParam(value = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("message", "Mật khẩu không đúng");
        }
        model.addAttribute("action", "save-new-password");
        return "user/change-password";
    }

    @RequestMapping("/save-new-password")
    public String changePassword(Model model, @RequestParam("old-password") String oldPassword,
            @RequestParam("new-password") String newPassword) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            if (accountService.changePassword(((AccountEntity) principal).getId(), oldPassword, newPassword) == false) {
                return "redirect:/user/change-password?error=true";
            }
        }
        return "redirect:/logout";
    }

    @RequestMapping("/manage-address")
    public String viewManageAddress(Model model) {
        return "user/manage-address";
    }

    @RequestMapping("/manage-order")
    public String viewManageOrder(Model model) {;
        return "user/manage-order";
    }
    
    @RequestMapping("/order-review")
    public String viewReviewOrder(Model model) {
        return "user/order-review";
    }

    @RequestMapping("/home")
    public String viewHome() {;
        return "redirect:/home";
    }

    @RequestMapping("/logout")
    public String logOut() {;
        return "redirect:/logout";
    }
    
}
