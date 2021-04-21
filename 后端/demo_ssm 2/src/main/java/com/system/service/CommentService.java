package com.system.service;

import com.system.domain.TComment;

import java.util.List;

public interface CommentService {
    void insertComment(TComment tComment);

    TComment selectByfId(int fId,int orderId);

    List<TComment> getListByfId(int fId);

    List<TComment> selectKey(String query);
}
