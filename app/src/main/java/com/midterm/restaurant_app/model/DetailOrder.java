package com.midterm.restaurant_app.model;

public class DetailOrder {
    private String idDOrd;
    private String idBillOrder;
    private String idFood;
    private int quantity;

    public DetailOrder() {
    }

    public DetailOrder(String idDOrd, String idBillOrder, String idFood, int quantity) {
        this.idDOrd = idDOrd;
        this.idBillOrder = idBillOrder;
        this.idFood = idFood;
        this.quantity = quantity;
    }

    public String getIdDOrd() {
        return idDOrd;
    }

    public void setIdDOrd(String idDOrd) {
        this.idDOrd = idDOrd;
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
