package com.midterm.restaurant_app.model;

public class Table {
    private String idTable;
    private String nameTable;
    private boolean statusTb;
    private int capacity;
    private int floor;

    public Table(){}

    public Table(String idTable, String nameTable, boolean statusTb, int capacity, int floor) {
        this.idTable = idTable;
        this.nameTable = nameTable;
        this.statusTb = statusTb;
        this.capacity = capacity;
        this.floor = floor;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public boolean isStatusTb() {
        return statusTb;
    }

    public void setStatusTb(boolean statusTb) {
        this.statusTb = statusTb;
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
