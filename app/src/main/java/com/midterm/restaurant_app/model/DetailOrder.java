package com.midterm.restaurant_app.model;

import java.util.List;

public class DetailOrder {
    private String idDetailOrder;
    private String idBillOrder;
    private String idProduct;
    private int quantity;

    public DetailOrder() {
    }

    public DetailOrder(String idDetailOrder, String idBillOrder, String idProduct, int quantity) {
        this.idDetailOrder = idDetailOrder;
        this.idBillOrder = idBillOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public String getIdDetailOrder() {
        return idDetailOrder;
    }

    public void setIdDetailOrder(String idDetailOrder) {
        this.idDetailOrder = idDetailOrder;
    }

    public String getIdBillOrder() {
        return idBillOrder;
    }

    public void setIdBillOrder(String idBillOrder) {
        this.idBillOrder = idBillOrder;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
