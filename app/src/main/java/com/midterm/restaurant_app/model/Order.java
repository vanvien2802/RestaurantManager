package com.midterm.restaurant_app.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String idOrdered;
    private String idAcc;
    private List<String> idDetailOrders;
    private String idTable;
    private boolean statusOrdered;
    private double totalBill;
    private String dtimeOder;


    public Order() {
    }

    public Order(String idOrdered, String idAcc, List<String> idDetailOrders, String idTable, boolean statusOrdered, double totalBill, String dtimeOder) {
        this.idOrdered = idOrdered;
        this.idAcc = idAcc;
        this.idDetailOrders = idDetailOrders;
        this.idTable = idTable;
        this.statusOrdered = statusOrdered;
        this.totalBill = totalBill;
        this.dtimeOder = dtimeOder;
    }

    public String getIdOrdered() {
        return idOrdered;
    }

    public void setIdOrdered(String idOrdered) {
        this.idOrdered = idOrdered;
    }

    public String getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(String idAcc) {
        this.idAcc = idAcc;
    }

    public List<String> getIdDetailOrders() {
        return idDetailOrders;
    }

    public void setIdDetailOrders(List<String> idDetailOrders) {
        this.idDetailOrders = idDetailOrders;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public boolean isStatusOrdered() {
        return statusOrdered;
    }

    public void setStatusOrdered(boolean statusOrdered) {
        this.statusOrdered = statusOrdered;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public String getDtimeOder() {
        return dtimeOder;
    }

    public void setDtimeOder(String dtimeOder) {
        this.dtimeOder = dtimeOder;
    }
}
