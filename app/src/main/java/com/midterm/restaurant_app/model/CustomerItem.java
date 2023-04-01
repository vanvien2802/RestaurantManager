package com.midterm.restaurant_app.model;

import java.util.ArrayList;
import java.util.Set;

public class CustomerItem {
    private String name;
    private float total_paid;
    private String phone;
    private String email;
    private String member_status;
    private String address;
    private Set<String> favorite_dishes;

    public CustomerItem() {}

    public CustomerItem(String name, float total_paid, String phone, String email, String member_status, String address, Set<String> favorite_dishes) {
        this.name = name;
        this.total_paid = total_paid;
        this.phone = phone;
        this.email = email;
        this.member_status = member_status;
        this.address = address;
        this.favorite_dishes = favorite_dishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotal_paid() {
        return total_paid;
    }

    public void setTotal_paid(float total_paid) {
        this.total_paid = total_paid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMember_status() {
        return member_status;
    }

    public void setMember_status(String member_status) {
        this.member_status = member_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<String> getFavorite_dishes() {
        return favorite_dishes;
    }

    public void setFavorite_dishes(Set<String> favorite_dishes) {
        this.favorite_dishes = favorite_dishes;
    }
}
