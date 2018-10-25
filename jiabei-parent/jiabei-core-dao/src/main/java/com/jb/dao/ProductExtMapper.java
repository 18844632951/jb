package com.jb.dao;

import com.jb.entity.ProductExt;

public interface ProductExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductExt record);

    int insertSelective(ProductExt record);

    ProductExt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductExt record);

    int updateByPrimaryKeyWithBLOBs(ProductExt record);

    int updateByPrimaryKey(ProductExt record);
}