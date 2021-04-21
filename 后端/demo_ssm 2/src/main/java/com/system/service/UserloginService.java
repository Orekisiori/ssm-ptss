package com.system.service;


import com.system.domain.Userlogin;

import java.util.List;

public interface UserloginService {

    //根据名字查找用户
    Userlogin findByName(String name) throws Exception;

    //保存用户登录信息
    void save(Userlogin userlogin) throws Exception;

    //根据姓名删除
    void removeByName(String name) throws Exception;

    //根据用户名更新
    void updateByName(String name, Userlogin userlogin);

    //根据id查询用户信息
    Userlogin selectByPrimaryKey(Integer userid);

    //用户列表信息
    List<Userlogin> getListUser();

    //根据ID更新用户信息
    void updateByPrimaryKeySelective(Userlogin userlogin);

    //根据id删除
    void deleteByID(Integer userid);

    //模糊查询
    List<Userlogin>selectKey(String query);

    void updateImg(Userlogin userlogin);
}
