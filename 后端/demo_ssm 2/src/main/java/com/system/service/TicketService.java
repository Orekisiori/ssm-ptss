package com.system.service;

import com.system.domain.TicketsInfo;

import java.util.Date;
import java.util.List;

public interface TicketService {
    List<TicketsInfo> getAllTicket();

    TicketsInfo getDetial(Integer ticketId);

    List<TicketsInfo> getRequsetList(String ticketStartplace,String ticketEndplace);

    List<TicketsInfo> getAccurateRequsetList(String ticketStartplace, String ticketEndplace, Date ticketBegintime, Date ticketEndtime);

    void addticket(TicketsInfo ticketsInfo);

    List<TicketsInfo> getCurrentList(String airlineName);

    TicketsInfo updateTicket(TicketsInfo ticketsInfo);

    void addtoUser(TicketsInfo ticketsInfo);
}
