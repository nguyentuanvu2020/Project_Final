/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_color")
public class ColorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productColor;
    
    @OneToMany(mappedBy = "color",fetch = FetchType.LAZY)
    List<ProductDetailEntity> listProductDetail;

    public ColorEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public List<ProductDetailEntity> getListProductDetail() {
        return listProductDetail;
    }

    public void setListProductDetail(List<ProductDetailEntity> listProductDetail) {
        this.listProductDetail = listProductDetail;
    }
    

}
