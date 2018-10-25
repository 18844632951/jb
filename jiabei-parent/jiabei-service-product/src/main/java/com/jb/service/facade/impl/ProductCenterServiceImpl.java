package com.jb.service.facade.impl;

import com.jb.core.base.PageResult;
import com.jb.core.base.StateCode;
import com.jb.core.dd.DataDictionary;
import com.jb.core.exception.InputParamsNullException;
import com.jb.dao.ProductExtMapper;
import com.jb.dao.ProductMapper;
import com.jb.dao.ProductMediaMapper;
import com.jb.entity.Product;
import com.jb.entity.ProductExt;
import com.jb.entity.ProductMedia;
import com.jb.facade.service.ProductCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 商品管理模块对外发布接口实现
 * Created by 邓水洪 on 2018/10/24.
 */
public class ProductCenterServiceImpl implements ProductCenterService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private ProductMediaMapper productMediaMapper;

    @Override
    public PageResult get(PageResult page) throws InputParamsNullException, Exception {
        return null;
    }

    @Override
    public Product get(String id) throws InputParamsNullException, Exception {
        return null;
    }

    /**
     * 商品发布具体业务实现
     *
     * @param product      商品基本信息
     * @param productExt   商品详细信息
     * @param productMedia 商品媒体信息
     * @throws InputParamsNullException
     * @throws Exception
     */
    @Transactional
    @Override
    public void add(Product product, ProductExt productExt, ProductMedia productMedia) throws InputParamsNullException, Exception {
        if (product == null || productExt == null && productMedia == null) {
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }
        Date time = new Date();
        //模拟商家
        String storeId = "jb88888";
        //1. 商品基本信息添加
        product.setState(StateCode.XJ);
        product.setStoreId(storeId);
        productMapper.insertSelective(product);
        //2. 商品详情添加
        productExt.setCreatetime(time.getTime());
        productExt.setUpdatetime(time.getTime());
        productExt.setProductid(product.getId());
        productExtMapper.insertSelective(productExt);
        //3. 商品媒体信息添加
        productMedia.setProductid(product.getId());
//      productMedia.set();



    }

    @Override
    public int update(Product product, ProductExt productExt, ProductMedia productMedia) throws InputParamsNullException, Exception {
        return 0;
    }

    @Override
    public int delete(String productId) throws InputParamsNullException, Exception {
        return 0;
    }
}
