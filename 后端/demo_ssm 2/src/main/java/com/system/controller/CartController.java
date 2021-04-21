package com.system.controller;

import com.system.domain.TCart;
import com.system.domain.vo.cartVo;
import com.system.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping("getCartList")
    public List<cartVo> getList(int userId){
        return cartService.getCartVoList(userId);
    }

    @RequestMapping("addCart")
    public String addCart(int userId,int fId,int fNumber){
        //获取当卡用户的购物车信息
        try {
            cartService.addCart(userId,fId,fNumber);
        }catch (Exception e) {
            return "error";
        }
        return "success";
    }

    //根据id删除用户信息
    @RequestMapping(value = "deleteCartByid",method = RequestMethod.GET)
    public String deleteCartByid(Integer cartId){
        try{
            cartService.deleteByID(cartId);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //保存购物车信息
    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    public String updateCart(TCart tCart){
        try{
            cartService.updateByPrimaryKeySelective(tCart);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }
}
