package com.system.service.impl;

import com.system.domain.TFlowSold;
import com.system.mapper.TFlowSoldMapper;
import com.system.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private TFlowSoldMapper tFlowSoldMapper;


    @Override
    public List<TFlowSold> getStatisticsList() {
        return tFlowSoldMapper.getStatisticsList();
    }
}
