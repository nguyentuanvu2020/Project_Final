package com.ivt.controller;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.AccountRoleEntity;
import com.ivt.entities.Cart;
import com.ivt.entities.ColorEntity;
import com.ivt.entities.CustomerEntity;
import com.ivt.entities.FavoriteEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.entities.SizeEntity;
import com.ivt.enums.Gender;
import com.ivt.service.AccountService;
import com.ivt.service.CategoryService;
import com.ivt.service.ImageService;
import com.ivt.service.MailService;
import com.ivt.service.OrderService;
import com.ivt.service.ColorService;
import com.ivt.service.FavoriteService;
import com.ivt.service.ProductDetailService;
import com.ivt.service.ProductService;
import com.ivt.service.ReviewService;
import com.ivt.service.SizeService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ColorService productColorService;

    @Autowired
    private SizeService productSizeService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MailService mailService;

    @Autowired
    private BCryptPasswordEncoder passwordEndcoder;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(Model model) {
        model.addAttribute("allProduct", productService.get10ProductNew());
        model.addAttribute("listFavorite", productService.get6ProductFavorite());
        model.addAttribute("listHot", productService.get10ProductHot());
        return "home";
    }

    @RequestMapping(value = {"/howtochoosesize"}, method = RequestMethod.GET)
    public String viewHome22() {
        return "howtochoosesize";
    }

    @RequestMapping("/check-out")
    public String viewCheckOut() {
        return "check-out";
    }

    @RequestMapping("/account")
    public String viewAccount(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
        } else {
            return "redirect:/login";
        }
        return "redirect:/user/account";
    }

    @RequestMapping("/login")
    public String viewLoginx(Model model,
            @RequestParam(value = "error", required = false) boolean isError) {
        model.addAttribute("action", "j_spring_security_check");
        if (isError) {
            model.addAttribute("message", "Wrong password");
            return "login";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            return "redirect:/user/account";
        }
        return "login";
    }

    @RequestMapping(value = {"/addproduct-detail"}, method = RequestMethod.GET)
    public String viewAddProduct(Model model, HttpSession session) {
        model.addAttribute("product-detail", new ProductDetailEntity());
        model.addAttribute("action", "add-more");
        List<ProductDetailEntity> list = new ArrayList<>();
        session.setAttribute("session", list);
        return "addproduct";
    }

    @RequestMapping(value = {"/register-account"}, method = RequestMethod.GET)
    public String viewAccountForm(Model model) {
        model.addAttribute("account", new AccountEntity());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("action", "save-account");
        return "account-form";

    }

    @RequestMapping(value = {"/save-account"}, method = RequestMethod.POST)
    public String Register(Model model, @ModelAttribute("account") AccountEntity account) {
        AccountRoleEntity accountRole = new AccountRoleEntity();
        accountRole.setId(1);
        List<AccountRoleEntity> listRole = new ArrayList<>();
        listRole.add(accountRole);
        account.setAccountRoles(listRole);
        account.setPassword(passwordEndcoder.encode(account.getPassword()));
        if (accountService.registerAccount(account)) {
            return "redirect:/login";
        } else {
            return "redirect:/register-account";
        }
    }

    @RequestMapping(value = {"/product-detail-view"}, method = RequestMethod.GET)
    public String viewProductDetail(Model model, @RequestParam("productId") int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            if (favoriteService.checkIsFavorite(productService.findProductById(id), accountService.findAccountById(((AccountEntity) principal).getId()))) {
                model.addAttribute("style", "#FF424F");
            } else {
                model.addAttribute("style", "none");
            }
        } else {
            model.addAttribute("style", "none");
        }
        ProductEntity product = productService.findAWholeProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("reviewTotal", reviewService.CountReviewByProductId(id));
        model.addAttribute("avgRate", reviewService.AvgRateReviewByProductId(id));
        model.addAttribute("favoriteTotal", favoriteService.favoriteTotalbyProductId(id));
        model.addAttribute("listColor", productColorService.findListColorByProductId(id));
        model.addAttribute("listSize", productSizeService.findListSizeByProductId(id));
        model.addAttribute("genders", Gender.values());
        model.addAttribute("listReview", reviewService.getAllReviewByProduct(product));
        return "product-detail-view";

    }

    @RequestMapping(value = {"/print-size-by-color"}, method = RequestMethod.GET)
    @ResponseBody
    public String viewProductDetail(Model model, @RequestParam("colorId") int colorId,
            @RequestParam("productId") int productId) {
        List<SizeEntity> listSize = productSizeService.findListSizeByProductIdAndColorId(productId, colorId);
        String responBody = "";
        for (int i = 0; i < listSize.size(); i++) {
            responBody += listSize.get(i).getId();
            if (i < listSize.size() - 1) {
                responBody += ",";
            }
        }
        responBody = "[" + responBody + "]";
        return responBody;

    }

    @RequestMapping(value = {"/print-color-by-size"}, method = RequestMethod.GET)
    @ResponseBody
    public String viewProductDetail1(Model model, @RequestParam("sizeId") int sizeId,
            @RequestParam("productId") int productId) {
        List<ColorEntity> listColor = productColorService.findListColorByProductIdAndSizeId(productId, sizeId);
        String responBody = "";
        for (int i = 0; i < listColor.size(); i++) {
            responBody += listColor.get(i).getId();
            if (i < listColor.size() - 1) {
                responBody += ",";
            }
        }
        responBody = "[" + responBody + "]";
        return responBody;

    }

    @RequestMapping(value = {"/buynow"}, method = RequestMethod.POST)
    public String viewCart(Model model, @RequestParam("colorId") int colorId,
            @RequestParam("sizeId") int sizeId,
            @RequestParam("productId") int productId, @RequestParam("quantity") int quantity,
            HttpSession session) {
        ProductDetailEntity newProductDetail = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        int count = 0;
        if (session.getAttribute("cart") == null) {
            Cart cart = new Cart();
            newProductDetail.setProductQuantity(quantity);
            if (newProductDetail.getProduct().getListPromotion().size() > 0) {
                newProductDetail.getProduct().setPrice(newProductDetail.getProduct().getPrice() - (newProductDetail.getProduct().getPrice() * newProductDetail.getProduct().getListPromotion().get(0).getDiscount() / 100));
            }
            cart.addProductDetail(newProductDetail);
            session.setAttribute("cart", cart);
            return "redirect:/cart";
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            int quantityInCart = cart.checkQuantity(newProductDetail.getId());
            if (quantityInCart + quantity > newProductDetail.getProductQuantity()) {
                return "redirect:/product-detail-view?productId=" + productId;
            } else {
                newProductDetail.setProductQuantity(quantity);
                if (newProductDetail.getProduct().getListPromotion().size() > 0) {
                    newProductDetail.getProduct().setPrice(newProductDetail.getProduct().getPrice() - (newProductDetail.getProduct().getPrice() * newProductDetail.getProduct().getListPromotion().get(0).getDiscount() / 100));
                }
                cart.addProductDetail(newProductDetail);
                session.setAttribute("cart", cart);
                return "redirect:/cart";
            }
        }

    }

    @RequestMapping(value = {"/add-to-cart"}, method = RequestMethod.GET)
    @ResponseBody
    public String addToCart(Model model, @RequestParam("colorId") int colorId,
            @RequestParam("sizeId") int sizeId,
            @RequestParam("productId") int productId, @RequestParam("quantity") int quantity, HttpSession session) {
        ProductDetailEntity newProductDetail = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        boolean check = (session.getAttribute("cart") == null);
        int count = 0;
        if (check) {
            Cart cart = new Cart();
            newProductDetail.setProductQuantity(quantity);
            if (newProductDetail.getProduct().getListPromotion().size() > 0) {
                newProductDetail.getProduct().setPrice(newProductDetail.getProduct().getPrice() - (newProductDetail.getProduct().getPrice() * newProductDetail.getProduct().getListPromotion().get(0).getDiscount() / 100));
            }
            cart.addProductDetail(newProductDetail);
            session.setAttribute("cart", cart);
            count = cart.getCount();
            return "" + count;
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            int quantityInCart = cart.checkQuantity(newProductDetail.getId());
            if ((quantityInCart + quantity) <= newProductDetail.getProductQuantity()) {
                newProductDetail.setProductQuantity(quantity);
                if (newProductDetail.getProduct().getListPromotion().size() > 0) {
                    newProductDetail.getProduct().setPrice(newProductDetail.getProduct().getPrice() - (newProductDetail.getProduct().getPrice() * newProductDetail.getProduct().getListPromotion().get(0).getDiscount() / 100));
                }
                cart.addProductDetail(newProductDetail);
                session.setAttribute("cart", cart);
                count = cart.getCount();
                return "" + count;
            } else {
                return "fail";
            }
        }
    }

//    @RequestMapping(value = {"/update-cart"}, method = RequestMethod.GET)
//    public String updateCart(HttpSession session, Model model) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        List<ProductEntity> allProduct = productService.getAllProduct();
//        model.addAttribute("listProduct", allProduct);
//        return "product-list";
//
//    }
    @RequestMapping(value = {"/delete-item"}, method = RequestMethod.GET)
    @ResponseBody
    public String viewCartx(HttpSession session, @RequestParam("id") int id) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getCart().size() == 1) {
            cart = new Cart();
        } else {
            cart.deleteProductDetail(id);
        }
        session.setAttribute("cart", cart);
        String responseBody = "[" + cart.getCount() + "," + cart.getTotal() + "]";
        return responseBody;
    }

    @RequestMapping(value = {"/create-order"}, method = RequestMethod.POST)
    public String insertOrder(HttpSession session, Model model,
            @ModelAttribute("customer") CustomerEntity customer, @RequestParam("note") String note) {
        Cart cart = (Cart) session.getAttribute("cart");
        OrderEntity newOrder = new OrderEntity();
        newOrder.setOrderDate(new Date());
        List<OrderDetailEntity> listOrderDetail = new ArrayList<>();
        List<ProductDetailEntity> listProductDetailStore = new ArrayList<>();
        for (ProductDetailEntity item : cart.getCart()) {
//            ProductEntity product = productService.findProductById(item.getProduct().getId());
            ProductDetailEntity productDetailStore = productService.findProductDetailById(item.getId());
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setProduct(item.getProduct());
            orderDetail.setOrder(newOrder);
            orderDetail.setQuantity(item.getProductQuantity());
            orderDetail.setUnitPrice(item.getProduct().getPrice());
            orderDetail.setPrice(item.getProductQuantity() * orderDetail.getUnitPrice());
            orderDetail.setColor(item.getColor().getProductColor());
            orderDetail.setSize(item.getProductSize().getProductSize());
            listOrderDetail.add(orderDetail);
            productDetailStore.setProductQuantity(productDetailStore.getProductQuantity() - item.getProductQuantity());
            listProductDetailStore.add(productDetailStore);
        }
        newOrder.setListOrderDetail(listOrderDetail);
        newOrder.setCustomer(customer);
        newOrder.setTotalPrice(cart.getTotal());
        newOrder.setNote(note);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            customer.setAccount((AccountEntity) principal);
        }
        try {
            orderService.AddOrder(newOrder, listProductDetailStore);
            session.removeAttribute("cart");
            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String viewCartAll(HttpSession session, Model model,
            @RequestParam(value = "error", required = false) boolean error,
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "style", required = false) String style) {
        model.addAttribute("date", new Date());
        model.addAttribute("message", message);
        model.addAttribute("style", style);
        if (session.getAttribute("cart") == null) {
            return "cart";
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            List<ProductDetailEntity> listProductDetail = new ArrayList<>();
            for (ProductDetailEntity item : cart.getCart()) {
                ProductDetailEntity productDetail = productDetailService.findProductDetailById(item.getId());
                listProductDetail.add(productDetail);
            }
            model.addAttribute("max", listProductDetail);
            return "cart";
        }

    }

    @RequestMapping(value = {"/update-quantity"}, method = RequestMethod.GET)
    @ResponseBody
    public String updateQuantity(HttpSession session, Model model, @RequestParam("id") int id,
            @RequestParam("quantity") int quantity) {
//        ProductDetailEntity productDetail = productDetailService.findProductDetailById(id);
        Cart cart = (Cart) session.getAttribute("cart");
        cart.updateQuantity(id, quantity);
        String responseBody = "[" + cart.getCount() + "," + cart.getTotal() + "]";
        return responseBody;

    }

    @RequestMapping(value = {"/check-out"}, method = RequestMethod.GET)
    public String viewCheckOut(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getCart().size() == 0 || cart == null) {
            return "redirect:/home";
        }
        for (ProductDetailEntity item : cart.getCart()) {
            if (item.getProductQuantity() > productDetailService.findProductDetailById(item.getId()).getProductQuantity()) {
                return "redirect:/cart?error=true&message=Quantity is not Correct!&style=alert alert-danger";
            }
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            String currentUserName = ((AccountEntity) principal).getName();
            model.addAttribute("customer", principal);
            model.addAttribute("check", "acc");
            model.addAttribute("action", "create-order");
            model.addAttribute("includeShipping", cart.getTotal() + 40000);

        } else {
            model.addAttribute("action", "create-order");
            model.addAttribute("customer", new CustomerEntity());
            model.addAttribute("includeShipping", cart.getTotal() + 40000);
            model.addAttribute("check", "cus");
            return "check-out";
        }
        return "check-out";

    }

    //////////////////////Check quantity
    @RequestMapping(value = {"/checkQuantity"}, method = RequestMethod.GET)
    @ResponseBody
    public String checkQuantity(Model model, @RequestParam("colorId") int colorId,
            @RequestParam("sizeId") int sizeId,
            @RequestParam("productId") int productId, HttpSession session) {

        ProductDetailEntity ProductDetail = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        return "" + ProductDetail.getProductQuantity();

    }

    @RequestMapping(value = {"/collection"}, method = RequestMethod.GET)
    public String viewCollection(Model model) {
        model.addAttribute("allProduct", productService.getAllProductAndImage());
        return "collection";

    }

    @RequestMapping(value = {"/template"}, method = RequestMethod.GET)
    public String viewTemplate(Model model) {
        model.addAttribute("allProduct", productService.getAllProductAndImage());
        return "template";

    }

    @RequestMapping(value = {"/template2"}, method = RequestMethod.GET)
    public String viewTemplate2(Model model) {
        model.addAttribute("allProduct", productService.getAllProductAndImage());
        return "management/seller/template2";

    }

//    addfavorite................................................
    @RequestMapping(value = {"/check-email-isavailable"}, method = RequestMethod.GET)
    @ResponseBody
    public String checkEmail(@RequestParam("email") String email) {
        if (accountService.isAvailable(email)) {
            return "true";
        } else {
            return "fasle";
        }

    }

    @RequestMapping(value = {"/add-favorite"}, method = RequestMethod.GET)
    @ResponseBody
    public String addFavorite(@RequestParam("productId") int productId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FavoriteEntity newFavorite = new FavoriteEntity();
        newFavorite.setAccount((AccountEntity) principal);
        newFavorite.setProduct(productService.findProductById(productId));
        favoriteService.addFavorite(newFavorite);
        int totalFavorite = favoriteService.favoriteTotalbyProductId(productId);
        String response = String.valueOf(totalFavorite);
        return response;
    }

    @RequestMapping(value = {"/remove-favorite"}, method = RequestMethod.GET)
    @ResponseBody
    public String removeFavorite(@RequestParam("productId") int productId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            favoriteService.deleteFavorite(productService.findProductById(productId), accountService.findAccountById(((AccountEntity) principal).getId()));
        }
        int totalFavorite = favoriteService.favoriteTotalbyProductId(productId);
        String response = String.valueOf(totalFavorite);
        return response;
    }

    //Xác thực quên mật khẩu page....................
    @RequestMapping(value = {"/forget-password"}, method = RequestMethod.GET)
    public String forgetPasswordView(Model model) {
        model.addAttribute("action", "verify-password");
        return "forget-password";
    }

    @RequestMapping(value = {"/verify-password"}, method = RequestMethod.POST)
    public String sendCode(Model model, @RequestParam("email") String email, HttpSession session) {
        if (accountService.isAvailable(email)) {
            Random rd = new Random();
            int code = 100000 + rd.nextInt(10000);
            String codeName = String.valueOf(code);
            session.setAttribute(codeName, email);
            try {
                mailService.sendCodeMailPage(email, code);
            } catch (Exception e) {
            }
            model.addAttribute("code", codeName);
        } else {
            return "redirect:/forget-password";
        }

        model.addAttribute("action", "check-code");

        return "check-code-password";
    }

    @RequestMapping(value = {"/check-code"}, method = RequestMethod.POST)
    public String checkCode(Model model, HttpSession session, @RequestParam("code") int code) {
        String email = (String) session.getAttribute(String.valueOf(code));
        model.addAttribute("email", email);
        model.addAttribute("action", "set-new-password");
        return "set-new-password";
    }
}
