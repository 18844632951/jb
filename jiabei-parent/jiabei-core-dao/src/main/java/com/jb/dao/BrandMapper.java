package com.jb.dao;

import com.jb.core.base.PageResult;
import com.jb.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(Brand record) throws Exception;

    int insertSelective(Brand record) throws Exception;

    Brand selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(Brand record) throws Exception;

    int updateByPrimaryKey(Brand record) throws Exception;

    //分页
    List<Brand> get(@Param("page") PageResult page) throws Exception;

    //获取总的数据数
    long getAllRows() throws  Exception;

    /**
     * 通过名模糊查询品牌
     * @param name  名
     * @return 结果
     */
    List<Brand> getBrandByName(String name) throws  Exception;
}