package com.midterm.restaurant_app.model;

import com.google.type.DateTime;

public class Order {
    private String idOrderDetail;
    private String idOrder;
    private String idAcc;
    private String idTable;
    private String statusOrder;
    private Double totalBill;
    private DateTime dateTimeOrder;

    public Order(){}

    public Order(String idOrderDetail, String idOrder, String idAcc, String idTable, String statusOrder, Double totalBill, DateTime dateTimeOrder) {
        this.idOrderDetail = idOrderDetail;
        this.idOrder = idOrder;
        this.idAcc = idAcc;
        this.idTable = idTable;
        this.statusOrder = statusOrder;
        this.totalBill = totalBill;
        this.dateTimeOrder = dateTimeOrder;
    }

    public String getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(String idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(String idAcc) {
        this.idAcc = idAcc;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    public DateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public void setDateTimeOrder(DateTime dateTimeOrder) {
        this.dateTimeOrder = dateTimeOrder;
    }
}
