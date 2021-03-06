package com.jb.facade.service;

import com.jb.core.base.PageResult;
import com.jb.core.exception.InputParamsNullException;
import com.jb.entity.Brand;
import com.jb.entity.ProductType;

import java.util.List;

/**
 * 为每一个模块对外提供一个接口
 * 模块： 商家中心
 * Created by 27654 on 2018/10/15.
 */
public interface ProductTypeCenterService {
    /**
     * 通过父级类目获取子级分类
     *
     * @return pid 下的所有子节点类目信息
     */
    List<ProductType> getTypes(long pid) throws Exception;

    /**
     * 查询自定节点类目的类容信息
     *
     * @return 该id类目的基本信息
     */
    ProductType getType(long id) throws Exception;

    /**
     * 保存分类信息
     *
     * @param productType
     * @return 新增分类信息
     * @throws Exception 失败时统一跑出异常
     */
    int addType(ProductType productType) throws InputParamsNullException, Exception;

    /**
     * 保存分类信息
     *
     * @param productType
     * @return 编辑分类信息
     * @throws Exception 失败时统一跑出异常
     */
    int editerType(ProductType productType) throws InputParamsNullException, Exception;

    /**
     * 功能说明：
     * 删除指定路径下的所有分类， 即删除之类类目， 包括子类目 一同删除
     *
     * @param path 要删除的类目的路径
     * @return 影响的记录行数
     * @throws Exception 一般性异常外抛处理
     */
    int deleteCascadeType(String path) throws InputParamsNullException, Exception;

    /**
     * 分页获取品牌内容
     * @param page 分页信息
     * @return 分页结果信息
     * @throws InputParamsNullException
     * @throws Exception
     */
    PageResult getBrand(PageResult page) throws InputParamsNullException, Exception;

    /**
     * 新增品牌信息
     * @param brand  品牌基本新
     * @return  影响记录数
     */
    int addBrand(Brand brand) throws InputParamsNullException, Exception;

    /**
     * 保存品牌信息
     *
     * @param brand
     * @return 编辑品牌信息
     * @throws Exception 失败时统一抛出异常
     */
    int editerBrand(Brand brand) throws InputParamsNullException, Exception;

    /**
     * 通过id删除品牌
     * @param id  品牌号
     * @return  影响记录数
     * @throws InputParamsNullException
     * @throws Exception
     */
    int delBrand(String id)throws InputParamsNullException, Exception;

}
