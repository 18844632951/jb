package com.jb.dao;

import com.jb.entity.ProductViewProperty;

public interface ProductViewPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductViewProperty record);

    int insertSelective(ProductViewProperty record);

    ProductViewProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductViewProperty record);

    int updateByPrimaryKey(ProductViewProperty record);
}