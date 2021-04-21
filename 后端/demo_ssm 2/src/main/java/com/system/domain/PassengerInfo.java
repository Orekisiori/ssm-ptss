package com.system.domain;

import java.util.Date;

public class PassengerInfo {
    private Integer passengerId;

    private String avatar;

    private String passengerName;

    private String passengerPassword;

    private String passengerGender;

    private String passengerIdentity;

    private String passengerPhone;

    private Float passengerAccount;

    private Date registerTime;

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
    }

    public String getPassengerPassword() {
        return passengerPassword;
    }

    public void setPassengerPassword(String passengerPassword) {
        this.passengerPassword = passengerPassword == null ? null : passengerPassword.trim();
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender == null ? null : passengerGender.trim();
    }

    public String getPassengerIdentity() {
        return passengerIdentity;
    }

    public void setPassengerIdentity(String passengerIdentity) {
        this.passengerIdentity = passengerIdentity == null ? null : passengerIdentity.trim();
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone == null ? null : passengerPhone.trim();
    }

    public Float getPassengerAccount() {
        return passengerAccount;
    }

    public void setPassengerAccount(Float passengerAccount) {
        this.passengerAccount = passengerAccount;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}