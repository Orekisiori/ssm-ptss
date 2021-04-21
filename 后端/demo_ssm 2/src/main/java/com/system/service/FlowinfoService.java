package com.system.service;

import com.system.domain.TFlowInfo;
import com.system.domain.TOrderInfo;

import java.util.List;

public interface FlowinfoService {

    void addFlowInfo(TFlowInfo tFlowInfo);

    //返回鲜花列表
    List<TFlowInfo> getFlowInfoList();

    //根据id查询鲜花信息
    TFlowInfo selectByPrimaryKey(Integer fId);

    void updateStock(TFlowInfo tFlowInfo);

    TFlowInfo getStock(String fName);

    List<TFlowInfo> selectByfIds(List<Integer> fIds);

    void deleteByID(Integer fId);

    List<TFlowInfo> selectKey(String query);

    void updateFlowInfo(TFlowInfo tFlowInfo);
}
