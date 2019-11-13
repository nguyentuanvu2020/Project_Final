package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.AccountRoleEntity;
import com.ivt.service.AccountService;
import com.ivt.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;

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
    public String viewAddAccount(Model model) {
        model.addAttribute("action", "add-account");
        model.addAttribute("newAcount", new AccountEntity());
        model.addAttribute("roles", roleService.getAllRole());
        return "/management/admin/add-account";
    }

    @Autowired
    private BCryptPasswordEncoder passwordEndcoder;

    @RequestMapping(value = "/add-account", method = RequestMethod.POST)
    public String addAccount(Model model,
            @ModelAttribute("newAcount") AccountEntity newAccount,
            @RequestParam("role") int roleId[]) {
        List<AccountRoleEntity> roles = new ArrayList<>();
        for (int i : roleId) {
            AccountRoleEntity role = roleService.getRoleById(i);
            roles.add(role);
        }
        newAccount.setAccountRoles(roles);
        newAccount.setDisabled(false);
        newAccount.setPassword(passwordEndcoder.encode("25251325"));
        newAccount = accountService.addNewAccount(newAccount);
        if (newAccount.getId() > 0) {
            model.addAttribute("info", "Sussces Account have id: " + newAccount.getId());
            return viewListAcount(model);
        } else {
            model.addAttribute("info", "Fail");
            return viewListAcount(model);
        }
    }

    @RequestMapping(value = "/update-status/{accountId}")
    public String updateStatusAccount(Model model,
            @PathVariable("accountId") int accountId) {

        AccountEntity ac = accountService.findAccountById(accountId);
        if (ac.getId() > 0) {
            if (ac.isDisabled()) {
                ac.setDisabled(false);
                accountService.addNewAccount(ac);
            } else {
                ac.setDisabled(true);
                accountService.addNewAccount(ac);
            }
        }
        return viewListAcount(model);
    }

    @RequestMapping("/list-account")
    public String viewListAcount(Model model) {
        model.addAttribute("listAccount", accountService.getAll());
        return "/management/admin/list-account";
    }
}
