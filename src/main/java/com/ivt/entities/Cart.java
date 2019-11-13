package com.ivt.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private double total;
    private int count;
    private List<ProductDetailEntity> cart;

    public Cart() {
        count = 0;
        total = 0;
        cart = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ProductDetailEntity> getCart() {
        return cart;
    }

    public void setCart(List<ProductDetailEntity> cart) {
        this.cart = cart;
    }

    public void addProductDetail(ProductDetailEntity productDetail) {
        double a = 0;
        int b = 0;
        int check = 0;
        for (ProductDetailEntity item : cart) {
            if (item.getId() == productDetail.getId()) {
                item.setProductQuantity(item.getProductQuantity() + productDetail.getProductQuantity());
                check = 1;
            }
        }
        if (check == 0) {
            cart.add(productDetail);
        }

        for (ProductDetailEntity item : cart) {
            a += (item.getProductQuantity() * item.getProduct().getPrice());
            b += item.getProductQuantity();

        }
        total = a;
        count = b;
    }

    public void deleteProductDetail(int id) {
        double a = 0;
        int b = 0;
        for (ProductDetailEntity item : cart) {
            if (item.getId() == id) {
                cart.remove(item);
                break;
            }
        }
        for (ProductDetailEntity item : cart) {
            a += (item.getProductQuantity() * item.getProduct().getPrice());
            b += (item.getProductQuantity() * 1);
        }
        total = a;
        count = b;
    }

    public void updateQuantity(int id, int quantity) {
        double a = 0;
        int b = 0;
        for (ProductDetailEntity item : cart) {
            if (item.getId() == id) {
                item.setProductQuantity(quantity);
                break;
            }
        }
        for (ProductDetailEntity item : cart) {
            a += (item.getProductQuantity() * item.getProduct().getPrice());
            b += (item.getProductQuantity() * 1);
        }
        total = a;
        count = b;
    }

    public int checkQuantity(int ProductDetailId) {
        for (ProductDetailEntity item : cart) {
            if (ProductDetailId == item.getId()) {
                return item.getProductQuantity();
            }
        }
        return 0;
    }

    public int countItem(ProductDetailEntity itemOut) {
        int countI = 0;
        for (ProductDetailEntity itemIn : cart) {
            if (itemIn.getProduct().getId() == itemOut.getProduct().getId()) {
                countI++;
            }
        }
        return countI;
    }
}
