package com.system.service.impl;

import com.system.domain.ShowTable;
import com.system.domain.TicketsInfo;
import com.system.mapper.ShowTableMapper;
import com.system.mapper.TicketsInfoMapper;
import com.system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketsInfoMapper ticketsInfoMapper;

    @Autowired
    private ShowTableMapper showTableMapper;

    @Override
    public List<TicketsInfo> getAllTicket() {
        return ticketsInfoMapper.getList();
    }

    @Override
    public TicketsInfo getDetial(Integer ticketId) {
        return ticketsInfoMapper.selectByPrimaryKey(ticketId);
    }

    @Override
    public List<TicketsInfo> getRequsetList(String ticketStartplace,String ticketEndplace) {
        return ticketsInfoMapper.selectByStartPandEndP(ticketStartplace,ticketEndplace);
    }

    @Override
    public List<TicketsInfo> getAccurateRequsetList(String ticketStartplace, String ticketEndplace, Date ticketBegintime, Date ticketEndtime) {
        return ticketsInfoMapper.selectAccurateList(ticketStartplace,ticketEndplace,ticketBegintime,ticketEndtime);
    }

    @Override
    public void addticket(TicketsInfo ticketsInfo) {
        ticketsInfoMapper.insertSelective(ticketsInfo);
    }

    @Override
    public List<TicketsInfo> getCurrentList(String airlineName) {
        return ticketsInfoMapper.getCurrentList(airlineName);
    }

    @Override
    public TicketsInfo updateTicket(TicketsInfo ticketsInfo) {
        ticketsInfoMapper.updateByPrimaryKeySelective(ticketsInfo);
        return ticketsInfoMapper.selectByPrimaryKey(ticketsInfo.getTicketId());
    }

    @Override
    public void addtoUser(TicketsInfo ticketsInfo) {
        int ticketId = ticketsInfo.getTicketId();
        ShowTable showTable = new ShowTable();
        showTable.setTicketId(ticketId);
        showTableMapper.insertSelective(showTable);
    }

}
