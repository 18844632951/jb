package com.jb.dao;

import com.jb.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeMapper {
    int deleteByPrimaryKey(Long id)  throws Exception;

    int insert(ProductType record) throws Exception;

    int insertSelective(ProductType record) throws Exception;

    ProductType selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(ProductType record) throws Exception;

    int updateByPrimaryKey(ProductType record) throws Exception;

    /**
     * 同过父类获取子分类
     *
     * @param pid
     * @return 类目
     */
    List<ProductType> getTypeByPid(@Param("pid") long pid) throws  Exception;

    /**
     * 同过路径获取分类
     *
     * @param pid
     * @return 类目
     */
    List<ProductType> getTypeAll() throws  Exception;

    /**
     * 级联删除分类列表
     * @param id 删除类目编号
     * @return 影响记录的行数
     * @throws Exception
     */
    int deleteCascade(String path) throws  Exception;
}