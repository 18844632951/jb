package com.jb.dao;

import com.jb.entity.SKU;

public interface SKUMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SKU record);

    int insertSelective(SKU record);

    SKU selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SKU record);

    int updateByPrimaryKey(SKU record);
}