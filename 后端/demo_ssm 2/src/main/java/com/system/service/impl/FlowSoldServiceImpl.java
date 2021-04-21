package com.system.service.impl;

import com.system.domain.TFlowSold;
import com.system.mapper.TFlowSoldMapper;
import com.system.service.FlowSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlowSoldServiceImpl implements FlowSoldService {
    @Autowired
    private TFlowSoldMapper tFlowSoldMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addFlowSold(List<TFlowSold> tFlowSoldList) {
        if(tFlowSoldList.isEmpty() || tFlowSoldList==null) {
            throw new NullPointerException();
        }
        tFlowSoldMapper.insertFlowSolds(tFlowSoldList);
    }
}
