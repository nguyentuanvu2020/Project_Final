/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.ColorEntity;
import com.ivt.entities.ImageProductEntity;
import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.entities.PromotionEntity;
import com.ivt.entities.SizeEntity;
import com.ivt.repositories.ProductDetailRepository;
import com.ivt.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivt.repositories.ColorRepository;
import com.ivt.repositories.ImageProductRepository;
import com.ivt.repositories.PromotionRepository;
import com.ivt.repositories.SizeRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ColorRepository productColorRepository;

    @Autowired
    private SizeRepository productSizeRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public List<ProductEntity> getAllProduct() {
        return (List<ProductEntity>) productRepository.findAll();
    }

    public List<ProductEntity> getAllProductAndImage() {
        List<ProductEntity> listProduct = productRepository.getAllProductAndImage();
        for (ProductEntity productEntity : listProduct) {
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductDetailEntity> findProductDetailByProduct(ProductEntity product) {
        return productDetailRepository.findByProduct(product);
    }

    public List<SizeEntity> getAllSize() {
        return (List<SizeEntity>) productSizeRepository.findAll();
    }

    public ProductEntity findProductById(int id) {
        return productRepository.findOne(id);
    }

    public void addProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public ProductEntity updateProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public ProductDetailEntity findProductDetailById(int id) {
        return productDetailRepository.findOne(id);
    }

    public ProductEntity findAWholeProductById(int id) {
        ProductEntity product = productRepository.findWholeProductById(id);
        product.setListPromotion(promotionRepository.findListPromotionByProductId(product.getId()));
        return product;
    }

    public ProductEntity findProductByOrderDetailId(int orderDetailId) {
        return productRepository.findProductByOrderDetailId(orderDetailId);
    }
    //code của hiệp
//    @Autowired
//    private ProductRepository productRepository;
//
    @Autowired
    private ImageProductRepository imageProductRepository;
//    @Autowired
//    private ProductDetailRepository productDetailRepository;

    public ProductEntity saveNewProduct(ProductEntity newProduct) {
        List<ImageProductEntity> listimage = newProduct.getListImageProductDetail();
        newProduct.setListImageProductDetail(null);
        newProduct.setListProductDetail(null);
        ProductEntity newAddpr = productRepository.save(newProduct);
        if (listimage != null && listimage.size() > 0) {
            for (ImageProductEntity imageEntity : listimage) {
                imageEntity.setProduct(newAddpr);
                imageProductRepository.save(imageEntity);
            }
        }
        return newAddpr;
    }

    public void saveDetail(ProductEntity newProduct) {
        List<ProductDetailEntity> listDetail = newProduct.getListProductDetail();
        for (ProductDetailEntity productDetailEntity : listDetail) {
            productDetailEntity.setProduct(newProduct);
            productDetailRepository.save(productDetailEntity);
        }
    }

    public List<ProductEntity> getAll() {
        return (List<ProductEntity>) productRepository.getAll();
    }

    public ProductEntity getById(int id) {
        return productRepository.findOne(id);
    }

    public List<ProductEntity> getByPromotionId(int promotionId) {
        return (List<ProductEntity>) productRepository.getByPromotionId(promotionId);
    }
    //Home Service

    public List<ProductEntity> get6ProductFavorite() {
        List<ProductEntity> listProductFavorite = productRepository.get6ProductFavorite();
        for (ProductEntity productEntity : listProductFavorite) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
        }
        return listProductFavorite;
    }

    public List<ProductEntity> get10ProductHot() {
        List<ProductEntity> listProductHot = productRepository.get10ProductHot();
        for (ProductEntity productEntity : listProductHot) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProductHot;
    }

    public List<ProductEntity> get10ProductNew() {
        List<ProductEntity> listProductNew = productRepository.get10ProductNew();
        for (ProductEntity productEntity : listProductNew) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProductNew;
    }

//    search service.............................
    public List<ProductEntity> searchProductHome(String search) {
        search = "%" + search + "%";
        List<ProductEntity> listProduct = productRepository.searchProductHome(search);
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByPriceAscending() {
        List<ProductEntity> listProduct = productRepository.findAllProductByPriceASC();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByPriceDescending() {
        List<ProductEntity> listProduct = productRepository.findAllProductByPriceDESC();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByNewAscending() {
        List<ProductEntity> listProduct = productRepository.findAllProductByNewASC();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByNewDescending() {
        List<ProductEntity> listProduct = productRepository.findAllProductByNewDESC();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByNameAscending() {
        List<ProductEntity> listProduct = productRepository.findAllProductByNameASC();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByNameDescending() {
        List<ProductEntity> listProduct = productRepository.findAllProductByNameDESC();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByHOtSell() {
        List<ProductEntity> listProduct = productRepository.getAllProductHot();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByHOTFavorite() {
        List<ProductEntity> listProduct = productRepository.getAllProductFavorite();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByCategoryName(String name) {
        List<ProductEntity> listProduct = productRepository.findAllProductByCategoryName(name);
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByColorId(int id) {
        List<ProductEntity> listProduct = productRepository.findAllProductByColorId(id);
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchBySizeId(int id) {
        List<ProductEntity> listProduct = productRepository.findAllProductBySizeId(id);
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> findAllFavoriteProductByAccountId(int id) {
        List<ProductEntity> listProduct = productRepository.findAllFavoriteProductByAccountId(id);
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByMoneyLower() {
        List<ProductEntity> listProduct = productRepository.searchByMoneyLowerFive();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByMoneyFromFiveToOne() {
        List<ProductEntity> listProduct = productRepository.searchByMoneyFromFiveToOne();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByMoneyFromOneToFive() {
        List<ProductEntity> listProduct = productRepository.searchByMoneyFromOneToFive();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByMoneyFromFiveToTwo() {
        List<ProductEntity> listProduct = productRepository.searchByMoneyFromFiveToTwo();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByMoneyMoreTwo() {
        List<ProductEntity> listProduct = productRepository.searchByMoneyMoreTwo();
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }

    public List<ProductEntity> searchByCategoryIdAJ(int[] id) {
        List<ProductEntity> listProduct;
        if (id.length == 1) {
            listProduct = productRepository.searchByCategoryIdAJ(id[0]);
        } else {
            listProduct = productRepository.searchByCategoryIdAJ2(id[0], id[1]);
        }
        for (ProductEntity productEntity : listProduct) {
            productEntity.setListImageProductDetail(imageProductRepository.findByProduct(productEntity));
            List<PromotionEntity> listPromotion = promotionRepository.findListPromotionByProductId(productEntity.getId());
            productEntity.setListPromotion(listPromotion);
        }
        return listProduct;
    }
}
