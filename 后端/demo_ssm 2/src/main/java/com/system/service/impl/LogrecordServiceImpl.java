package com.system.service.impl;

import com.system.domain.LogRecord;
import com.system.mapper.LogRecordMapper;
import com.system.service.LogrecoedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class LogrecordServiceImpl implements LogrecoedService {
    @Autowired
    private LogRecordMapper logRecordMapper;

    @Override
    public List<LogRecord> getList() {
        return logRecordMapper.getList();
    }

    @Override
    public List<LogRecord> searchList(String searchtext) {
        return logRecordMapper.searchList(searchtext);
    }
}
