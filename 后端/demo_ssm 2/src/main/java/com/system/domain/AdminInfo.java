package com.system.domain;

import java.util.Date;

public class AdminInfo {
    private Integer adminId;

    private String adminName;

    private String adminPassword;

    private Float adminAccount;

    private Date adminLogin;

    private Date adminExit;

    private String adminPhone;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public Float getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(Float adminAccount) {
        this.adminAccount = adminAccount;
    }

    public Date getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(Date adminLogin) {
        this.adminLogin = adminLogin;
    }

    public Date getAdminExit() {
        return adminExit;
    }

    public void setAdminExit(Date adminExit) {
        this.adminExit = adminExit;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }
}