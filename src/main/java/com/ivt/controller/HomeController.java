package com.ivt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivt.entities.AccountEntity;
import com.ivt.entities.AccountRoleEntity;
import com.ivt.entities.Cart;
import com.ivt.entities.ColorEntity;
import com.ivt.entities.CustomerEntity;
import com.ivt.entities.ImageProductEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.entities.SizeEntity;
import com.ivt.enums.Gender;
import com.ivt.enums.ProductStatus;
import com.ivt.service.AccountService;
import com.ivt.service.CategoryService;
import com.ivt.service.ImageService;
import com.ivt.service.MailService;
import com.ivt.service.OrderService;
import com.ivt.service.ColorService;
import com.ivt.service.ProductDetailService;
import com.ivt.service.ProductService;
import com.ivt.service.SizeService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(Model model) {
        model.addAttribute("allProduct", productService.getAllProductAndImage());
        return "home";
    }

    @RequestMapping(value = {"/categoryz"}, method = RequestMethod.GET)
    public String viewHome2(Model model) {
        return "collections";
    }

    @RequestMapping(value = {"/howtochoosesize"}, method = RequestMethod.GET)
    public String viewHome22() {
        return "howtochoosesize";
    }

    @RequestMapping("/check-out")
    public String viewCheckOut() {
        return "check-out";
    }

    @RequestMapping("/login")
    public String viewLoginx(Model model,
            @RequestParam(value = "error", required = false) boolean isError) {
        model.addAttribute("action", "j_spring_security_check");
        if (isError) {
            model.addAttribute("message", "Tài khoản hoặc mật khẩu không đúng");
            return "account-login";
        }
        return "account-login";
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
            accountRole.setId(1);
            List<AccountRoleEntity> listRole = new ArrayList<>();
            listRole.add(accountRole);
            account.setAccountRoles(listRole);
            account.setPassword(passwordEndcoder.encode(account.getPassword()));
            accountService.registerAccount(account);
        } catch (Exception e) {
        }

        return "redirect:/home";

    }

    @RequestMapping(value = {"/product-detail-view"}, method = RequestMethod.GET)
    public String viewProductDetail(Model model, @RequestParam("productId") int id) {
        model.addAttribute("product", productService.findAWholeProductById(id));
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
            @RequestParam("productId") int productId, HttpSession session) {
        ProductDetailEntity newProductDetail = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        newProductDetail.setProductQuantity(1);
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
            @RequestParam("productId") int productId, HttpSession session) {

        ProductDetailEntity newProductDetail = productDetailService.findProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        newProductDetail.setProductQuantity(1);
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
            OrderEntity orderSaved = orderService.AddOrder(newOrder, listProductDetailStore);
            mailService.sendEmail(orderSaved);
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
        model.addAttribute("action", "create-order");
        model.addAttribute("customer", new CustomerEntity());
        model.addAttribute("includeShipping", cart.getTotal() + 40000);
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
}
