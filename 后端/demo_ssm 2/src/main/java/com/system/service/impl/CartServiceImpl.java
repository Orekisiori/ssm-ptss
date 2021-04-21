package com.system.service.impl;

import com.system.domain.TCart;
import com.system.domain.vo.cartVo;
import com.system.mapper.TCartMapper;
import com.system.service.CartService;
import com.system.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    TCartMapper tCartMapper;

    public List<cartVo> getCartVoList(int userId) {
        return tCartMapper.selectCartVoByuserId(userId);
    }

    @Override
    public void deleteByID(Integer cartId) {
        tCartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public void updateByPrimaryKeySelective(TCart tCart) {
        tCartMapper.updateNumber(tCart);
    }

    @Override
    public List<TCart> getCartList(int userId) {
        return tCartMapper.selectByuserId(userId);
    }

    public void addCart(int userId, int fId, int fNumber) {
        //获取当前购物车信息
        List<TCart> carts=getCartList(userId);
        //将当前购物车里的信息取出
        boolean flgs = true;
        for (TCart cart : carts) {
            //判断当前购物车有没有要添加的商品信息,有就更新t_cart表,没有就插入一条数据
            if (cart.getfId().equals(fId)) {
                cart.setfNumber(cart.getfNumber() + fNumber);
                tCartMapper.updateByPrimaryKey(cart);
                flgs = false;
            }
        }
        if (flgs) {
            TCart tCart = new TCart();
            tCart.setUserId(userId);
            tCart.setfId(fId);
            tCart.setfNumber(fNumber);
            tCart.setCartId(MyUtils.getCurrentTimeForId());
            tCartMapper.insert(tCart);
        }
    }

    @Override
    public TCart getfNulberByfId(Integer fId) {
        return tCartMapper.getfNulberByfId(fId);
    }

}
