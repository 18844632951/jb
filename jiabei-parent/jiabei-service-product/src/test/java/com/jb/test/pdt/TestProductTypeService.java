package com.jb.test.pdt;

import com.alibaba.fastjson.JSON;
import com.jb.entity.ProductType;
import com.jb.service.facade.impl.ProductTypeCenterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 商品分类测试类
 * Created by 27654 on 2018/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service-product.xml"})
public class TestProductTypeService {
    @Autowired
    private ProductTypeCenterServiceImpl productCenterService;
    //private ProductTypeMapper mapper;
    @Test
    public void testGetTypeByPid() throws Exception {
        List<ProductType> types = productCenterService.getTypes(0);
        System.err.println(JSON.toJSONString(types));
    }


}
