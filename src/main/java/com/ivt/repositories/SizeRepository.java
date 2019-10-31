/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.repositories;

import com.ivt.entities.ColorEntity;
import com.ivt.entities.SizeEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Integer> {

    @Query(value = "select distinct pz.* from product_size pz join productdetails pd on pz.id = pd.size_id where product_id = ?1 order by pz.id", nativeQuery = true)
    List<SizeEntity> findListSizeByProductId(int productId);

    @Query(value = "select distinct pz.* from product_size pz join productdetails pd on pz.id = pd.size_id where pd.product_id = ?1 and pd.color_id = ?2 order by pz.id", nativeQuery = true)
    List<SizeEntity> findListSizeByProductIdAndColorId(int productId, int colorId);
}
