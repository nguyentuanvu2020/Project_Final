/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.SizeEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivt.repositories.SizeRepository;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    public List<SizeEntity> findListSizeByProductId(int productId) {
        return (List<SizeEntity>) sizeRepository.findListSizeByProductId(productId);
    }

    public List<SizeEntity> findListSizeByProductIdAndColorId(int productId, int colorId) {
        return (List<SizeEntity>) sizeRepository.findListSizeByProductIdAndColorId(productId, colorId);
    }
    
    //code của hiệp
    
    public List<SizeEntity> getAll(){
        return (List<SizeEntity>) sizeRepository.findAll();
    }
    public SizeEntity getById(int id){
        return sizeRepository.findOne(id);
    }
    
    public SizeEntity getBySize(int size){
        return sizeRepository.findByProductSize(size);
    }
}
