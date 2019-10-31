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
import com.ivt.entities.SizeEntity;
import com.ivt.repositories.ProductDetailRepository;
import com.ivt.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivt.repositories.ColorRepository;
import com.ivt.repositories.ImageProductRepository;
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

    public List<ProductEntity> getAllProduct() {
        return (List<ProductEntity>) productRepository.findAll();
    }
    
    public List<ProductEntity> getAllProductAndImage() {
        return (List<ProductEntity>) productRepository.getAllProductAndImage();
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

    public ProductDetailEntity findProductDetailById(int id) {
        return productDetailRepository.findOne(id);
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
}