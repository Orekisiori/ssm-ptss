package com.system.controller;

import com.system.domain.TicketsInfo;
import com.system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("ticketinfo")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping("getlist")
    public List<TicketsInfo> getList() throws Exception {
        return ticketService.getAllTicket();
    }

    @RequestMapping(value = "getDetial",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public TicketsInfo getdetial(Integer ticketId) throws Exception {
        return ticketService.getDetial(ticketId);
    }

    //查询机票,模糊查询
    @RequestMapping(value = "requestTicket",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<TicketsInfo> getRequsetList(String ticketStartplace,String ticketEndplace) throws  Exception{
        return ticketService.getRequsetList(ticketStartplace,ticketEndplace);
    }

    //查询机票，详细查询
    @RequestMapping(value = "accurateRequestTicket",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<TicketsInfo> getAccurateRequsetList(String ticketStartplace, String ticketEndplace, Date ticketBegintime,Date ticketEndtime) throws  Exception{
        return ticketService.getAccurateRequsetList(ticketStartplace,ticketEndplace,ticketBegintime,ticketEndtime);
    }

    @RequestMapping(value = "addticket",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public void addTicket(TicketsInfo ticketsInfo) {
        ticketService.addticket(ticketsInfo);
    }

    @RequestMapping(value = "getCurrentList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<TicketsInfo> getCurrentList(String airlineName) {
        return ticketService.getCurrentList(airlineName);
    }

    //更新机票信息
    @RequestMapping(value = "updateTicket",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public TicketsInfo updateTicket(TicketsInfo ticketsInfo) throws Exception{
        return ticketService.updateTicket(ticketsInfo);
    }

    //添加至showtable中
    @RequestMapping(value = "addtuShowtable",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public void addtoUser(TicketsInfo ticketsInfo) throws Exception {
         ticketService.addtoUser(ticketsInfo);
    }

}
