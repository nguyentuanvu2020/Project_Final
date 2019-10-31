package com.ivt.entities;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productdetails")
public class ProductDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;
    
    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeEntity productSize;
    
    public ProductDetailEntity() {
    }

    public ProductDetailEntity(int productQuantity, ColorEntity color, SizeEntity productSize) {
        this.productQuantity = productQuantity;
        this.color = color;
        this.productSize = productSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ColorEntity getColor() {
        return color;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }

    public SizeEntity getProductSize() {
        return productSize;
    }

    public void setProductSize(SizeEntity productSize) {
        this.productSize = productSize;
    }
    
    
   
    
}
