package com.midterm.restaurant_app.model;

public class OrderDetailItem {
    private String food_id;
    private int quantity;
    private double total_price;
    private double discount_percent;
    private String note;

    public OrderDetailItem(){}

    public OrderDetailItem(String food_id, int quantity, double total_price, double discount_percent, String note) {
        this.food_id = food_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.discount_percent = discount_percent;
        this.note = note;
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
