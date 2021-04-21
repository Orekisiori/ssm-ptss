package com.system.service;

import com.system.domain.TComment;

import java.util.List;

public interface ManageCommentService {
    List<TComment> getCommentList();

    TComment selectByPrimaryKey(int id);

    void updateByPrimaryKeySelective(TComment tComment);

    void deleteByID(Integer id);

    void insertComment(TComment tComment);

    List<TComment> selectKey(String query);
}
