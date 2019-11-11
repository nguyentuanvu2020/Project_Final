package com.ivt.service;

import com.ivt.entities.PromotionEntity;
import com.ivt.repositories.PromotionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author phand
 */
@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ProductService productService;

    public void saveNewPromotion(PromotionEntity newPromotionEntity) {
        promotionRepository.save(newPromotionEntity);
    }

    public List<PromotionEntity> getAll() {
        return (List<PromotionEntity>) promotionRepository.findAll();
    }

    public PromotionEntity getPromotionById(int id) {
        PromotionEntity promotion = promotionRepository.findOne(id);
        promotion.setListProduct(productService.getByPromotionId(promotion.getId()));
        return promotion;
    }

    public List<PromotionEntity> findListPromotionByProductId(int productId) {
        return promotionRepository.findListPromotionByProductId(productId);
    }

}
