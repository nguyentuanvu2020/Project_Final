
package com.ivt.repositories;

import com.ivt.entities.PromotionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<PromotionEntity, Integer>{
    
}
