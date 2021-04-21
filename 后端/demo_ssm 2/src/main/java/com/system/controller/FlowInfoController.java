package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TCart;
import com.system.domain.TFlowInfo;
import com.system.service.FlowinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("FlowInfo")
public class FlowInfoController {

    @Autowired
    FlowinfoService flowinfoService;

    //获取商品列表
    @RequestMapping("list")
    public List<TFlowInfo> FlowInfoList(){
        return flowinfoService.getFlowInfoList();
    }

    //根据id查询商品信息
    @RequestMapping(value = "queryFlowInfoById",method = RequestMethod.GET)
    public TFlowInfo queryFlowInfoById(int fId){
        return flowinfoService.selectByPrimaryKey(fId);
    }

    @RequestMapping("addFlowInfo")
    public String addComment(TFlowInfo flowInfo){
        try{
            flowinfoService.addFlowInfo(flowInfo);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //保存商品信息
    @RequestMapping(value = "updateFlowInfo", method = RequestMethod.POST)
    public String updateFlowInfo(TFlowInfo tFlowInfo){
        try{
            flowinfoService.updateFlowInfo(tFlowInfo);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //根据id删除商品信息
    @RequestMapping(value = "deleteFlowInfoByid",method = RequestMethod.GET)
    public String deleteCartByid(Integer fId){
        try{
            flowinfoService.deleteByID(fId);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //模糊查询
    @RequestMapping(value = "selectKey")
    public List<TFlowInfo> selectKey(String query){
        return flowinfoService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TFlowInfo> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TFlowInfo> list = flowinfoService.getFlowInfoList();
        PageInfo<TFlowInfo> getpageinfo = new PageInfo<TFlowInfo>(list);
        return  getpageinfo;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
        try {
            String filePath = "C:/Users/Wt/Desktop/demo_vue/demo/static/" + file.getOriginalFilename();
            File saveDir = new File(filePath);
            if (!saveDir.exists()) {
                file.transferTo(saveDir);
                return "ok";
            }
            return "exit";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
}
