package com.system.service;

import com.system.domain.TReturn;

import java.util.List;

public interface ReturnInfoService {
    List<TReturn> getReturnInfoList();

    void delectReturnInfoBylId(int rId);

    List<TReturn> selectKey(String query);
}
