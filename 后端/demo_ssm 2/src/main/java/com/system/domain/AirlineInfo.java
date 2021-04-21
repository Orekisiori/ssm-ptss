package com.system.domain;

import java.util.Date;

public class AirlineInfo {
    private Integer airlineId;

    private String airlineName;

    private String airlinePassword;

    private String airlineIdentity;

    private String airlinePhone;

    private Date registerdate;

    private Float airlineAccount;

    private String isvalid;

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName == null ? null : airlineName.trim();
    }

    public String getAirlinePassword() {
        return airlinePassword;
    }

    public void setAirlinePassword(String airlinePassword) {
        this.airlinePassword = airlinePassword == null ? null : airlinePassword.trim();
    }

    public String getAirlineIdentity() {
        return airlineIdentity;
    }

    public void setAirlineIdentity(String airlineIdentity) {
        this.airlineIdentity = airlineIdentity == null ? null : airlineIdentity.trim();
    }

    public String getAirlinePhone() {
        return airlinePhone;
    }

    public void setAirlinePhone(String airlinePhone) {
        this.airlinePhone = airlinePhone == null ? null : airlinePhone.trim();
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public Float getAirlineAccount() {
        return airlineAccount;
    }

    public void setAirlineAccount(Float airlineAccount) {
        this.airlineAccount = airlineAccount;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }
}