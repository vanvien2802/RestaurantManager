package com.midterm.restaurant_app.model;

public class Table {
    private String idTable;
    private String nameTable;
    private String statusTB;
    private String status_rq;
    private String time_rq;
    private int capacity;
    private int floor;

    public Table(String idTable, String nameTable, String statusTb, String status_rq, String time_rq, int capacity, int floor) {
        this.idTable = idTable;
        this.nameTable = nameTable;
        this.statusTB = statusTb;
        this.status_rq = status_rq;
        this.time_rq = time_rq;
        this.capacity = capacity;
        this.floor = floor;
    }

    public Table() {
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

    public String getStatusTb() {
        return statusTB;
    }

    public void setStatusTb(String statusTb) {
        this.statusTB = statusTb;
    }

    public String getStatus_rq() {
        return status_rq;
    }

    public void setStatus_rq(String status_rq) {
        this.status_rq = status_rq;
    }

    public String getTime_rq() {
        return time_rq;
    }

    public void setTime_rq(String time_rq) {
        this.time_rq = time_rq;
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
