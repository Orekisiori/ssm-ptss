package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TCart;
import com.system.service.ManageCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ManageCart")
public class ManageCartController {
    @Autowired
    private ManageCartService manageCartService;

    //购物车列表
    @RequestMapping(value = "getCartList",method = RequestMethod.GET)
    public List<TCart> getList(){
        return manageCartService.getCartList();
    }

    //根据id查询购物车信息
    @RequestMapping(value = "queryCartByid",method = RequestMethod.GET)
    public TCart queryCartById(int cartId){
        return manageCartService.selectByPrimaryKey(cartId);
    }

    //保存购物车信息
    @RequestMapping(value = "saveCart", method = RequestMethod.POST)
    public String updateCart(TCart tCart){
        try{
            manageCartService.updateByPrimaryKeySelective(tCart);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //根据id删除购物车信息
    @RequestMapping(value = "deleteCartById",method = RequestMethod.GET)
    public String deleteCartById(Integer cartId){
        try{
            manageCartService.deleteByID(cartId);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //插入购物车信息
    @RequestMapping(value = "insertCart", method = RequestMethod.POST)
    public String insertCart(TCart tCart){
        try{
            manageCartService.insertOrder(tCart);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //模糊查询
    @RequestMapping(value = "selectKey",method = RequestMethod.GET)
    public List<TCart> selectKey(String query){
        TCart tCart = new TCart();
        tCart.setQuerykey(query);
        return manageCartService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TCart> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TCart> list = manageCartService.getCartList();
        PageInfo<TCart> getpageinfo = new PageInfo<TCart>(list);
        return  getpageinfo;
    }
}
