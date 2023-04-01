package com.midterm.restaurant_app.model;

public class FoodItem {

    private String name;
    private String price;
    private String details;
    private boolean status;
    private String category;
    private float rate;

    public FoodItem() {
    }

    public FoodItem(String name, String price, String details, boolean status, String category, float rate) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.status = status;
        this.category = category;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
