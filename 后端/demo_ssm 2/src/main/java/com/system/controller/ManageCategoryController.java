package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TClassification;
import com.system.domain.TFlowInfo;
import com.system.service.ManageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ManageCategory")
public class ManageCategoryController {

    @Autowired
    private ManageCategoryService manageCategoryService;

    //分类列表
    @RequestMapping(value = "getCategoryList",method = RequestMethod.GET)
    public List<TClassification> getList(){
        return manageCategoryService.getCategoryList();
    }

    //根据id查询分类信息
    @RequestMapping(value = "queryCategoryByid",method = RequestMethod.GET)
    public TClassification queryCategoryById(int CategoryId){
        return manageCategoryService.selectByPrimaryKey(CategoryId);
    }

    //保存分类信息
    @RequestMapping(value = "saveCategory", method = RequestMethod.POST)
    public String updateCategory(TClassification tClassification){
        try{
            manageCategoryService.updateByPrimaryKeySelective(tClassification);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //根据id删除分类信息
    @RequestMapping(value = "deleteCategoryById",method = RequestMethod.GET)
    public String deleteCategoryById(Integer categoryId){
        try{
            manageCategoryService.deleteByID(categoryId);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //插入分类信息
    @RequestMapping(value = "insertCategory", method = RequestMethod.POST)
    public String insertCategory(TClassification tClassification){
        try{
            manageCategoryService.insertCategory(tClassification);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //模糊查询
    @RequestMapping(value = "selectKey",method = RequestMethod.GET)
    public List<TClassification> selectKey(String query){
        TClassification tClassification = new TClassification();
        tClassification.setQuery(query);
        return manageCategoryService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TClassification> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TClassification> list = manageCategoryService.getCategoryList();
        PageInfo<TClassification> getpageinfo = new PageInfo<TClassification>(list);
        return  getpageinfo;
    }

    //根据分类Id获取商品列表
    @RequestMapping(value = "getFlowinfoCategoty",method = RequestMethod.GET)
    public List<TFlowInfo> getFlowinfoCategoty(Integer categoryId){
        return manageCategoryService.selectByFlowinfos(categoryId);
    }
}
