package com.system.service;

import com.system.domain.TCart;

import java.util.List;

public interface ManageCartService {
    List<TCart> getCartList();

    TCart selectByPrimaryKey(int cartId);

    void updateByPrimaryKeySelective(TCart tCart);

    void deleteByID(Integer cart_id);

    void insertOrder(TCart tCart);

    List<TCart> selectKey(String query);
}
