package com.system.service;

import com.system.domain.TCart;
import com.system.domain.vo.cartVo;

import java.util.List;

public interface CartService {

    List<TCart> getCartList(int userId);

    void addCart(int userId,int fId,int number);

    TCart getfNulberByfId(Integer fId);

    List<cartVo> getCartVoList(int userId);

    void deleteByID(Integer cartId);

    void updateByPrimaryKeySelective(TCart tCart);
}
