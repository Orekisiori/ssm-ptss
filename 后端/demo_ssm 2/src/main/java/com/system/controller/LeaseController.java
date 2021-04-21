package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TFlowInfo;
import com.system.domain.TLease;
import com.system.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lease")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @RequestMapping("addLease")
    public String addLease(TLease tLease) {
        try {
            leaseService.addLease(tLease);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }

    //超过期限要交钱
    @RequestMapping("uptadeRow")
    public String uptadeRow(TLease tLease) {
        try {
            leaseService.uptadeRow(tLease);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }

    @RequestMapping("getLeaseList")
    public List<TLease> getLeaseList(){
        return leaseService.getLeaseList();
    }

    @RequestMapping("delectLeaseBylId")
    public String delectLeaseBylId(int lId){
        try{
            leaseService.delectLeaseBylId(lId);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //根据商品的fIds查询商品信息
    @RequestMapping(value = "queryFlowInfoByfIds",method = RequestMethod.GET)
    public List<TFlowInfo> queryFlowInfoById(){
        return leaseService.selectByfIds();
    }

    //模糊查询
    @RequestMapping(value = "selectKey",method = RequestMethod.GET)
    public List<TLease> selectKey(String query){
        TLease tLease = new TLease();
        tLease.setQuery(query);
        return leaseService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TLease> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TLease> list = leaseService.getLeaseList();
        PageInfo<TLease> getpageinfo = new PageInfo<TLease>(list);
        return  getpageinfo;
    }
}
