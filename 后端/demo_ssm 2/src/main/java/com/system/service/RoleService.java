package com.system.service;


import com.system.domain.Role;

/**
 *  Role 权限表Service层
 */
public interface RoleService {
    Role findByid(Integer id) throws Exception;
}
