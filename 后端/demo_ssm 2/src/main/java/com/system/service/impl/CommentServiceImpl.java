package com.system.service.impl;

import com.system.domain.TComment;
import com.system.mapper.TCommentMapper;
import com.system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private TCommentMapper tCommentMapper;

    @Override
    public void insertComment(TComment tComment) {
        tComment.setCrateTime(new Date());
        tComment.setUpdateTime(new Date());
        tComment.setStatic("1");
        tCommentMapper.insert(tComment);
    }

    @Override
    public TComment selectByfId(int fId,int orderId) {
        return tCommentMapper.selectByfId(fId,orderId);
    }

    @Override
    public List<TComment> getListByfId(int fId) {
        return tCommentMapper.getListByfId(fId);
    }

    @Override
    public List<TComment> selectKey(String query) {
        return tCommentMapper.selectKey(query);
    }
}
