package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.TCart;
import com.system.domain.TComment;
import com.system.service.ManageCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ManageComment")
public class ManageCommentController {

    @Autowired
    private ManageCommentService manageCommentService;

    //评论列表
    @RequestMapping(value = "getCommentList",method = RequestMethod.GET)
    public List<TComment> getCommentList(){
        return manageCommentService.getCommentList();
    }

    //根据Id查询评论信息
    @RequestMapping(value = "queryCommentByid",method = RequestMethod.GET)
    public TComment queryCommentById(int id){
        return manageCommentService.selectByPrimaryKey(id);
    }

    //保存评论信息
    @RequestMapping(value = "saveComment", method = RequestMethod.POST)
    public String updateComment(TComment tComment){
        try{
            manageCommentService.updateByPrimaryKeySelective(tComment);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //根据id删除评论信息
    @RequestMapping(value = "deleteCommentById",method = RequestMethod.GET)
    public String deleteCommentById(Integer id){
        try{
            manageCommentService.deleteByID(id);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //插入评论信息
    @RequestMapping(value = "insertComment", method = RequestMethod.POST)
    public String insertComment(TComment tComment){
        try{
            manageCommentService.insertComment(tComment);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    //模糊查询
    @RequestMapping(value = "selectKey")
    public List<TComment> selectKey(String query){
        return manageCommentService.selectKey(query);
    }

    //pagehelper分页
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TComment> getpage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TComment> list = manageCommentService.getCommentList();
        PageInfo<TComment> getpageinfo = new PageInfo<TComment>(list);
        return  getpageinfo;
    }
}
