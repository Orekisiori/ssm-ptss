package com.system.mapper;

import com.system.domain.TClassification;

import java.util.List;

public interface TClassificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClassification record);

    int insertSelective(TClassification record);

    TClassification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClassification record);

    int updateByPrimaryKey(TClassification record);

    List<TClassification> getList();

    List<TClassification> selectKey(String query);
}
