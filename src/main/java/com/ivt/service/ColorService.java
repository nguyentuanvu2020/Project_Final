
package com.ivt.service;

import com.ivt.entities.ColorEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivt.repositories.ColorRepository;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;
    
    
    public List<ColorEntity> findListColorByProductId(int productId){
        return colorRepository.findListColorByProductId(productId);
    }
    
    public List<ColorEntity> findListColorByProductIdAndSizeId(int productId, int sizeId) {
        return (List<ColorEntity>) colorRepository.findListColorByProductIdAndSizeId(productId, sizeId);
    }
//    hiệp
//    @Autowired
//    private ColorRepository colorRepository;
    
    public List<ColorEntity> getAll(){
        return (List<ColorEntity>) colorRepository.findAll();
    }
    
    public ColorEntity getById(int id){
        return colorRepository.findOne(id);
    }
}
