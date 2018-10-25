package com.jb.dao;

import com.jb.entity.ProductComment;

public interface ProductCommentMapper {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(ProductComment record) throws Exception;

    int insertSelective(ProductComment record) throws Exception;

    ProductComment selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(ProductComment record) throws Exception;

    int updateByPrimaryKey(ProductComment record) throws Exception;
}