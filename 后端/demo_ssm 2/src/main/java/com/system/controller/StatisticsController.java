package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TFlowSold;
import com.system.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    //销售列表
    @RequestMapping(value = "getStatisticsList",method = RequestMethod.GET)
    public List<TFlowSold> getStatisticsList(){
        return statisticsService.getStatisticsList();
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TFlowSold> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TFlowSold> list = statisticsService.getStatisticsList();
        PageInfo<TFlowSold> getpageinfo = new PageInfo<TFlowSold>(list);
        return  getpageinfo;
    }
}
