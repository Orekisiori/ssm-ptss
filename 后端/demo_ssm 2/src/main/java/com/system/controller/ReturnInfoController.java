package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TLease;
import com.system.domain.TReturn;
import com.system.service.ReturnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("return")
public class ReturnInfoController {

    @Autowired
    private ReturnInfoService returnInfoService;

    @RequestMapping("getReturnInfoList")
    public List<TReturn> getReturnInfoList(){
        return returnInfoService.getReturnInfoList();
    }

    @RequestMapping("delectReturnInfoBylId")
    public String delectReturnInfoBylId(int rId){
        try{
            returnInfoService.delectReturnInfoBylId(rId);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //模糊查询
    @RequestMapping(value = "selectKey",method = RequestMethod.GET)
    public List<TReturn> selectKey(String query){
        TReturn tReturn = new TReturn();
        tReturn.setQuery(query);
        return returnInfoService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TReturn> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TReturn> list = returnInfoService.getReturnInfoList();
        PageInfo<TReturn> getpageinfo = new PageInfo<TReturn>(list);
        return  getpageinfo;
    }
}
