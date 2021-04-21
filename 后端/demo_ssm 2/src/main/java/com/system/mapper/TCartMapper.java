package com.system.mapper;

import com.system.domain.TCart;
import com.system.domain.vo.cartVo;

import java.util.List;

public interface TCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCart record);

    int insertSelective(TCart record);

    TCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCart record);

    int updateByPrimaryKey(TCart record);

    List<TCart> selectByuserId(int userId);

    List<cartVo> selectCartVoByuserId(int userId);

    TCart getfNulberByfId(Integer fId);

    List<TCart> getCartList();

    List<TCart> selectByKey(String query);

    void updateNumber(TCart tCart);
}
