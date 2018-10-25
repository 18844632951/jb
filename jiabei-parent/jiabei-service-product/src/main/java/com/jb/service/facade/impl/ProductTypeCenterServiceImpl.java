package com.jb.service.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jb.core.dd.DataDictionary;
import com.jb.core.exception.InputParamsNullException;
import com.jb.dao.ProductTypeMapper;
import com.jb.entity.ProductType;
import com.jb.facade.service.ProductTypeCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 对外服务实现
 * Created by 27654 on 2018/10/17.
 */
@Service
public class ProductTypeCenterServiceImpl implements ProductTypeCenterService {

    @Autowired
    private ProductTypeMapper typeMapper;

    /**
     * 采用父节点查找树结构
     *
     * @param pid 父节点id
     * @return 树状结构的数据结构
     * @throws Exception
     */
    @Override
    public List<ProductType> getTypes(long pid) throws Exception {
        List<ProductType> tree = null;  //树状结构的链表
        List<ProductType> list = null;
        //1. 拿到pid 的所有节点
        if (pid != 0) {
            list = typeMapper.getTypeByPid(pid);
        } else {
            list = typeMapper.getTypeAll();
        }

        //改进方案
        Map<Long, ProductType> map = new HashMap<>();
        for (ProductType productType : list) {
            map.put(productType.getId(), productType);
        }
        tree = new ArrayList<>();
        for (ProductType productType : list) {
            if (productType.getPid().equals(pid)) { //是父节点
                tree.add(productType);
            } else {  //说明不是父节点， 是子节点
                ProductType parentType = map.get(productType.getPid()); //从map 中找
                /*
                  时间复杂度 O(n^2)
                  改进方案： 使用空间换取时间  采用Map 结构
                 long parentId = productType.getPid();
                 for(ProductType child : list){
                     if(child.getId() == parentId){ //如果当前的节点的父类目是遍历类目的ID
                         parentType = child;
                         break;
                     }
                 }*/
                parentType.getChildren().add(productType);    //将找到的节点填进去
            }
        }

        return tree;
    }

    @Override
    public ProductType getType(long id) throws Exception {

        return typeMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增分类业务
     * 业务流程：  1.  插入分类基本信息， 2 跟新分类路径path
     *
     * @param productType 更新类目内容
     * @return 更新影响的记录数
     * @throws Exception                全局异常
     * @throws InputParamsNullException 入参为空异常
     */
    @Override
    @Transactional
    public int addType(ProductType productType) throws InputParamsNullException, Exception {
        int rows = 0;

        if (productType == null) {
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }
        productType.setTotalcount(0);   //设置默认计数为 0
        long time = System.currentTimeMillis();
        productType.setCreatetime(time);  //设置创建时间
        productType.setUpdatetime(time);  //设置更新时时间
        typeMapper.insertSelective(productType);
        //TODO
        System.err.println("PAth = " + productType.getPath());
        String path;            //组装path 类目路径
        ProductType newObj = new ProductType(); //创建新的对象   更新路径
        long id = productType.getId();  //获取新增后的id

        if (productType.getPath() == null || productType.getPath().equals("")) {
            newObj.setPid(0l);              //父级为0
            path = "." + id + ".";       //顶级分类目录
        } else {
            path = productType.getPath() + id + ".";
        }

        newObj.setId(id);
        newObj.setPath(path);
        rows = typeMapper.updateByPrimaryKeySelective(newObj);

        return rows;
    }

    /***
     * 编辑分类信息
     * @param productType
     * @return 更新记录数
     * @throws InputParamsNullException  入参为空异常
     * @throws Exception  一般异常
     */
    @Override
    public int editerType(ProductType productType) throws InputParamsNullException, Exception {
        int rows = 0;
        if (productType == null) {
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }
        if (productType.getId() == null || productType.getId().equals("")) {
            throw new InputParamsNullException(DataDictionary.UPDATE_ID_NULL.getDescribe());
        }
        rows = typeMapper.updateByPrimaryKeySelective(productType);
        return rows;
    }

    /**
     * 删除类目，可能涉及批量操作
     *
     * @param path 要删除的类目的路径
     * @return 记录数
     * @throws InputParamsNullException 入参为空异常
     * @throws Exception
     */
    @Override
    @Transactional
    public int deleteCascadeType(String path) throws InputParamsNullException, Exception {
        if (path == null) {
            throw new InputParamsNullException(DataDictionary.PARAMS_NULL.getDescribe());
        }
        int rows = typeMapper.deleteCascade(path);
        return rows;
    }
}
