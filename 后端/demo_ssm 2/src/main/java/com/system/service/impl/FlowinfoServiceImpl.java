package com.system.service.impl;

import com.system.domain.TFlowInfo;
import com.system.mapper.TFlowInfoMapper;
import com.system.service.FlowinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlowinfoServiceImpl implements FlowinfoService {
    @Autowired
    private TFlowInfoMapper tFlowInfoMapper;


    public void addFlowInfo(TFlowInfo tFlowInfo) {
        //把图片路径补全
        String getimg=tFlowInfo.getfImgpath();
        String imgPath = "/static/"+getimg;
        tFlowInfo.setfImgpath(imgPath);

        tFlowInfoMapper.insert(tFlowInfo);
    }

    public List<TFlowInfo> getFlowInfoList() {
        return tFlowInfoMapper.getFlowInfoList();
    }

    public TFlowInfo selectByPrimaryKey(Integer fId) {
        return tFlowInfoMapper.selectByPrimaryKey(fId);
    }

    public List<TFlowInfo> selectByfIds(List<Integer> fIds) {
        return tFlowInfoMapper.selectByfIds(fIds);
    }

    @Override
    public void deleteByID(Integer fId) {
        tFlowInfoMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public List<TFlowInfo> selectKey(String query) {
        return tFlowInfoMapper.selectKey(query);
    }

    @Override
    public void updateFlowInfo(TFlowInfo tFlowInfo) {
        //把图片路径补全
        String getimg=tFlowInfo.getfImgpath();
        String imgPath = "/static/"+getimg;
        tFlowInfo.setfImgpath(imgPath);
        tFlowInfoMapper.updateByPrimaryKey(tFlowInfo);
    }

    @Override
    public void updateStock(TFlowInfo tFlowInfo) {
        tFlowInfoMapper.updateStock(tFlowInfo);
    }

    /**
     * 根据fnmae查询盆栽库存
     * @param fName
     */
    @Override
    public TFlowInfo getStock(String fName) {
        return tFlowInfoMapper.getStock(fName);
    }
}
