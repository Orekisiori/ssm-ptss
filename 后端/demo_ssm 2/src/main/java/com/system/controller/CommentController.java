package com.system.controller;

import com.system.domain.TComment;
import com.system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("addComment")
    public String addComment(TComment tComment){
        try{
            commentService.insertComment(tComment);
        }
        catch (Exception e){
            return "error";
        }
        return "ok";
    }

    @RequestMapping("selectByfId")
    public TComment selectByfId(int fId,int orderId){
        return commentService.selectByfId(fId,orderId);
    }

    @RequestMapping("getListByfId")
    public List<TComment> getListByfId(int fId){
        return commentService.getListByfId(fId);
    }

    //模糊查询
    @RequestMapping(value = "selectKey")
    public List<TComment> selectKey(String query){
        return commentService.selectKey(query);
    }
}
