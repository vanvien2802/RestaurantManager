package com.midterm.restaurant_app.model;

import java.util.Date;
import java.util.Set;

public class OrderItem {
    private String customer_id;
    private String table_id;
    private Date order_date;
    private double total_price;
    private String payment_method;
    private Set<String> order_detail_ids;

    public OrderItem(){}

    public OrderItem(String customer_id, String table_id, Date order_date, double total_price, String payment_method, Set<String> order_detail_ids) {
        this.customer_id = customer_id;
        this.table_id = table_id;
        this.order_date = order_date;
        this.total_price = total_price;
        this.payment_method = payment_method;
        this.order_detail_ids = order_detail_ids;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Set<String> getOrder_detail_ids() {
        return order_detail_ids;
    }

    public void setOrder_detail_ids(Set<String> order_detail_ids) {
        this.order_detail_ids = order_detail_ids;
    }
}
