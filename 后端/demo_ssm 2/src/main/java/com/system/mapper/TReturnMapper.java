package com.system.mapper;

import com.system.domain.TReturn;

import java.util.List;

public interface TReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    int insertSelective(TReturn record);

    TReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TReturn record);

    int updateByPrimaryKey(TReturn record);

    List<TReturn> getReturnInfoList();

    List<TReturn> selectKey(String query);
}
