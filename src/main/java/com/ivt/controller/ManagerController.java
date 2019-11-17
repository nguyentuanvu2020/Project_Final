/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.controller;

import com.ivt.entities.CategoryEntity;
import com.ivt.entities.ColorEntity;
import com.ivt.entities.ImageProductEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.entities.PromotionEntity;
import com.ivt.entities.SizeEntity;
import com.ivt.service.CategoryService;
import com.ivt.service.ColorService;
import com.ivt.service.ImageService;
import com.ivt.service.ProductDetailService;
import com.ivt.service.ProductService;
import com.ivt.service.PromotionService;
import com.ivt.service.SizeService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/management/manager")
public class ManagerController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ProductDetailService detailService;
    @Autowired
    private PromotionService promotionService;

    @RequestMapping(value = "/list-product", method = RequestMethod.GET)
    public String viewListProduct(Model model,@ModelAttribute(name = "info") String info) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("info", info);
        return "management/manager/list-product";
    }

    @RequestMapping(value = "/add-new-product", method = RequestMethod.GET)
    public String viewAddNewProduct(Model model, HttpSession session) {
        session.removeAttribute("listDetail");
        model.addAttribute("action", "management/manager/add-new-product");
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("categorys", categoryService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        model.addAttribute("colors", colorService.getAll());
        List<ProductDetailEntity> listDrtail = new ArrayList();
        session.setAttribute("listdetail", listDrtail);
        return "management/manager/add-new-product";
    }

    @RequestMapping(value = "/add-new-product", method = RequestMethod.POST)
    public String doAddProduct(Model model,
            @ModelAttribute("product") ProductEntity newProduct,
            @RequestParam("file") MultipartFile[] file,
            HttpServletRequest request, HttpSession session,RedirectAttributes ra) {
        newProduct.setStatus("YES");
        if ("".equals(newProduct.getName()) || "".equals(newProduct.getDescription()) || newProduct.getPrice() == 0) {
            model.addAttribute("thongbao", "nhap day du thong tin");
            return viewAddNewProduct(model, session);
        } else {

            LocalDateTime n = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

            List<ImageProductEntity> listImage = new ArrayList<ImageProductEntity>();
            List<ProductDetailEntity> listDetail = (List<ProductDetailEntity>) session.getAttribute("listdetail");
            ProductEntity newProductEntity = new ProductEntity();
            try {
                for (MultipartFile multipartFile : file) {
                    byte[] byteImage = multipartFile.getBytes();
                    ServletContext context = request.getServletContext();
                    String urlImage = context.getRealPath("/image");
                    int index = urlImage.indexOf("target");
                    String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
                    String filename = multipartFile.getOriginalFilename();
                    int duoifile = filename.indexOf(".");
                    String fileType = filename.substring(duoifile);
                    filename = filename.substring(0, duoifile);
                    String date = String.valueOf(System.currentTimeMillis());
                    filename =  filename + date + fileType;
                    listImage.add(new ImageProductEntity(filename));
                    newProduct.setListImageProductDetail(listImage);
                    newProductEntity = productService.saveNewProduct(newProduct);
                    Path path = Paths.get(pathFolder + filename);
                    Files.write(path, byteImage);
                }
                newProductEntity.setListProductDetail(listDetail);
                productService.saveDetail(newProductEntity);
                model.addAttribute("p", newProduct);
                ra.addAttribute("info", "New product have id: "+newProductEntity.getId());
                return "redirect:../../management/manager/list-product";
            } catch (IOException e) {
                System.out.println(e);
                model.addAttribute("info", "Erro!!!");
                return viewAddNewProduct(model,session);
            }
        }
    }

    @RequestMapping(value = "/add-detail", method = RequestMethod.GET)
    @ResponseBody
    public String addListDetail(HttpSession session,
            @RequestParam("color") int color,
            @RequestParam("size") int sizes,
            @RequestParam("quantity") int quantity) {
        List<ProductDetailEntity> listdetail = (List<ProductDetailEntity>) session.getAttribute("listdetail");
        ColorEntity colorEngEntity = colorService.getById(color);
        SizeEntity sizeEntity = sizeService.getById(sizes);
        ProductDetailEntity newDetail = new ProductDetailEntity(quantity, colorEngEntity, sizeEntity);
        //tét
        String returnText = "";
        int check = 1;
        for (ProductDetailEntity productDetailEntity : listdetail) {
            if (productDetailEntity.getColor().getId() == color && productDetailEntity.getProductSize().getId() == sizes) {
                returnText = "DeleteDetail";
                check = 0;
            }
        }
        if (check == 1) {
            listdetail.add(newDetail);
            String idHtml = "" + color + sizes + quantity + "";
            returnText = "<tr id ='" + idHtml + "'>\n"
                    + "<td>#</td>\n"
                    + "<td>" + colorEngEntity.getProductColor() + "</td>\n"
                    + "<td hidden id='tdcolor" + idHtml + "'>" + colorEngEntity.getId() + "</td>\n"
                    + "<td>" + sizeEntity.getProductSize() + "</td>\n"
                    + "<td hidden id='tdsize" + idHtml + "'>" + sizeEntity.getId() + "</td>\n"
                    + "<td id='tdquantity" + idHtml + "'>" + quantity + "</td>\n"
                    + "<td><button class=\"btn btn-danger btn-xs\" type=\"button\" onclick=\"deleteDetail(" + idHtml + ")\">Delete</button></td>\n"
                    + "</tr>";
        }
        session.setAttribute("listdetail", listdetail);
        return returnText;
    }

    @RequestMapping(value = "/delete-detail", method = RequestMethod.GET)
    @ResponseBody
    public String deleteDetail(HttpSession session,
            @RequestParam("color") int color,
            @RequestParam("size") int sizes) {
        List<ProductDetailEntity> listdetail = (List<ProductDetailEntity>) session.getAttribute("listdetail");
        for (ProductDetailEntity item : listdetail) {
            if (item.getProductSize().getId() == sizes && item.getColor().getId() == color) {
                listdetail.remove(item);
                break;
            }
        }
        StringBuilder text = new StringBuilder();
        for (ProductDetailEntity productDetailEntity : listdetail) {
            text.append(productDetailEntity.getColor().getProductColor()).append(productDetailEntity.getProductSize().getProductSize());
        }
        return text.toString();
    }

    @RequestMapping(value = "/update-product/{productid}", method = RequestMethod.GET)
    public String viewUpdateProduct(Model model,
            @PathVariable("productid") int productId,
            HttpSession session) {
        model.addAttribute("action", "management/manager/update-product");
        session.removeAttribute("listDetail");
        ProductEntity product = productService.getById(productId);
        List<ImageProductEntity> listImage = new ArrayList<>();
        listImage = imageService.getListImageByProducId(product);
        List<ProductDetailEntity> listDetail = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        listDetail = detailService.getListProductByProductId(product);
        session.setAttribute("images", imageNames);
        session.setAttribute("listDetail", listDetail);
        model.addAttribute("product", product);
        model.addAttribute("images", listImage);
        model.addAttribute("productDetails", listDetail);
        model.addAttribute("categorys", categoryService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        model.addAttribute("colors", colorService.getAll());
        return "management/manager/update-product";
    }

    @RequestMapping(value = "/update-product/add-more-detail", method = RequestMethod.GET)
    @ResponseBody
    public String addMoreDetail(@RequestParam("color") int color,
            @RequestParam("size") int sizes,
            @RequestParam("quantity") int quantity,
            HttpSession session) {
        List<ProductDetailEntity> listDetail = (List<ProductDetailEntity>) session.getAttribute("listDetail");
        ColorEntity colorEngEntity = colorService.getById(color);
        SizeEntity sizeEntity = sizeService.getById(sizes);
        ProductDetailEntity newDetail = new ProductDetailEntity(quantity, colorEngEntity, sizeEntity);
        int check = 1;
        String returnText = "";
        for (ProductDetailEntity productDetailEntity : listDetail) {
            if (productDetailEntity.getColor().getId() == color && productDetailEntity.getProductSize().getId() == sizes) {
                returnText = "DeleteDetail";
                check = 0;
            }
        }
        if (check == 1) {
            listDetail.add(newDetail);
            String idHtml = "" + color + sizes + quantity + "";
            returnText = "<tr id ='" + idHtml + "'>\n"
                    + "<td>#</td>\n"
                    + "<td>" + colorEngEntity.getProductColor() + "</td>\n"
                    + "<td hidden id='tdcolor" + idHtml + "'>" + colorEngEntity.getId() + "</td>\n"
                    + "<td>" + sizeEntity.getProductSize() + "</td>\n"
                    + "<td hidden id='tdsize" + idHtml + "'>" + sizeEntity.getId() + "</td>\n"
                    + "<td id='tdquantity" + idHtml + "'>" + quantity + "</td>\n"
                    + "<td><button class=\"btn btn-danger btn-xs\" type=\"button\" onclick=\"deleteMoreDetail(" + idHtml + ")\">Delete</button></td>\n"
                    + "</tr>";
        }
        session.setAttribute("listDetail", listDetail);
        return returnText;
    }

    @RequestMapping(value = "/update-product/delete-more-detail", method = RequestMethod.GET)
    @ResponseBody
    public String deleteMoreDetail(HttpSession session,
            @RequestParam("color") int color,
            @RequestParam("size") int sizes) {
        List<ProductDetailEntity> listDetail = (List<ProductDetailEntity>) session.getAttribute("listDetail");
        for (ProductDetailEntity item : listDetail) {
            if (item.getProductSize().getId() == sizes && item.getColor().getId() == color) {
                listDetail.remove(item);
                break;
            }
        }
        StringBuilder text = new StringBuilder();
        for (ProductDetailEntity productDetailEntity : listDetail) {
            text.append(productDetailEntity.getColor().getProductColor()).append(productDetailEntity.getProductSize().getProductSize());
        }
        session.setAttribute("listDetail", listDetail);
        return text.toString();
    }

    @RequestMapping(value = "/update-product/update-more-detail", method = RequestMethod.GET)
    @ResponseBody
    public String updateMoreDetail(HttpSession session,
            @RequestParam("color") String color,
            @RequestParam("size") int sizes,
            @RequestParam("quantity") int quantity) {
        List<ProductDetailEntity> listDetail = (List<ProductDetailEntity>) session.getAttribute("listDetail");
        for (ProductDetailEntity item : listDetail) {
            if (item.getProductSize().getProductSize() == sizes && item.getColor().getProductColor().equals(color)) {
                item.setProductQuantity(quantity);
            }
        }
        session.setAttribute("listDetail", listDetail);
        return "" + quantity;
    }

    @RequestMapping(value = "delete-image", method = RequestMethod.GET)
    @ResponseBody
    public String deleteImage(@RequestParam("id") int imageId,
            @RequestParam("name") String imageName,
            HttpServletRequest request) {
        List<String> imageNames = (List<String>) request.getSession().getAttribute("images");
        imageNames.add(imageName);
        request.getSession().setAttribute("images", imageNames);
        String image = "noneimage.jpg";
        ServletContext context = request.getServletContext();
        String url = request.getContextPath() + "/resources/image/" + image;
//        String urlImage = context.getRealPath("/image");
//        int index = urlImage.indexOf("target");
//        String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
//        String duongdan = pathFolder + "/" + imageName;
//        File file = new File(duongdan);
//        //String thongbao;
//        if (file.exists()) {
//            //thongbao = "co file nhe";
//            imageService.deleteImageById(imageId);
//            file.delete();
//        } else {
//            //thongbao = "khong co file nhe";
//        }
        return url;
    }

    @RequestMapping(value = "/update-product", method = RequestMethod.POST)
    public String doUpdateProduct(Model model,
            @ModelAttribute("product") ProductEntity newProduct,
            @RequestParam("file") MultipartFile[] files,
            HttpServletRequest request, HttpSession session) {

        newProduct.setStatus("YES");
        LocalDateTime n = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String date = String.valueOf(n.format(formatter));
        List<ImageProductEntity> listImage = new ArrayList<ImageProductEntity>();
        List<ProductDetailEntity> listDetail = (List<ProductDetailEntity>) session.getAttribute("listDetail");
        ProductEntity newProductEntity = new ProductEntity();
        List<String> listImageName = (List<String>) session.getAttribute("images");
        String thongbao = "";
        if (files != null && files.length > 0) {
            try {
                for (MultipartFile multipartFile : files) {
                    byte[] byteImage = multipartFile.getBytes();
                    ServletContext context = request.getServletContext();
                    String urlImage = context.getRealPath("/image");
                    int index = urlImage.indexOf("target");
                    String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
                    String filename = multipartFile.getOriginalFilename();
                    int duoifile = filename.indexOf(".");
                    String fileType = filename.substring(duoifile);
                    filename = filename.substring(0, duoifile);
                    filename = newProduct.getName() + filename + date + fileType;
                    listImage.add(new ImageProductEntity(filename));
                    newProduct.setListImageProductDetail(listImage);
                    newProductEntity = productService.saveNewProduct(newProduct);
                    Path path = Paths.get(pathFolder + filename);
                    Files.write(path, byteImage);
                }
                newProductEntity.setListProductDetail(listDetail);
                productService.saveDetail(newProductEntity);
                model.addAttribute("p", newProduct);
                for (String string : listImageName) {
                    ServletContext context = request.getServletContext();
                    String urlImage = context.getRealPath("/image");
                    int index = urlImage.indexOf("target");
                    String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
                    String duongdan = pathFolder + "/" + string;
                    File file = new File(duongdan);
                    if (file.exists()) {
                        file.delete();
                        List<ImageProductEntity> im = imageService.getByNameForDelete(string);
                        for (ImageProductEntity imageProductEntity : im) {
                            imageService.deleteImageById(imageProductEntity.getId());
                        }
                    } else {
                        thongbao = "duong dan sai va eo co file";
                    }
                }
                return viewListProduct(model,"");
            } catch (IOException e) {
                System.out.println(e);
                model.addAttribute("test", e);
                model.addAttribute("thongbao", "updated product have id: " + newProduct.getId());
                return viewListProduct(model,"");
            }
        } else {
            for (String string : listImageName) {
                ServletContext context = request.getServletContext();
                String urlImage = context.getRealPath("/image");
                int index = urlImage.indexOf("target");
                String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
                String duongdan = pathFolder + "/" + string;
                File file = new File(duongdan);
                if (file.exists()) {
                    file.delete();
                    List<ImageProductEntity> im = imageService.getByNameForDelete(string);
                    for (ImageProductEntity imageProductEntity : im) {
                        imageService.deleteImageById(imageProductEntity.getId());
                    }
                } else {
                    thongbao = "duong dan sai va eo co file";
                }
            }

            newProduct = productService.saveNewProduct(newProduct);
            if (listDetail != null) {
                newProduct.setListProductDetail(listDetail);
                productService.saveDetail(newProduct);
            }
            model.addAttribute("thongbao", "updated product have id: " + newProduct.getId());
            return viewListProduct(model,"");
        }
    }
// promotion

    @RequestMapping(value = "add-promotion", method = RequestMethod.GET)
    public String viewAddPromotion(Model model) {
        model.addAttribute("action", "management/manager/add-promotion");
        model.addAttribute("newPromotion", new PromotionEntity());
        return "management/manager/add-promotion";
    }

    @RequestMapping(value = "add-promotion", method = RequestMethod.POST)
    public String doAddPromotion(Model model,
            @ModelAttribute("newPromotion") PromotionEntity newPromotion,
            @RequestParam(name = "file") MultipartFile file,
            HttpServletRequest request) {
        if (newPromotion.getDiscount() <= 0
                || "".equals(newPromotion.getDescription())
                || "".equals(newPromotion.getStartDate())
                || "".equals(newPromotion.getEndDate())) {
            return "redirect:../../management/manager/add-promotion";
        }

        LocalDateTime n = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String date = String.valueOf(n.format(formatter));
        if (newPromotion.getId() > 0) {
            PromotionEntity promotion = promotionService.getPromotionById(newPromotion.getId());
            if (promotion.getListProduct().size() > 0) {
                newPromotion.setListProduct(promotion.getListProduct());
            }
        }

        if (newPromotion.getEndDate().after(newPromotion.getStartDate())) {
            try {
                if (file.isEmpty()) {
                    newPromotion.setImage(newPromotion.getImage());
                    newPromotion.setStatus("YES");
                    promotionService.saveNewPromotion(newPromotion);
                    return "redirect:../../management/manager/list-promotion";
                } else {
                    byte[] byteImage = file.getBytes();
                    ServletContext context = request.getServletContext();
                    String urlImage = context.getRealPath("/image");
                    int index = urlImage.indexOf("target");
                    String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
                    String filename = file.getOriginalFilename();
                    int duoifile = filename.indexOf(".");
                    String fileType = filename.substring(duoifile);
                    filename = filename.substring(0, duoifile);
                    filename = newPromotion.getDiscount() + filename + date + fileType;
                    Path path = Paths.get(pathFolder + filename);
                    Files.write(path, byteImage);
                    newPromotion.setImage(filename);
                    newPromotion.setStatus("YES");
                    promotionService.saveNewPromotion(newPromotion);
                    return "redirect:../../management/manager/list-promotion";
                }
            } catch (IOException e) {
                return "redirect:../../management/manager/add-promotion";
            }
        } else {
            return "redirect:../../management/manager/add-promotion";
        }
    }
// phần update và thêm sản phẩm vào promotion

    @RequestMapping("/list-promotion")
    public String viewListPromotion(Model model) {
        model.addAttribute("promotions", promotionService.getAll());
        return "management/manager/list-promotion";
    }

    @RequestMapping(value = "/add-product-promotion/{prmid}", method = RequestMethod.GET)
    public String viewAddProductPromotion(Model model, @PathVariable("prmid") int promotionId) {
        model.addAttribute("action", "management/manager/update-product-promotion");
        PromotionEntity promotion = promotionService.getPromotionById(promotionId);
        model.addAttribute("promotion", promotion);
        model.addAttribute("products", productService.getAll());
        return "management/manager/promotion-product";
    }

    // 
    @RequestMapping(value = "/update-product-promotion", method = RequestMethod.POST)
    public String doAddProductPromotion(Model model,
            @ModelAttribute("promotionid") int promotionId,
            HttpServletRequest request, @RequestParam(name = "productId", defaultValue = "0") int[] product) {
        PromotionEntity p = promotionService.getPromotionById(promotionId);
        List<ProductEntity> lpr = new ArrayList<>();
        if (product.length != 0) {
            for (int i : product) {
                ProductEntity prd = productService.getById(i);
                lpr.add(prd);
            }
            p.setListProduct(lpr);
        } else {
            p.setListProduct(lpr);
        }
        promotionService.saveNewPromotion(p);
        return "redirect:../../management/manager/list-promotion";
    }

    // update promotion
    @RequestMapping(value = "/update-promotion/{prmid}", method = RequestMethod.GET)
    public String viewUpdateProductPromotion(Model model, @PathVariable("prmid") int promotionId) {
        model.addAttribute("action", "management/manager/add-promotion");
        model.addAttribute("check", "edit");
        PromotionEntity promotion = promotionService.getPromotionById(promotionId);
        model.addAttribute("newPromotion", promotion);
        model.addAttribute("products", productService.getAll());
        return "management/manager/update-promotion";
    }

    // update promotion
    @RequestMapping(value = "/detail-promotion/{prmid}", method = RequestMethod.GET)
    public String viewDetailProductPromotion(Model model, @PathVariable("prmid") int promotionId) {
        PromotionEntity promotion = promotionService.getPromotionById(promotionId);
        model.addAttribute("promotion", promotion);
        return "management/manager/detail-promotion";
    }

    // add new category
    @RequestMapping(value = "/add-new-category", method = RequestMethod.GET)
    public String viewAddNewCategory(Model model) {
        model.addAttribute("action", "management/manager/add-category");
        model.addAttribute("category", new CategoryEntity());
        return "management/manager/add-new-category";
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public String viewAddNewCategory2(Model model, @ModelAttribute("caregory") CategoryEntity newCategory) {
        if ("".equals(newCategory.getName()) || "".equals(newCategory.getDescription())) {
            model.addAttribute("message", "please enter full info!!!");
            return viewAddNewCategory(model);
        } else {
            try {
                CategoryEntity a = categoryService.saveCategory(newCategory);
                int check = a.getId();
                if (check > 0) {
                    model.addAttribute("category", "New category have id: " + a.getId());
                    return viewListCategory(model);
                } else {
                    throw new Exception("Cant save new category!!!");
                }
            } catch (Exception e) {
                model.addAttribute("message", "Error please check category name!!!");
                model.addAttribute("style", "alert alert-danger");
                return "management/manager/add-new-category";
            }
        }
    }

    // update category
    @RequestMapping(value = "/edit-category/{cateId}", method = RequestMethod.GET)
    public String viewUpdateNewCategory(Model model, @PathVariable("cateId") int categoryId) {
        model.addAttribute("action", "management/manager/edit-category");
        CategoryEntity editCategoy = categoryService.getById(categoryId);
        model.addAttribute("category", editCategoy);
        return "management/manager/update-category";
    }

    @RequestMapping(value = "/edit-category", method = RequestMethod.POST)
    public String updateCategory(Model model, @ModelAttribute("caregory") CategoryEntity newCategory) {
        try {
            CategoryEntity a = categoryService.saveCategory(newCategory);
            int check = a.getId();
            if (check > 0) {
                model.addAttribute("category", "Update category have id: " + a.getId());
                return viewListCategory(model);
            } else {
                throw new Exception("Cant save new category!!!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error please check category name!!!");
            model.addAttribute("style", "alert alert-danger");
            return "management/manager/add-new-category";
        }
    }

    // list category
    @RequestMapping(value = "/list-category", method = RequestMethod.GET)
    public String viewListCategory(Model model) {
        model.addAttribute("caregorys", categoryService.getAll());
        return "management/manager/list-category";
    }

    // update status off product
    @RequestMapping(value = "/update-status/{productId}", method = RequestMethod.GET)
    public String updateStatusProduct(Model model, @PathVariable("productId") int productId) {
        ProductEntity product = productService.findProductById(productId);
        if (product.getId() > 0) {
            if (product.getStatus().equalsIgnoreCase("YES")) {
                product.setStatus("NO");
            } else {
                product.setStatus("YES");
            }
            product = productService.updateProduct(product);
            model.addAttribute("thongbao", "Update status of product have id :" + product.getId());

            return viewListProduct(model,"");
        } else {
            model.addAttribute("thongbao", "Not find!!!");
            return viewListProduct(model,"");
        }

    }

    // update status off promotion
    @RequestMapping(value = "/update-status-promotion/{promotionId}", method = RequestMethod.GET)
    public String updateStatusPromotion(Model model, @PathVariable("promotionId") int promotionId) {
        PromotionEntity promotion = promotionService.getPromotionById(promotionId);
        if (promotion.getId() > 0) {
            if (promotion.getStatus().equalsIgnoreCase("YES")) {
                promotion.setStatus("NO");
            } else {
                promotion.setStatus("YES");
            }
            promotion = promotionService.updatePromotion(promotion);
            model.addAttribute("thongbao", "Update status of promotion have id :" + promotion.getId());
            return viewListPromotion(model);
        } else {
            model.addAttribute("thongbao", "Not find!!!");
            return viewListPromotion(model);
        }

    }
}
