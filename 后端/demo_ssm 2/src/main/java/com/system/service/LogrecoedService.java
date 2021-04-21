package com.system.service;

import com.system.domain.LogRecord;

import java.util.List;

public interface LogrecoedService {
    List<LogRecord> getList();

    List<LogRecord> searchList(String searchtext);

}
