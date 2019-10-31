/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewManagementHome(Model model,HttpSession session) {
        session.removeAttribute("listdetail");
        session.removeAttribute("listDetail");
        session.removeAttribute("images");
        return "management/index";
    }
}
