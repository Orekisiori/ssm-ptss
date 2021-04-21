package com.system.mapper;

import com.system.domain.TWallet;

public interface TWalletMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TWallet record);

    int insertSelective(TWallet record);

    TWallet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TWallet record);

    int updateByPrimaryKey(TWallet record);

    TWallet selectByUserId(int userId);
}
