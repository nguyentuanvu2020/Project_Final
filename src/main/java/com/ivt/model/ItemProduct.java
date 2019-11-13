package com.ivt.model;

import java.util.Objects;

public class ItemProduct {

    private int productID;
    private String name;
    private String color;
    private int Size;
    private int quantity;
    private double unitprice;
    private double price;

    public ItemProduct() {
    }

    public ItemProduct(int priductID, String name, String color, int Size, int quantity, double unitprice, double price) {
        this.productID = priductID;
        this.name = name;
        this.color = color;
        this.Size = Size;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int priductID) {
        this.productID = priductID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemProduct other = (ItemProduct) obj;
        if (this.productID != other.productID) {
            return false;
        }
        if (this.Size != other.Size) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }
    
}
