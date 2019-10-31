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
@Table(name = "product_size")
public class SizeEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productSize;

    @OneToMany(mappedBy = "productSize",fetch = FetchType.LAZY)
    List<ProductDetailEntity> listProductDetail;
    
    public SizeEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    public List<ProductDetailEntity> getListProductDetail() {
        return listProductDetail;
    }

    public void setListProductDetail(List<ProductDetailEntity> listProductDetail) {
        this.listProductDetail = listProductDetail;
    }

    
}
