package com.example.thecakecorner.models;

public class ordermodel {
    private  String cakename;
    private int price;
    private String address;
    private int weigh;
    private String date;
    private String message;
    private String cusname;

    public ordermodel(String cakename, int price, String address, int weigh, String date, String message, String cusname) {
        this.cakename = cakename;
        this.price = price;
        this.address = address;
        this.weigh = weigh;
        this.date = date;
        this.message = message;
        this.cusname = cusname;
    }

    public String getCakename() {
        return cakename;
    }

    public void setCakename(String cakename) {
        this.cakename = cakename;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWeigh() {
        return weigh;
    }

    public void setWeigh(int weigh) {
        this.weigh = weigh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }
}
