package com.system.mapper;

import com.system.domain.TComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TComment record);

    int insertSelective(TComment record);

    TComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TComment record);

    int updateByPrimaryKey(TComment record);

    TComment selectByfId(@Param("fId") int fId,@Param("orderId") int orderId);

    List<TComment> getListByfId(int fId);

    List<TComment> selectKey(String query);

    List<TComment> getCommentList();
}
