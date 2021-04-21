package com.system.domain;

import java.util.Date;

public class TicketsInfo {
    private Integer ticketId;

    private Integer airlineId;

    private String airlineName;

    private String ticketStartplace;

    private String ticketEndplace;

    private Date ticketBegintime;

    private Date ticketEndtime;

    private Integer ticketNumber;

    private Float ticketPrice;

    private String ticketImg;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
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

    public String getTicketStartplace() {
        return ticketStartplace;
    }

    public void setTicketStartplace(String ticketStartplace) {
        this.ticketStartplace = ticketStartplace == null ? null : ticketStartplace.trim();
    }

    public String getTicketEndplace() {
        return ticketEndplace;
    }

    public void setTicketEndplace(String ticketEndplace) {
        this.ticketEndplace = ticketEndplace == null ? null : ticketEndplace.trim();
    }

    public Date getTicketBegintime() {
        return ticketBegintime;
    }

    public void setTicketBegintime(Date ticketBegintime) {
        this.ticketBegintime = ticketBegintime;
    }

    public Date getTicketEndtime() {
        return ticketEndtime;
    }

    public void setTicketEndtime(Date ticketEndtime) {
        this.ticketEndtime = ticketEndtime;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketImg() {return ticketImg;}

    public void setTicketImg(String ticketImg) {
        this.ticketImg = ticketImg == null ? null : ticketImg.trim();
    }
}