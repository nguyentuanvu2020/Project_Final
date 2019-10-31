/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.ProductDetailEntity;
import com.ivt.entities.ProductEntity;
import com.ivt.repositories.ProductDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    
    public List<ProductDetailEntity> getListProductDetailByProductIdAndColorId(int productId,int colorId){
        return productDetailRepository.getProductDetailByProductIdAndColorId(productId, colorId);
    }
    
    public ProductDetailEntity findProductDetailByProductIdAndColorIdAndSizeId(int productId, int colorId, int sizeId){
        return productDetailRepository.getProductDetailByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
    }
    
//    code của hiệp
//    @Autowired
//    private ProductDetailRepository productDetailRepository;
    
    public List<ProductDetailEntity> getListProductByProductId(ProductEntity productId){
        return (List<ProductDetailEntity>)productDetailRepository.findByProduct(productId);
    }
}
