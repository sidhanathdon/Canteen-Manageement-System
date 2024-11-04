package com.example.campusbite_admin.Domains;

public class AdminInfo {

    String adminName;
    String adminEmailId;
    String adminMobile;

    public AdminInfo(String adminName, String adminEmailId, String adminMobile) {
        this.adminName = adminName;
        this.adminEmailId = adminEmailId;
        this.adminMobile = adminMobile;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmailId() {
        return adminEmailId;
    }

    public void setAdminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile;
    }
}
