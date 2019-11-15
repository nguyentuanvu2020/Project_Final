package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.ColorEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.entities.ReviewEntity;
import com.ivt.entities.SizeEntity;
import com.ivt.enums.Gender;
import com.ivt.enums.OrderStatus;
import com.ivt.service.AccountService;
import com.ivt.service.ColorService;
import com.ivt.service.MailService;
import com.ivt.service.OrderDetailService;
import com.ivt.service.OrderService;
import com.ivt.service.ProductDetailService;
import com.ivt.service.ProductService;
import com.ivt.service.ReviewService;
import com.ivt.service.SizeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ProductDetailService productDetailService;

    @RequestMapping("/account")
    public String viewInfo(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            model.addAttribute("account", principal);
            model.addAttribute("genders", Gender.values());
            model.addAttribute("action", "update-data");
            return "user/account";
        } else {
            return "redirect:/login";
        }

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
    public String viewManageOrder(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            model.addAttribute("listOrder", orderService.getAllOrderByAccountId(((AccountEntity) principal).getId()));
            model.addAttribute("status", "PROCESSING");
            return "user/manage-order";
        } else {
            return "redirect:/home";
        }

    }

    @RequestMapping("/cancel-order")
    public String cancelOrder(Model model, @RequestParam("orderId") int orderId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            OrderEntity order = orderService.findOrderById(orderId);
            if (order.getOrderStatus().equals("PROCESSING")) {
                for (OrderDetailEntity item : order.getListOrderDetail()) {
                    SizeEntity size = sizeService.getBySize(item.getSize());
                    ColorEntity color = colorService.getByName(item.getColor());
                    ProductDetailEntity productDetailUpdate
                            = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(item.getProduct().getId(), color.getId(), size.getId());
                    productDetailUpdate.setProductQuantity(productDetailUpdate.getProductQuantity() + item.getQuantity());
                    productDetailService.Save(productDetailUpdate);
                }
                order.setOrderStatus(String.valueOf(OrderStatus.CANCEL));
                orderService.updateOrder(order);
                try {
                    mailService.sendCancelMailPage(order);
                } catch (Exception e) {
                }
                return "redirect:/user/manage-order";
            } else {
                return "redirect:/home";
            }
        } else {
            return "redirect:/home";
        }

    }

    @RequestMapping("/detail-order")
    public String viewDetailOrder(Model model, @RequestParam("id") int id) {
        model.addAttribute("order", orderService.getOrderByID(id));
        model.addAttribute("listOrderDetail", orderDetailService.findByOrder(orderService.getOrderByID(id)));
        model.addAttribute("status", OrderStatus.PAID);
        return "user/detail-order";
    }

    @RequestMapping("/order-review")
    public String viewReviewOrder(Model model,
            @RequestParam("orderDetailId") int orderDetailId) {
        OrderDetailEntity orderDetail = orderDetailService.findOrderDetailById(orderDetailId);
        if (orderDetail.isIsReviewed()) {
            return "redirect:/home";
        }
        model.addAttribute("orderDetailId", orderDetailId);
        model.addAttribute("action", "add-review-product");
        return "user/order-review";
    }

    @RequestMapping("/add-review-product")
    public String addReviewOrder(Model model,
            @RequestParam("orderDetailId") int orderDetailId, @RequestParam("rating") int rating,
            @RequestParam("content") String content) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {

            ProductEntity product = productService.findProductByOrderDetailId(orderDetailId);
            ReviewEntity newReview = new ReviewEntity();
            newReview.setAccount((AccountEntity) principal);
            newReview.setProduct(product);
            newReview.setContent(content);
            newReview.setRate(rating);
            newReview.setOrderDetailId(orderDetailId);
            OrderDetailEntity orderDetail = orderDetailService.findOrderDetailById(orderDetailId);
            newReview.setTypeOfShoes("Màu: " + orderDetail.getColor() + " Size: " + orderDetail.getSize());
            reviewService.addReview(newReview);
            orderDetail.setIsReviewed(true);
            orderDetailService.addOrderDetail(orderDetail);
            return "redirect:/user/manage-order";
        } else {
            return "redirect:/home";
        }

    }

    @RequestMapping("/home")
    public String viewHome() {;
        return "redirect:/home";
    }

    @RequestMapping("/logout")
    public String logOut() {;
        return "redirect:/logout";
    }

//    Orderby status order
    @RequestMapping("/manage-order-processing")
    public String viewManageOrder2(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            model.addAttribute("listOrder", orderService.getAllOrderByAccountId2(((AccountEntity) principal).getId()));
            model.addAttribute("status", OrderStatus.PROCESSING);
            return "user/manage-order";
        } else {
            return "redirect:/home";
        }

    }

    @RequestMapping("/manage-order-shipping")
    public String viewManageOrder3(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            model.addAttribute("listOrder", orderService.getAllOrderByAccountId3(((AccountEntity) principal).getId()));
            model.addAttribute("status", OrderStatus.PROCESSING);
            return "user/manage-order";
        } else {
            return "redirect:/home";
        }

    }

    @RequestMapping("/manage-order-paid")
    public String viewManageOrder4(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            model.addAttribute("listOrder", orderService.getAllOrderByAccountId4(((AccountEntity) principal).getId()));
            model.addAttribute("status", OrderStatus.PROCESSING);
            return "user/manage-order";
        } else {
            return "redirect:/home";
        }

    }

    @RequestMapping("/manage-order-cancel")
    public String viewManageOrder5(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            model.addAttribute("listOrder", orderService.getAllOrderByAccountId5(((AccountEntity) principal).getId()));
            model.addAttribute("status", OrderStatus.PROCESSING);
            return "user/manage-order";
        } else {
            return "redirect:/home";
        }

    }
}
