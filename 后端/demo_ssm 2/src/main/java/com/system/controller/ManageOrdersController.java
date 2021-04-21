package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TOrder;
import com.system.service.ManageOrdersService;
import com.system.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ManageOrders")
public class ManageOrdersController {
    @Autowired
    private ManageOrdersService manageOrdersService;

    @Autowired
    private OrderInfoService orderInfoService;

    //订单列表
    @RequestMapping(value = "getOrderList",method = RequestMethod.GET)
    public List<TOrder> getList(){
        return manageOrdersService.getOrderList();
    }

    //根据id查询订单信息
    @RequestMapping(value = "queryOrderByid",method = RequestMethod.GET)
    public TOrder queryUserById(int orderId){
        return manageOrdersService.selectByPrimaryKey(orderId);
    }

    //保存订单信息
    @RequestMapping(value = "saveOrder", method = RequestMethod.POST)
    public String updateOrder(TOrder tOrder){
        try{
            manageOrdersService.updateByPrimaryKeySelective(tOrder);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //根据id删除订单信息
    @RequestMapping(value = "deleteOrderById",method = RequestMethod.GET)
    public String deleteOrderById(Integer orderId){
        try{
            manageOrdersService.deleteByID(orderId);
            //删除订单信息的同时删除相关的订单详情信息
            orderInfoService.deleteById(orderId);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //插入订单信息
    @RequestMapping(value = "insertOrder", method = RequestMethod.POST)
    public String insertOrder(TOrder tOrder){
        try{
            manageOrdersService.insertOrder(tOrder);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //模糊查询
    @RequestMapping(value = "selectKey",method = RequestMethod.GET)
    public List<TOrder> selectKey(String query){
        TOrder tOrder = new TOrder();
        tOrder.setQuerykey(query);
        return manageOrdersService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TOrder> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TOrder> list = manageOrdersService.getOrderList();
        PageInfo<TOrder> getpageinfo = new PageInfo<TOrder>(list);
        return  getpageinfo;
    }
}
