package com.jb.dao;

import com.jb.entity.SkuProperty;

public interface SkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuProperty record);

    int insertSelective(SkuProperty record);

    SkuProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuProperty record);

    int updateByPrimaryKey(SkuProperty record);
}