package com.example.campusbite_admin.Domains;

public class Customers {
    String Id;
    String fullName;
    String email;
    String mobile;


    public Customers(String Id, String fullName,String email,String mobile) {
        this.Id=Id;
        this.fullName=fullName;
        this.email=email;
        this.mobile=mobile;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
