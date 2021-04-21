package com.system.mapper;

import com.system.domain.ApplicateRecord;

import java.util.List;

public interface ApplicateRecordMapper {
    int deleteByPrimaryKey(Integer applicateId);

    int insert(ApplicateRecord record);

    int insertSelective(ApplicateRecord record);

    ApplicateRecord selectByPrimaryKey(Integer applicateId);

    int updateByPrimaryKeySelective(ApplicateRecord record);

    int updateByPrimaryKey(ApplicateRecord record);

    List<ApplicateRecord> getList();
}