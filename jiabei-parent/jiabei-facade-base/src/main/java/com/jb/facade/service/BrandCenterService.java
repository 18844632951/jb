package com.jb.facade.service;

import com.jb.core.base.PageResult;
import com.jb.core.exception.InputParamsNullException;
import com.jb.entity.Brand;

import java.util.List;

/**
 * 品牌管理中心 对外服务接口定义
 * Created by 邓水洪 on 2018/10/22.
 */
public interface BrandCenterService {

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
    int delBrand(Long id)throws InputParamsNullException, Exception;

    /**
     * 通过名称模糊查询品牌
     * @param name 名称
     * @return 匹配的品牌
     * @throws InputParamsNullException
     * @throws Exception
     */
    List<Brand> getBrands(String name) throws  InputParamsNullException, Exception;
}
