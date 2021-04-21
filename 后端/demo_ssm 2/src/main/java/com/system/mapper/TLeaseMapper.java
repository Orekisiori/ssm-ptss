package com.system.mapper;

import com.system.domain.TLease;

import java.util.List;

public interface TLeaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TLease record);

    int insertSelective(TLease record);

    TLease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TLease record);

    int updateByPrimaryKey(TLease record);

    List<TLease> getLeaseList();

    List<TLease> selectKey(String query);
}
