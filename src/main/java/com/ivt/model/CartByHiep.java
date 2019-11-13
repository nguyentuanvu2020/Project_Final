package com.ivt.model;

import java.util.ArrayList;
import java.util.List;

public class CartByHiep {

    public List<ItemProduct> prodcuts;

    public CartByHiep() {
        prodcuts = new ArrayList<>();
    }

    public void themMonHang(ItemProduct itemmoi) {
        if (prodcuts.contains(itemmoi)) {
            ItemProduct hangmoi = prodcuts.get(prodcuts.indexOf(itemmoi));
            hangmoi.setQuantity(hangmoi.getQuantity() + itemmoi.getQuantity());
        } else {
            prodcuts.add(itemmoi);
        }
    }

    public void capnhatMonHang(ItemProduct mhmoi) {
        if (prodcuts.contains(mhmoi)) {
            ItemProduct hangmoi = prodcuts.get(prodcuts.indexOf(mhmoi));
            hangmoi.setQuantity(mhmoi.getQuantity());
        } else {
            prodcuts.add(mhmoi);
        }
    }

    public boolean xoaMonHang(int mmh) {
        for (int i = 0; i < prodcuts.size(); i++) {
            if (mmh == prodcuts.get(i).getProductID()) {
                prodcuts.remove(prodcuts.get(i));
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Giohang{" + "giohang=" + prodcuts + '}';
    }
// tính tiền khuyến mãi

    public int tinhKhuyenMai() {
        int khuyenmai = 0;
        for (ItemProduct monhang : prodcuts) {
           
        }
        return khuyenmai;
    }
// tính tổng tiền

    public double tinhTongtien() {
        double tongtien = 0;
        for (ItemProduct monhang : prodcuts) {
            tongtien = tongtien + monhang.getPrice()* monhang.getQuantity();
        }
        return tongtien;
    }

    public List<ItemProduct> getGH() {
        return prodcuts;
    }
    
    public static void main(String[] args) {
        CartByHiep cart =  new CartByHiep();
        ItemProduct item = new ItemProduct(0, "hiep", "hiep", 0, 0, 0, 0);
        ItemProduct item2 = new ItemProduct(1, "vu", "hiep", 0, 0, 0, 0);
        cart.themMonHang(item);
        cart.themMonHang(item2);
        for (ItemProduct prodcut : cart.prodcuts) {
            System.out.println(prodcut.getName());
        }
    }
}
