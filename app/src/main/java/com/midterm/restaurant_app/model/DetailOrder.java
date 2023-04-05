package com.midterm.restaurant_app.model;

public class DetailOrder {
    private String idDetailOrder;
    private String idProduct;
    private int quantity;
    private boolean status;

    public DetailOrder(){}

    public DetailOrder(String idDetailOrder, String idProduct, int quantity, boolean status) {
        this.idDetailOrder = idDetailOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.status = status;
    }

    public String getIdDetailOrder() {
        return idDetailOrder;
    }

    public void setIdDetailOrder(String idDetailOrder) {
        this.idDetailOrder = idDetailOrder;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
