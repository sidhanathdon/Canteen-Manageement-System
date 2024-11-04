package com.example.campusbite_admin.Domains;

public class FreshItems {
    String  oh_title,oh_itemFee,oh_totalFee,oh_date,oh_time,og_itemNo,customerName;
    String oh_pic;

    public FreshItems(String oh_title, String oh_itemFee, String oh_totalFee, String oh_date, String oh_time, String oh_pic, String og_itemNo, String customerName) {
        this.oh_title = oh_title;
        this.oh_itemFee = oh_itemFee;
        this.oh_totalFee = oh_totalFee;
        this.oh_date = oh_date;
        this.oh_time = oh_time;
        this.oh_pic = oh_pic;
        this.og_itemNo = og_itemNo;
        this.customerName = customerName;
    }

    public String getOh_title() {
        return oh_title;
    }

    public void setOh_title(String oh_title) {
        this.oh_title = oh_title;
    }

    public String getOh_itemFee() {
        return oh_itemFee;
    }

    public void setOh_itemFee(String oh_itemFee) {
        this.oh_itemFee = oh_itemFee;
    }

    public String getOh_totalFee() {
        return oh_totalFee;
    }

    public void setOh_totalFee(String oh_totalFee) {
        this.oh_totalFee = oh_totalFee;
    }

    public String getOh_date() {
        return oh_date;
    }

    public void setOh_date(String oh_date) {
        this.oh_date = oh_date;
    }

    public String getOh_time() {
        return oh_time;
    }

    public void setOh_time(String oh_time) {
        this.oh_time = oh_time;
    }

    public String getOh_pic() {
        return oh_pic;
    }

    public void setOh_pic(String oh_pic) {
        this.oh_pic = oh_pic;
    }

    public String getOg_itemNo() {
        return og_itemNo;
    }

    public void setOg_itemNo(String og_itemNo) {
        this.og_itemNo = og_itemNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
