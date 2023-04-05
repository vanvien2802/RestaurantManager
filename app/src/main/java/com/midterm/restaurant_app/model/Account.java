package com.midterm.restaurant_app.model;

public class Account {
    private String idAcc;
    private String nameAcc;
    private String email;
    private String nameUser;
    private String password;
    private String phoneNumber;
    private String address;
    private String idRole;

    public Account(){}

    public Account(String idAcc, String nameAcc, String email, String nameUser, String password, String phoneNumber, String address, String idRole) {
        this.idAcc = idAcc;
        this.nameAcc = nameAcc;
        this.email = email;
        this.nameUser = nameUser;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.idRole = idRole;
    }

    public String getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(String idAcc) {
        this.idAcc = idAcc;
    }

    public String getNameAcc() {
        return nameAcc;
    }

    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
}
