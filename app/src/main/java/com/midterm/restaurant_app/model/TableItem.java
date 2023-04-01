package com.midterm.restaurant_app.model;

public class TableItem {
    private String name;
    private boolean status;
    private int capacity;
    private int floor;

    public TableItem(){}

    public TableItem(String name, boolean status, int capacity, int floor) {
        this.name = name;
        this.status = status;
        this.capacity = capacity;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
