package com.system.controller;

import com.system.domain.LogRecord;
import com.system.service.LogrecoedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("logrecord")
public class LogrecordController {
    @Autowired
    private LogrecoedService logrecoedService;

    @RequestMapping("getList")
    public List<LogRecord> getList() {
        return logrecoedService.getList();
    }

    @RequestMapping(value = "searchList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<LogRecord> searchList(String searchtext) {
        return logrecoedService.searchList(searchtext);
    }

}
