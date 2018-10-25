package com.jb.test.pdt;

import com.alibaba.fastjson.JSON;
import com.jb.core.base.PageResult;
import com.jb.dao.BrandMapper;
import com.jb.entity.Brand;
import com.jb.facade.service.BrandCenterService;
import com.jb.service.facade.impl.ProductTypeCenterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 27654 on 2018/10/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service-product.xml"})
public class TestBrand {
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private BrandCenterService productCenterService;
    @Test
    public  void testGetByPageMapper() throws  Exception{
        Map search = new HashMap<String,Object>();
        search.put("name","耐");
        search.put("fristletter","N");
       PageResult page = new PageResult(1,10);
       page.setSearch(search);
        List<Brand> brands = brandMapper.get(page);
        System.err.println(JSON.toJSONString(brands));
    }

    @Test
    public  void  getRow()throws  Exception{
        long rows = brandMapper.getAllRows();
        System.err.println("rows"+rows);
    }

    /**
     * 分页获取
     * @throws Exception
     */
    @Test
    public  void testGetByPageService() throws  Exception{
        Map search = new HashMap<String,Object>();
        search.put("name","耐");
        search.put("fristletter","N");
        PageResult page = new PageResult(1,10);
        page.setSearch(search);
        PageResult brand = productCenterService.getBrand(page);
        System.err.println(JSON.toJSONString(brand));
    }


    @Test
    public void testGetByName()throws Exception{
        String name = "华";
        List<Brand> list = brandMapper.getBrandByName(name);
        System.err.println("结果：" + JSON.toJSONString(list));
    }


}
