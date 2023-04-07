package com.midterm.restaurant_app.model;

public class DetailOrder {
    private String idDetailOrder;
    private String idFood;
    private int quantity;

    public DetailOrder() {
    }

    public DetailOrder(String idDetailOrder, String idBillOrder, String idFood, int quantity) {
        this.idDetailOrder = idDetailOrder;
        this.idBillOrder = idBillOrder;
        this.idFood = idFood;
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

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
