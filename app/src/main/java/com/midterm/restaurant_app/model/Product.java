package com.midterm.restaurant_app.model;

public class Product {
    private String idProduct;
    private String nameProduct;
    private double pricesProduct;
    private double rateProduct;
    private boolean statusProduct;
    private String detailProduct;

    public Product(){}

    public Product(String idProduct, String nameProduct, double pricesProduct, double rateProduct, boolean statusProduct, String detailProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.pricesProduct = pricesProduct;
        this.rateProduct = rateProduct;
        this.statusProduct = statusProduct;
        this.detailProduct = detailProduct;
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

    public double getRateProduct() {
        return rateProduct;
    }

    public void setRateProduct(double rateProduct) {
        this.rateProduct = rateProduct;
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


}
