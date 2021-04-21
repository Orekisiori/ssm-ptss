package com.system.domain;

import java.util.Date;

public class RechargeRecord {
    private Integer rechargeId;

    private Integer passengerId;

    private String passengerName;

    private String passengerIdentity;

    private String passengerPhone;

    private Float rechargeAccount;

    private String optionName;

    private Date rechargeTime;

    public Integer getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Integer rechargeId) {
        this.rechargeId = rechargeId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
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

    public Float getRechargeAccount() {
        return rechargeAccount;
    }

    public void setRechargeAccount(Float rechargeAccount) {
        this.rechargeAccount = rechargeAccount;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }
}