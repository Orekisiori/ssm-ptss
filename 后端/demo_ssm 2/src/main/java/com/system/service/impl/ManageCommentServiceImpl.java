package com.system.service.impl;

import com.system.domain.TComment;
import com.system.mapper.TCommentMapper;
import com.system.service.ManageCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageCommentServiceImpl implements ManageCommentService {

    @Autowired
    private TCommentMapper tCommentMapper;


    @Override
    public List<TComment> getCommentList() {
        return tCommentMapper.getCommentList();
    }

    @Override
    public TComment selectByPrimaryKey(int id) {
        return tCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(TComment tComment) {
        tCommentMapper.updateByPrimaryKeySelective(tComment);
    }

    @Override
    public void deleteByID(Integer id) {
        tCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertComment(TComment tComment) {
        tCommentMapper.insert(tComment);
    }

    @Override
    public List<TComment> selectKey(String query) {
        return tCommentMapper.selectKey(query);
    }
}
