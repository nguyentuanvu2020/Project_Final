package com.ivt.service;

import com.ivt.entities.ProductEntity;
import com.ivt.entities.ReviewEntity;
import com.ivt.repositories.ReviewRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(ReviewEntity review) {
        review.setDateReview(new Date());
        reviewRepository.save(review);
    }

    public List<ReviewEntity> getAllReviewByProduct(ProductEntity product) {
        return reviewRepository.findByProduct(product);
    }

    public int CountReviewByProductId(int productId) {
        return reviewRepository.CountReviewByProductId(productId);
    }

    public double AvgRateReviewByProductId(int productId) {
        try {
            double avg = reviewRepository.AvgRateReviewByProductId(productId);
            return avg;
        } catch (Exception e) {
            return 0;
        }

    }
}
