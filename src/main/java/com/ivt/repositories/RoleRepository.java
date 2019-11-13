/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.AccountRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author phand
 */
@Repository
public interface RoleRepository extends CrudRepository<AccountRoleEntity, Integer>{
    
//    public AccountRoleEntity findById(int id);
}
