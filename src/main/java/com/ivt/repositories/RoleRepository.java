/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.AccountRoleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author phand
 */
@Repository
public interface RoleRepository extends CrudRepository<AccountRoleEntity, Integer> {

//    public AccountRoleEntity findById(int id);
    @Query(value = "select r.* from account_role r join acc_role_relationship rr "
            + "on r.id = rr.account_role_id "
            + "join project_final.account a "
            + "on rr.account_id = a.id where a.id = ?1", nativeQuery = true)
    List<AccountRoleEntity> getListRoleByAcountId(int Id);

}
