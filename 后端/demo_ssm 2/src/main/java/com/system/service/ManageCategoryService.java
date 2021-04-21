package com.system.service;

import com.system.domain.TCart;
import com.system.domain.TClassification;
import com.system.domain.TFlowInfo;

import java.util.List;

public interface ManageCategoryService {

    List<TClassification> getCategoryList();

    TClassification selectByPrimaryKey(int categoryId);

    void updateByPrimaryKeySelective(TClassification tClassification);

    void deleteByID(Integer categoryId);

    void insertCategory(TClassification tClassification);

    List<TClassification> selectKey(String query);

    List<TFlowInfo> selectByFlowinfos(Integer categoryId);
}
