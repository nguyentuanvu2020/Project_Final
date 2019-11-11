package com.ivt.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductDetailEntity> listProductDetail;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ImageProductEntity> listImageProductDetail;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetailEntity> listOrderDetail;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ReviewEntity> listReview;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<FavoriteEntity> listFavorite;

    @ManyToMany(mappedBy = "listProduct")
    private List<PromotionEntity> listPromotion;
            
    public ProductEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ProductDetailEntity> getListProductDetail() {
        return listProductDetail;
    }

    public void setListProductDetail(List<ProductDetailEntity> listProductDetail) {
        this.listProductDetail = listProductDetail;
    }

    public List<ImageProductEntity> getListImageProductDetail() {
        return listImageProductDetail;
    }

    public void setListImageProductDetail(List<ImageProductEntity> listImageProductDetail) {
        this.listImageProductDetail = listImageProductDetail;
    }

    public List<OrderDetailEntity> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetailEntity> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public List<ReviewEntity> getListReview() {
        return listReview;
    }

    public void setListReview(List<ReviewEntity> listReview) {
        this.listReview = listReview;
    }

    public List<FavoriteEntity> getListFavorite() {
        return listFavorite;
    }

    public void setListFavorite(List<FavoriteEntity> listFavorite) {
        this.listFavorite = listFavorite;
    }

    public List<PromotionEntity> getListPromotion() {
        return listPromotion;
    }
    public void setListPromotion(List<PromotionEntity> listPromotion) {
        this.listPromotion = listPromotion;
    }
}
