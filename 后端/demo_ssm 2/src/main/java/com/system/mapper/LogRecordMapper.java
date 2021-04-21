package com.system.mapper;

import com.system.domain.LogRecord;

import java.util.List;

public interface LogRecordMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(LogRecord record);

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(LogRecord record);

    int updateByPrimaryKey(LogRecord record);

    List<LogRecord> getList();

    List<LogRecord> searchList(String searchtext);
}