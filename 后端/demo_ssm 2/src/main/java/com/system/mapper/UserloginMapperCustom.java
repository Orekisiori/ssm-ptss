package com.system.mapper;

import com.system.domain.UserloginCustom;

/**
 *  UserloginMapper扩展类
 */
public interface UserloginMapperCustom {

    UserloginCustom findOneByName(String name) throws Exception;

}
