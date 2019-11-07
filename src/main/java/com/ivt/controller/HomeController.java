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
import com.ivt.service.SizeService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(Model model) {
        model.addAttribute("allProduct", productService.getAllProductAndImage());
        return "home";
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String viewHome2(Model model) {
        return "dashboard";
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
            model.addAttribute("message", "Tài khoản hoặc mật khẩu không đúng");
            return "login";
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
        try {
            AccountRoleEntity accountRole = new AccountRoleEntity();
            accountRole.setId(2);
            List<AccountRoleEntity> listRole = new ArrayList<>();
            listRole.add(accountRole);
            account.setAccountRoles(listRole);
            account.setPassword(passwordEndcoder.encode(account.getPassword()));
            if (accountService.isAvailable(account.getEmail())) {

                return "redirect:/account-form";
            }
            accountService.registerAccount(account);
        } catch (Exception e) {
        }

        return "redirect:/home";

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
        model.addAttribute("product", productService.findAWholeProductById(id));
        model.addAttribute("favoriteTotal", favoriteService.favoriteTotalbyProductId(id));
        model.addAttribute("listColor", productColorService.findListColorByProductId(id));
        model.addAttribute("listSize", productSizeService.findListSizeByProductId(id));
        model.addAttribute("genders", Gender.values());
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
        newProductDetail.setProductQuantity(quantity);
        boolean check = (session.getAttribute("cart") == null);

        if (check) {
            Cart cart = new Cart();
            cart.addProductDetail(newProductDetail);
            session.setAttribute("cart", cart);
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            cart.addProductDetail(newProductDetail);
            session.setAttribute("cart", cart);
        }
        return "cart";

    }

    @RequestMapping(value = {"/add-to-cart"}, method = RequestMethod.GET)
    @ResponseBody
    public String addToCart(Model model, @RequestParam("colorId") int colorId,
            @RequestParam("sizeId") int sizeId,
            @RequestParam("productId") int productId, @RequestParam("quantity") int quantity, HttpSession session) {

        ProductDetailEntity newProductDetail = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        newProductDetail.setProductQuantity(quantity);
        boolean check = (session.getAttribute("cart") == null);
        int count = 0;
        if (check) {
            Cart cart = new Cart();
            cart.addProductDetail(newProductDetail);
            session.setAttribute("cart", cart);
            count = cart.getCount();
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            cart.addProductDetail(newProductDetail);
            session.setAttribute("cart", cart);
            count = cart.getCount();
        }
        return "" + count;

    }

    @RequestMapping(value = {"/update-cart"}, method = RequestMethod.GET)
    public String updateCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<ProductEntity> allProduct = productService.getAllProduct();
        model.addAttribute("listProduct", allProduct);
        return "product-list";

    }

    @RequestMapping(value = {"/delete-item"}, method = RequestMethod.GET)
    public String viewCartx(HttpSession session, @RequestParam("id") int id) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getCart().size() == 1) {
            cart = new Cart();
        } else {
            cart.deleteProductDetail(id);
        }
        session.setAttribute("cart", cart);
        return "cart";

    }

    @RequestMapping(value = {"/create-order"}, method = RequestMethod.POST)
    public String insertOrder(HttpSession session, Model model,
            @ModelAttribute("customer") CustomerEntity customer, @RequestParam("note") String note) {
        Cart cart = (Cart) session.getAttribute("cart");
        OrderEntity newOrder = new OrderEntity();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null) {
            customer.setAccount((AccountEntity) principal);
        }
        newOrder.setOrderDate(new Date());
        List<OrderDetailEntity> listOrderDetail = new ArrayList<>();
        List<ProductDetailEntity> listProductDetailStore = new ArrayList<>();
        for (ProductDetailEntity item : cart.getCart()) {
            ProductEntity product = productService.findProductById(item.getProduct().getId());
            ProductDetailEntity productDetailStore = productService.findProductDetailById(item.getId());
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setProduct(product);
            orderDetail.setOrder(newOrder);
            orderDetail.setQuantity(item.getProductQuantity());
            orderDetail.setUnitPrice(product.getPrice());
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
        try {
            orderService.AddOrder(newOrder, listProductDetailStore);
            session.removeAttribute("cart");
            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String viewCartAll(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        return "cart";

    }

    @RequestMapping(value = {"/update-quantity"}, method = RequestMethod.GET)
    @ResponseBody
    public String updateQuantity(HttpSession session, Model model, @RequestParam("id") int id,
            @RequestParam("quantity") int quantity) {
        Cart cart = (Cart) session.getAttribute("cart");
        cart.updateQuantity(id, quantity);
        String responseBody = "[" + cart.getCount() + "," + cart.getTotal() + "]";
        return responseBody;

    }

    @RequestMapping(value = {"/check-out"}, method = RequestMethod.GET)
    public String viewCheckOut(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            String currentUserName = ((AccountEntity) principal).getName();
            // principal
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

        ProductDetailEntity newProductDetail = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        newProductDetail.getProductQuantity();
        return "" + newProductDetail.getProductQuantity();

    }

    @RequestMapping(value = {"/collection"}, method = RequestMethod.GET)
    public String viewCollection(Model model) {
        model.addAttribute("allProduct", productService.getAllProductAndImage());
        return "collection";

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
}
