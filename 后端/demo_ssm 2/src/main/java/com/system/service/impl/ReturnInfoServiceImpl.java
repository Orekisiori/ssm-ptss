package com.system.service.impl;

import com.system.domain.TReturn;
import com.system.mapper.TReturnMapper;
import com.system.service.ReturnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnInfoServiceImpl implements ReturnInfoService {

    @Autowired
    private TReturnMapper tReturnMapper;

    @Override
    public List<TReturn> getReturnInfoList() {
        return tReturnMapper.getReturnInfoList();
    }

    @Override
    public void delectReturnInfoBylId(int rId) {
        tReturnMapper.deleteByPrimaryKey(rId);
    }

    @Override
    public List<TReturn> selectKey(String query) {
        return tReturnMapper.selectKey(query);
    }
}
