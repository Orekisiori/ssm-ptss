package com.system.service.impl;

import com.system.domain.TClassification;
import com.system.domain.TFlowInfo;
import com.system.mapper.TClassificationMapper;
import com.system.service.FlowinfoService;
import com.system.service.ManageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManageCategoryServiceImpl implements ManageCategoryService {

    @Autowired
    private TClassificationMapper tClassificationMapper;

    @Autowired
    private FlowinfoService  flowinfoService;

    @Override
    public List<TClassification> getCategoryList() {
        return tClassificationMapper.getList();
    }

    @Override
    public TClassification selectByPrimaryKey(int categoryId) {
        return tClassificationMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public void updateByPrimaryKeySelective(TClassification tClassification) {
        tClassificationMapper.updateByPrimaryKeySelective(tClassification);
    }

    @Override
    public void deleteByID(Integer categoryId) {
        tClassificationMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public void insertCategory(TClassification tClassification) {
        tClassificationMapper.insert(tClassification);
    }

    @Override
    public List<TClassification> selectKey(String query) {
        return tClassificationMapper.selectKey(query);
    }

    @Override
    public List<TFlowInfo> selectByFlowinfos(Integer categoryId) {
        TClassification tClassification=tClassificationMapper.selectByPrimaryKey(categoryId);
        String fId=tClassification.getfId();

        //将fId转换为List<Integer>类型
        List<Integer> fIdsList = Arrays.asList(fId.split(","))
                .stream().map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());

        //根据fIds查询鲜花信息
        List<TFlowInfo> flowInfoList =  flowinfoService.selectByfIds(fIdsList);
        return flowInfoList;
    }
}
