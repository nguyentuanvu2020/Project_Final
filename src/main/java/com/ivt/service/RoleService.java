/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.AccountRoleEntity;
import com.ivt.repositories.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<AccountRoleEntity> getAllRole(){
        return (List<AccountRoleEntity>) roleRepository.findAll();
    }
    
    public AccountRoleEntity getRoleById(int id){
        return roleRepository.findOne(id);
    }
}
