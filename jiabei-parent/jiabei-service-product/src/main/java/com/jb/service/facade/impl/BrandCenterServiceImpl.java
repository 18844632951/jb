package com.jb.service.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jb.core.base.PageResult;
import com.jb.core.dd.DataDictionary;
import com.jb.core.exception.InputParamsNullException;
import com.jb.dao.BrandMapper;
import com.jb.entity.Brand;
import com.jb.facade.service.BrandCenterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 品牌管理中心业务处理
 * Created by 邓水洪 on 2018/10/22.
 */
@Service
public class BrandCenterServiceImpl implements BrandCenterService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查找
     *
     * @param page 分页信息
     * @return 分页结果
     */
    @Override
    public PageResult getBrand(PageResult page) throws InputParamsNullException, Exception {
        if (page == null) {
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }

        List<Brand> brands = brandMapper.get(page);  //分页查询
        long rows = brandMapper.getAllRows();   //获取总记录数
        System.err.println("brads" + rows);
        page.setData(brands);   //数据
        long size = (rows - 1) / page.getLength() + 1;
        page.setSize(size); //总页数

        return page;
    }

    /**
     * 新增品牌
     * @param brand  品牌基本新
     * @return  影响记录数
     * @throws InputParamsNullException
     * @throws Exception
     */
    @Override
    public int addBrand(Brand brand) throws InputParamsNullException, Exception {
        int rows;

        if(brand == null){
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }
        Date date = new Date();
        long time = date.getTime();
        //brand.setId(profix);      //自增
        brand.setFristletter(brand.getFristletter().toUpperCase()); //统一大写
        brand.setCreatetime(time);
        brand.setUpdatetime(time);
        rows = brandMapper.insertSelective(brand);

        return rows;
    }

    /**
     * 编辑品牌信息
     * @param brand 品牌信息
     * @return  影响记录数
     * @throws InputParamsNullException
     * @throws Exception
     */
    @Override
    public int editerBrand(Brand brand) throws InputParamsNullException, Exception {
        if(brand == null || brand.getId()== null || brand.getId().equals("")){
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除指定品牌品牌
     * @param id  品牌号
     * @return 影响记录数
     * @throws InputParamsNullException
     * @throws Exception
     */
    @Override
    public int delBrand(Long id) throws InputParamsNullException, Exception {
        if(id == null){
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }

        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> getBrands(String name) throws InputParamsNullException, Exception {
        if(name == null || name.equals("")){
            throw  new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }
        return brandMapper.getBrandByName(name);
    }
}
