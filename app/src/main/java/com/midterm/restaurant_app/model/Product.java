package com.midterm.restaurant_app.model;

public class Product {
    private String idProduct;
    private String nameProduct;
    private double pricesProduct;
    private boolean statusProduct;
    private String detailProduct;
    private int rateProduct;

    public Product() {
    }

    public Product(String idProduct, String nameProduct, double pricesProduct, boolean statusProduct, String detailProduct, int rateProduct, int quantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.pricesProduct = pricesProduct;
        this.statusProduct = statusProduct;
        this.detailProduct = detailProduct;
        this.rateProduct = rateProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPricesProduct() {
        return pricesProduct;
    }

    public void setPricesProduct(double pricesProduct) {
        this.pricesProduct = pricesProduct;
    }

    public boolean isStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(boolean statusProduct) {
        this.statusProduct = statusProduct;
    }

    public String getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(String detailProduct) {
        this.detailProduct = detailProduct;
    }

    public int getRateProduct() {
        return rateProduct;
    }

    public void setRateProduct(int rateProduct) {
        this.rateProduct = rateProduct;
    }
}
