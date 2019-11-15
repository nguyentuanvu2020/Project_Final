package com.ivt.repositories;

import com.ivt.entities.ColorEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends CrudRepository<ColorEntity, Integer> {

    @Query(value = "select distinct pc.* from product_color pc join productdetails pd on pc.id = pd.color_id where pd.product_id  = ?1", nativeQuery = true)
    List<ColorEntity> findListColorByProductId(int productId);

    @Query(value = "select distinct pc.* from product_color pc join productdetails pd on pc.id = pd.color_id where pd.product_id  = ?1 and pd.size_id = ?2", nativeQuery = true)
    List<ColorEntity> findListColorByProductIdAndSizeId(int productId, int sizeId);

    ColorEntity findByProductColor(String color);
}
