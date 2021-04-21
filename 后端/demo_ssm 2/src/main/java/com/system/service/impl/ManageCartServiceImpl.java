package com.system.service.impl;

import com.system.domain.TCart;
import com.system.mapper.TCartMapper;
import com.system.service.ManageCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageCartServiceImpl implements ManageCartService {

    @Autowired
    private TCartMapper tCartMapper;

    @Override
    public List<TCart> getCartList() {
        return tCartMapper.getCartList();
    }

    @Override
    public TCart selectByPrimaryKey(int cartId) {
        return tCartMapper.selectByPrimaryKey(cartId);
    }

    @Override
    public void updateByPrimaryKeySelective(TCart tCart) {
        tCartMapper.updateByPrimaryKey(tCart);
    }

    @Override
    public void deleteByID(Integer cart_id) {
        tCartMapper.deleteByPrimaryKey(cart_id);
    }

    @Override
    public void insertOrder(TCart tCart) {
        tCartMapper.insertSelective(tCart);
    }

    @Override
    public List<TCart> selectKey(String query) {
        return tCartMapper.selectByKey(query);
    }
}
