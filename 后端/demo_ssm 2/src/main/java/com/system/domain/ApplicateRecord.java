package com.system.domain;

public class ApplicateRecord {
    private Integer applicateId;

    private Integer airlineId;

    private String airlineName;

    private Float requestAccount;

    private String airlineIdentity;

    private String applicateStatus;

    public Integer getApplicateId() {
        return applicateId;
    }

    public void setApplicateId(Integer applicateId) {
        this.applicateId = applicateId;
    }

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

    public Float getRequestAccount() {
        return requestAccount;
    }

    public void setRequestAccount(Float requestAccount) {
        this.requestAccount = requestAccount;
    }

    public String getAirlineIdentity() {
        return airlineIdentity;
    }

    public void setAirlineIdentity(String airlineIdentity) {
        this.airlineIdentity = airlineIdentity == null ? null : airlineIdentity.trim();
    }

    public String getApplicateStatus() {
        return applicateStatus;
    }

    public void setApplicateStatus(String applicateStatus) {
        this.applicateStatus = applicateStatus == null ? null : applicateStatus.trim();
    }
}