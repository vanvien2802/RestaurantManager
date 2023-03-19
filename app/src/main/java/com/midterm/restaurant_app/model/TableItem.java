package com.midterm.restaurant_app.model;

public class TableItem {
    private String nameTable;
    private boolean status;
    private int numServed;
    private int totalServe;

    public String getTotalCostFood() {
        return totalCostFood;
    }

    public void setTotalCostFood(String totalCostFood) {
        this.totalCostFood = totalCostFood;
    }

    private String totalCostFood;

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumServed() {
        return numServed;
    }

    public void setNumServed(int numServed) {
        this.numServed = numServed;
    }

    public int getTotalServe() {
        return totalServe;
    }

    public void setTotalServe(int totalServe) {
        this.totalServe = totalServe;
    }

    public TableItem() {
    }

    public TableItem(String nameTable, boolean status, int numServed, int totalServe, String total) {
        this.nameTable = nameTable;
        this.status = status;
        this.numServed = numServed;
        this.totalServe = totalServe;
        this.totalCostFood = total;
    }
}
