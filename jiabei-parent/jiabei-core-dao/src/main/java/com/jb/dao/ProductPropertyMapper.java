package com.jb.dao;

import com.jb.entity.ProductProperty;

public interface ProductPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductProperty record);

    int insertSelective(ProductProperty record);

    ProductProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductProperty record);

    int updateByPrimaryKey(ProductProperty record);
}