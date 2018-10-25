package com.jb.dao;

import com.jb.entity.ProductMedia;

public interface ProductMediaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductMedia record);

    int insertSelective(ProductMedia record);

    ProductMedia selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductMedia record);

    int updateByPrimaryKey(ProductMedia record);
}