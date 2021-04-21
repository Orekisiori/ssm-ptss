package com.system.mapper;

import com.system.domain.TicketsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TicketsInfoMapper {
    int deleteByPrimaryKey(Integer ticketId);

    int insert(TicketsInfo record);

    int insertSelective(TicketsInfo record);

    TicketsInfo selectByPrimaryKey(Integer ticketId);

    int updateByPrimaryKeySelective(TicketsInfo record);

    int updateByPrimaryKey(TicketsInfo record);

    List<TicketsInfo> getList();

    List<TicketsInfo> selectByStartPandEndP(@Param(value = "ticketStartplace") String ticketStartplace, @Param(value = "ticketEndplace")String ticketEndplace);

    List<TicketsInfo> selectAccurateList(@Param(value = "ticketStartplace") String ticketStartplace, @Param(value = "ticketEndplace")String ticketEndplace, @Param(value = "ticketBegintime")Date ticketBegintime,@Param(value = "ticketEndtime")Date ticketEndtime);

    List<TicketsInfo> getCurrentList(String airlineName);
}