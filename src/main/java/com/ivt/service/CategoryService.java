/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.CategoryEntity;
import com.ivt.repositories.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryEntity> getAllCategory(){
        return (List<CategoryEntity>) categoryRepository.findAll();
    }
    
//    hiệp
    
    public List<CategoryEntity> getAll(){
        return (List<CategoryEntity>) categoryRepository.findAll();   
    } 
}
