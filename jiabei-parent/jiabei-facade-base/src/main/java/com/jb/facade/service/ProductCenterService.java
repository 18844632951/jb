package com.jb.facade.service;

import com.jb.core.base.PageResult;
import com.jb.core.exception.InputParamsNullException;
import com.jb.entity.Product;
import com.jb.entity.ProductExt;
import com.jb.entity.ProductMedia;

/**
 * 商品中心接口定义
 * Created by 27654 on 2018/10/24.
 */
public interface ProductCenterService {

    /**
     * 分页获取商家商品数
     *
     * @param page 分页信息
     * @return PageResult 分页结果
     * @throws InputParamsNullException
     * @throws Exception
     */
    PageResult get(PageResult page) throws InputParamsNullException, Exception;

    /**
     * 查找执行商品的信息
     * @param id 查找的商品号
     * @return  商品的详细信息（包括图片，详情，规格，skus属性等信息）
     * @throws InputParamsNullException
     * @throws Exception
     */
    Product get(String id) throws InputParamsNullException, Exception;

    /**
     * 新增商品信息
     *
     * @param product      商品基本信息
     * @param productExt   商品详细信息
     * @param productMedia 商品媒体信息
     * @throws InputParamsNullException
     * @throws Exception
     */
    void add(Product product, ProductExt productExt, ProductMedia productMedia) throws InputParamsNullException, Exception;

    /**
     * 更新商品信息
     *
     * @param product      商品基本信息
     * @param productExt   商品详细信息
     * @param productMedia 商品媒体信息
     * @return 影响记录数
     * @throws InputParamsNullException
     * @throws Exception
     */
    int update(Product product, ProductExt productExt, ProductMedia productMedia) throws InputParamsNullException, Exception;

    /**
     * 通过商品号删除指定商品
     *
     * @param productId 商品编号
     * @return 影响记录数
     * @throws InputParamsNullException
     * @throws Exception
     */
    int delete(String productId) throws InputParamsNullException, Exception;
}
