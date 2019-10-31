
package com.ivt.service;

import com.ivt.entities.ImageProductEntity;
import com.ivt.entities.ProductEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivt.repositories.ImageProductRepository;

@Service
public class ImageService {

    @Autowired
    private ImageProductRepository imageProductRepository;

    public List<ImageProductEntity> getImageByProduct(ProductEntity product) {
        return imageProductRepository.findByProduct(product);
    }

    public List<ImageProductEntity> getImageByProductId(int productId) {
        return imageProductRepository.findByProductId(productId);
    }
    
//    code của hiệp
//    @Autowired
//    private ImageProductRepository imageProductRepository;

    public List<ImageProductEntity> getListImageByProducId(ProductEntity entity) {
        return (List<ImageProductEntity>) imageProductRepository.findByProduct(entity);
    }
    
    public void deleteImageById(int id){
        imageProductRepository.delete(id);
    }
    
    public void deleteImageByName(String name){
        imageProductRepository.deleteByName(name);
    }
    
    public List<ImageProductEntity> getByNameForDelete(String name){
        return imageProductRepository.findByName(name);
    }
}
