/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.AccountRoleEntity;
import com.ivt.entities.ColorEntity;
import com.ivt.entities.CustomerEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.SizeEntity;
import com.ivt.enums.AccountRole;
import com.ivt.enums.OrderStatus;
import com.ivt.model.CartByHiep;
import com.ivt.model.ItemProduct;
import com.ivt.service.ColorService;
import com.ivt.service.OrderDetailService;
import com.ivt.service.OrderService;
import com.ivt.service.ProductDetailService;
import com.ivt.service.ProductService;
import com.ivt.service.SizeService;
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
    @Autowired SizeService sizeService;
    @Autowired
    private ColorService colorService;

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
            session.setAttribute("name", status + t);
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
                session.setAttribute("name", status + " - " + sd + " - " + ed);
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
        double totalPrice = 0;
        for (OrderEntity orderEntity : ListPaid) {
            totalPrice += orderEntity.getTotalPrice();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("listPaidOrders", ListPaid);
        return "management/seller/list-order-paid";
    }

    //paid cancel
    @RequestMapping("/cancel-orders")
    public String viewListOrderCancel(Model model) {
        List<OrderEntity> ListCancels = new ArrayList<OrderEntity>();
        model.addAttribute("oderStatus", OrderStatus.values());
        ListCancels = orderService.getAllOrderByStatusCancel(OrderStatus.CANCEL.toString());
        model.addAttribute("ListCancels", ListCancels);
        return "management/seller/list-order-cancel";
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
                        od.setDeliveredDate(new Date());
                        orderService.updateOrder(od);
                        return viewListOrderShipping(model);
                    case "CONFIRMED":
                        return "redirect:management/seller/list-order-confirmed";
                    case "SHIPPING":
                        od.setOrderStatus(OrderStatus.PAID.toString());
                        orderService.updateOrder(od);
                        return viewListOrderPaid(model);
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
    @Autowired
    private ProductDetailService detailService;

    //cancel order by id
    @RequestMapping(value = "cancel-order/{orderId}", method = RequestMethod.GET)
    public String cancelOrder(Model model,
            @PathVariable("orderId") int orderId
    ) {
        if (orderId != 0 && orderId > 0) {
            OrderEntity od = orderService.getOrderByID(orderId);
            List<OrderDetailEntity> listOddetail = orderDetailService.findByOrder(od);
            od.setListOrderDetail(listOddetail);
            if (od.getId() > 0) {
                // duyet va lay pr
                for (OrderDetailEntity oD : od.getListOrderDetail()) {
                    SizeEntity size = sizeService.getBySize(oD.getSize());
                    ColorEntity color = colorService.getByName(oD.getColor());
                    ProductDetailEntity detailUpdate = detailService.getByPCS(oD.getProduct(), color, size);
                    if(detailUpdate.getColor().getId() == color.getId()
                            && detailUpdate.getProductSize().getId() == size.getId()
                            && oD.getProduct().getId() == detailUpdate.getProduct().getId()){
                        detailUpdate.setProductQuantity(detailUpdate.getProductQuantity()+oD.getQuantity());
                        detailService.Save(detailUpdate);
                    }
                }
                
                od.setOrderStatus(OrderStatus.CANCEL.toString());
                orderService.updateOrder(od);
                return viewListOrderCancel(model);
            } else {
                return "redirect:../proccessing-orders";
            }
        } else {
            return "redirect:../proccessing-orders";
//            return this.viewListOrderProcessing(model);
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
                if (accountRole.getRole().equals(AccountRole.ROLE_MANAGER)
                        || accountRole.getRole().equals(AccountRole.ROLE_SELLER)) {
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

    // make order
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/makeorder", method = RequestMethod.GET)
    public String viewMakeOrder(Model model, HttpSession session) {
        CartByHiep hiepCart = new CartByHiep();
        session.setAttribute("hiepCart", hiepCart);
        model.addAttribute("products", productService.getAll());
        model.addAttribute("customerInfo", new CustomerEntity());
        return "management/seller/make-order";
    }

    @RequestMapping(value = "/add-product-to-cart", method = RequestMethod.GET)
    public String addToCart(Model model, HttpSession session,
            @RequestParam("productID") int productId,
            @RequestParam("color") String color,
            @RequestParam("size") int size) {

        ItemProduct newItem = new ItemProduct();
        newItem.setProductID(productId);
        newItem.setColor(color);
        newItem.setSize(size);
        CartByHiep hiepCart = (CartByHiep) session.getAttribute("hiepCart");
        hiepCart.themMonHang(newItem);
        return "";
    }
}
