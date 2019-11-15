package com.ivt.repositories;

import com.ivt.entities.PromotionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<PromotionEntity, Integer> {

    @Query(value = "select * from promotions pr join product_promotion_relationship rl \n"
            + "on pr.id = rl.promotion_id\n"
            + "join products p \n"
            + "on rl.product_id = p.id \n"
            + "where p.id = ?1 and pr.startDate <= curdate() and  curdate() <= pr.endDate", nativeQuery = true)
    List<PromotionEntity> findListPromotionByProductId(int productId);
}
