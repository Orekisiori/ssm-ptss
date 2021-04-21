package com.system.mapper;

import com.system.domain.ShowTable;

import java.util.List;

public interface ShowTableMapper {
    int deleteByPrimaryKey(Integer showId);

    int insert(ShowTable record);

    int insertSelective(ShowTable record);

    ShowTable selectByPrimaryKey(Integer showId);

    int updateByPrimaryKeySelective(ShowTable record);

    int updateByPrimaryKey(ShowTable record);

    List<ShowTable> getListId();
}