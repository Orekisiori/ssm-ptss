package com.system.service.impl;

import com.system.domain.TWallet;
import com.system.domain.Userlogin;
import com.system.domain.UserloginExample;
import com.system.mapper.TWalletMapper;
import com.system.mapper.UserloginMapper;
import com.system.service.UserloginService;
import com.system.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginMapper userloginMapper;

    @Autowired
    private TWalletMapper tWalletMapper;

    public Userlogin findByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUseraccountEqualTo(name);

        List<Userlogin> list = userloginMapper.selectByExample(userloginExample);

        if(list.size()==0){
            return null;
        }
        return list.get(0);
    }

    public void save(Userlogin userlogin) throws Exception {
        userloginMapper.insert(userlogin);

        //根据账号查询id
        Userlogin user=userloginMapper.selectByUserAccount(userlogin.getUserAccount());

        //添加用户的时候初始化钱包
        TWallet tWallet = new TWallet();
        tWallet.setBalance(0L);
        tWallet.setUserId(user.getUserid());
        tWalletMapper.insert(tWallet);
    }

    public void removeByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUseraccountEqualTo(name);

        userloginMapper.deleteByExample(userloginExample);
    }

    public void updateByName(String name, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUseraccountEqualTo(name);

        userloginMapper.updateByExampleSelective(userlogin, userloginExample);
    }

    public Userlogin selectByPrimaryKey(Integer userid) {
        return userloginMapper.selectByPrimaryKey(userid);
    }

    public List<Userlogin> getListUser() {
        return userloginMapper.getListUser();
    }

    public void updateByPrimaryKeySelective(Userlogin userlogin) {
        userloginMapper.updateByPrimaryKeySelective(userlogin);
    }

    public void deleteByID(Integer userid) {
        userloginMapper.deleteByPrimaryKey(userid);
    }

    public List<Userlogin> selectKey(String query) {
        return userloginMapper.selectKey(query);
    }

    @Override
    public void updateImg(Userlogin userlogin) {
        //把图片路径补全
        String getimg=userlogin.getUserImg();
        String imgPath = "/static/"+getimg;
        userlogin.setUserImg(imgPath);

        userloginMapper.updateByPrimaryKeySelective(userlogin);
    }


}
