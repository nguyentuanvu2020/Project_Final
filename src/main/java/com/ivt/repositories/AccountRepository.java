
package com.ivt.repositories;

import com.ivt.entities.AccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends
        CrudRepository<AccountEntity, Integer> {

    AccountEntity findByEmailLikeAndPasswordLike(
            String email, String password);

    @Query("Select acc From AccountEntity acc "
            + "Join fetch acc.accountRoles "
            + "Where acc.email Like ?1 and "
            + "acc.password Like ?2")
    AccountEntity findAccountByEmailAndPassword(
            String email, String password);
    
    @Query("Select acc From AccountEntity acc "
            + "Join fetch acc.accountRoles "
            + "Where acc.email = ?1 ")
    AccountEntity findByEmail(String email);
    
    @Query("Select acc.email From AccountEntity acc Where acc.email = ?1 ")
    List<AccountEntity> findByEmail2(String email);
   
    @Query("SELECT distinct a FROM AccountEntity a JOIN FETCH a.accountRoles ar")
    List<AccountEntity> getAllRole();
}
