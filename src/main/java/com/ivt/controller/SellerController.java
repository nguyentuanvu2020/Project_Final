/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.AccountRoleEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.enums.AccountRole;
import com.ivt.enums.OrderStatus;
import com.ivt.service.OrderDetailService;
import com.ivt.service.OrderService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    //processing orders
    @RequestMapping("/processing-orders")
    public String viewListOrderProcessing(Model model) {
        model.addAttribute("action", "management/seller/search");
        model.addAttribute("oderStatus", OrderStatus.values());
        List<OrderEntity> ListProcessing = new ArrayList<OrderEntity>();
        ListProcessing = orderService.getAllOrderProcessing();
        model.addAttribute("processingOders", ListProcessing);
        return "management/seller/list-order-processing";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(name = "ods", required = false) String status,
            @RequestParam("s-date") String startDate,
            @RequestParam("e-date") String endDate, HttpSession session) throws ParseException {
        if (startDate.isEmpty() || endDate.isEmpty()) {
            model.addAttribute("infomation", "List order of status " + status);
            List<OrderEntity> a = orderService.getAllOrderByStatusParameter(status);
            LocalDate t = LocalDate.now();
            session.setAttribute("name", status+t);
            session.setAttribute("order", a);
            model.addAttribute("orders", a);

        } else {
            SimpleDateFormat fmd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sd = fmd.parse(startDate + " 00:00:00");
            Date ed = fmd.parse(endDate + " 23:59:59");
            if (ed.before(sd)) {
                model.addAttribute("message", "Please reenter date!!!");
            } else {
                model.addAttribute("infomation", "List order in " + sd + "  to " + ed + " and status: " + status);
                List<OrderEntity> aa = orderService.getBetweenLike(sd, ed, status);
                session.setAttribute("name", status+" - "+sd+" - "+ed);
                session.setAttribute("order", aa);
                model.addAttribute("orders", aa);
            }
        }

        model.addAttribute("oderStatus", OrderStatus.values());

        return "management/seller/search-report";
    }

//ajax
    @RequestMapping("/search-by-date")
    public String searchByDate(Model model,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate
    ) {
//        fromDate = fromDate + " 00:00:00";
//        toDate = toDate + " 23:59:59";
        List<OrderEntity> ListProcessing = new ArrayList<OrderEntity>();
        ListProcessing = orderService.getListByDate(fromDate, toDate);
        if (ListProcessing.size() > 0) {
            model.addAttribute("processingOders", ListProcessing);
            return "management/seller/ajaxDate";
        } else {
            return "management/seller/ajaxDate";
        }
    }
// details by id

    @RequestMapping("/order-detail/{ordernum}")
    public String viewOrderDetail(Model model,
            @PathVariable("ordernum") int ordernumber
    ) {
        OrderEntity order = orderService.getOrderByID(ordernumber);
        if (order.getId() > 0) {
//            CustomerEntity customer  = customerService.getCustomerById(order.getCustomer().getId());
            List<OrderDetailEntity> orderdetails = orderDetailService.findByOrder(order);
            order.setListOrderDetail(orderdetails);
            model.addAttribute("orderDetail", order);
            return "management/seller/order-detail";
        } else {
            return "redirect:../management/seller/list-order-processing";
        }
    }

    //shipping orders
    @RequestMapping("/shipping-orders")
    public String viewListOrderShipping(Model model
    ) {
        model.addAttribute("oderStatus", OrderStatus.values());
        List<OrderEntity> ListShipping = new ArrayList<OrderEntity>();
        ListShipping = orderService.getAllOrderByStatusParameter(OrderStatus.SHIPPING.toString());
        model.addAttribute("shippingOrders", ListShipping);
        return "management/seller/list-order-shipping";
    }

    //paid orders
    @RequestMapping("/paid-orders")
    public String viewListOrderPaid(Model model
    ) {
        List<OrderEntity> ListPaid = new ArrayList<OrderEntity>();
        model.addAttribute("oderStatus", OrderStatus.values());
        ListPaid = orderService.getAllOrderByStatusParameter(OrderStatus.PAID.toString());
        model.addAttribute("listPaidOrders", ListPaid);
        return "management/seller/list-order-paid";
    }

    //update satus for order new
    @RequestMapping(value = "update-status-new/{orderId}", method = RequestMethod.GET)
    public String updateStatus(Model model,
            @PathVariable("orderId") int orderId
    ) {
        if (orderId != 0 && orderId > 0) {
            OrderEntity od = orderService.getOrderByID(orderId);
            boolean check = orderService.updateOrder(od);
            if (check) {
                switch (od.getOrderStatus()) {
                    case "PROCESSING":
                        od.setOrderStatus(OrderStatus.SHIPPING.toString());
                        orderService.updateOrder(od);
                        return "redirect:../shipping-orders";
                    case "CONFIRMED":
                        return "redirect:management/seller/list-order-confirmed";
                    case "SHIPPING":
                        od.setOrderStatus(OrderStatus.PAID.toString());
                        orderService.updateOrder(od);
                        return "redirect:../paid-orders";
                    case "PAID":
                        return "redirect:../paid-orders";
                    case "CANCEL":
                        return "redirect:management/seller/list-order-cancel";
                    case "RETURN":
                        return "redirect:management/seller/list-order-return";
                    default:
                        return "redirect:management/seller/list-order-processing";
                }
            } else {
                return "redirect:management/seller/list-order-processing";
            }
        } else {
            return "redirect:management/seller/list-order-processing";
        }
    }

    //cancel order by id
    @RequestMapping(value = "cancel-order/{orderId}", method = RequestMethod.GET)
    public String cancelOrder(Model model,
            @PathVariable("orderId") int orderId
    ) {
        if (orderId != 0 && orderId > 0) {
            OrderEntity od = orderService.getOrderByID(orderId);
            if (od.getId() > 0) {
                od.setOrderStatus(od.getOrderStatus() + "-" + OrderStatus.CANCEL.toString());
                orderService.updateOrder(od);
                return "redirect:../processing-orders";
            } else {
                return "redirect:../proccessing-orders";
            }
        } else {
            return "redirect:../proccessing-orders";
        }
    }

    @RequestMapping(value = "/export-file/{orderId}", method = RequestMethod.GET)
    public ModelAndView reportPdf(@PathVariable("orderId") int ordernumber
    ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Object roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean check = false;
        if (principal instanceof AccountEntity) {
            for (AccountRoleEntity accountRole : ((AccountEntity) principal).getAccountRoles()) {
                if (accountRole.getRole().equals(AccountRole.ROLE_MANAGER)) {
                    check = true;
                }
            }
        }
        if (check) {
            OrderEntity order = orderService.getOrderByID(ordernumber);
            List<OrderDetailEntity> orderdetails = orderDetailService.findByOrder(order);
            order.setListOrderDetail(orderdetails);
            return new ModelAndView("Pdf", "Data", order);
        } else {
            return new ModelAndView("redirect:../../../home", "message", "Not Allows");
        }
    }

    //make-report
    @RequestMapping("/make-report")
    public String viewMakeReport(Model model) {
        model.addAttribute("oderStatus", OrderStatus.values());
        return "management/seller/make-report";
    }

    // exprot file excel
    @RequestMapping(value = "/export-file-excel", method = RequestMethod.GET)
    public ModelAndView reportFileExcel(Model model, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("ExcelView");
        List<OrderEntity> orders = (List<OrderEntity>) session.getAttribute("order");
        String name = (String) session.getAttribute("name");
        for (OrderEntity item : orders) {
            item.setListOrderDetail(orderDetailService.findByOrder(item));
        }
        modelAndView.addObject("name", name);
        modelAndView.addObject("order", orders);
        return modelAndView;
    }

}
