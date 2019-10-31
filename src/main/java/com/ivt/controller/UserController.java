
package com.ivt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/home")
    public String viewHome(Model model) {
        model.addAttribute("message", "User Home Page");
        return "user/home";
    }
}
