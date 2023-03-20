package com.midterm.restaurant_app.model;

public class foodItem {

    private String title;
    private String cost;
    private String details;
    private boolean status;

    public foodItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public foodItem(String title, String cost, String details, boolean status) {
        this.title = title;
        this.cost = cost;
        this.details = details;
        this.status = status;
    }
}
