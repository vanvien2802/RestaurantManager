package com.midterm.restaurant_app.model;

public class Ordered {
    private String idOrdered;
    private String idAcc;
    private String idDOrd;
    private String idTable;
    private boolean statusOrdered;
    private double totalBill;
    private String dtimeOder;


    public Ordered() {
    }

    public Ordered(String idOrdered, String idAcc, String idDOrd, String idTable, boolean statusOrdered, double totalBill, String dtimeOder) {
        this.idOrdered = idOrdered;
        this.idAcc = idAcc;
        this.idDOrd = idDOrd;
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

    public String getIdDOrd() {
        return idDOrd;
    }

    public void setIdDOrd(String idDOrd) {
        this.idDOrd = idDOrd;
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
