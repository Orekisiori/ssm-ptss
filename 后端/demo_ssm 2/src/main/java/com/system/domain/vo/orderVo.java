package com.system.domain.vo;

import com.system.domain.TFlowInfo;
import com.system.domain.TOrder;

import java.util.List;

/**
 * 显示前端传过来的数据
 */
public class orderVo {
    private TOrder tOrder;

    private List<TFlowInfo> flowInfoList;

    public TOrder gettOrder() {
        return tOrder;
    }

    public void settOrder(TOrder tOrder) {
        this.tOrder = tOrder;
    }

    public List<TFlowInfo> getFlowInfoList() {
        return flowInfoList;
    }

    public void setFlowInfoList(List<TFlowInfo> flowInfoList) {
        this.flowInfoList = flowInfoList;
    }
}
