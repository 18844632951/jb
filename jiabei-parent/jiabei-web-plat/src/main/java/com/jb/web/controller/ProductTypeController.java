package com.jb.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.jb.core.base.AjaxResult;
import com.jb.core.dd.DataDictionary;
import com.jb.core.exception.InputParamsNullException;
import com.jb.entity.ProductType;
import com.jb.log.Log;
import com.jb.facade.service.ProductTypeCenterService;
import com.jb.core.base.RequestPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 * Created by 27654 on 2018/10/15.
 */
@Controller
@RequestMapping("/" + ProductTypeController.DOMAIN)
public class ProductTypeController {
    public static final String DOMAIN = "type";

    @Reference(check = false)
    private ProductTypeCenterService typeCenterService;

    /**
     * 获取所有的类目
     *
     * @return 具有一定结构的类目集合
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.JSON_TREE, method = RequestMethod.GET)
    public String getTypeTree() {
        List<ProductType> tree = null;
        try {
            tree = typeCenterService.getTypes(0);
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
        }
        return JSON.toJSONString(tree);
    }

    /**
     * 通过分类ID 获取分类列表
     *
     * @param value 类型ID
     * @return 类型基本信息
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.RESTFUL + RequestPath.GET, method = RequestMethod.GET)
    public String getTypeById(@PathVariable Long value) {
        ProductType type = null;

        try {
            if (value == null || value.equals("")) {
                throw new Exception("参数为空");
            }
            type = typeCenterService.getType(value);
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.ERROR).toJSON();
        }

        return JSON.toJSONString(type);
    }

    /**
     * 更新分类类容  注：  这里的保存指的是 添加和修改两个操作
     *
     * @param productType 更新内容
     * @return AjaxResult  页面统一响应结果
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.SAVE, method = RequestMethod.POST)
    public String saveType(ProductType productType) {
        int row;
        AjaxResult ajaxRs = null;
        try {
            if (productType == null || productType.getName() == null) {
                ajaxRs = new AjaxResult(DataDictionary.PARAMS_NULL);
                throw new Exception(ajaxRs.getMsg());
            }
            if (productType.getId() == null) {
                row = typeCenterService.addType(productType);   //新增分类并返回影响行数
            } else {
                row = typeCenterService.editerType(productType); //更新分类
            }
            if (row > 0) {
                ajaxRs = new AjaxResult(DataDictionary.SUCCESS);
            } else {
                ajaxRs = new AjaxResult(DataDictionary.FAILED);
            }
        } catch (InputParamsNullException e) {
            Log.logger.error(e.getMessage(), e);
            ajaxRs = new AjaxResult(DataDictionary.PARAMS_NULL);
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.NET_BAD).toJSON();
        }

        return ajaxRs.toJSON();
    }

    /**
     * 移除分类操作
     *
     * @param path 删除id
     * @return AjaxResult  页面统一响应结果
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.RESTFUL + RequestPath.DELET, method = RequestMethod.DELETE)
    public String delete(@PathVariable("value") String path) {
        AjaxResult ajaxRs = null;
        try {
            if (path == null || path.equals("")) {
                throw new Exception(ajaxRs.getMsg());
            }
            //查询分类path  TODO   完善删除查询path 业务
            int rows = typeCenterService.deleteCascadeType(path);
            if (rows > 0) {
                ajaxRs = new AjaxResult(DataDictionary.SUCCESS);
            } else {
                ajaxRs = new AjaxResult(DataDictionary.FAILED);
            }
        } catch (InputParamsNullException e1) {
            Log.logger.error(e1.getMessage(), e1);
            ajaxRs = new AjaxResult(DataDictionary.PARAMS_NULL);
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.NET_BAD).toJSON();
        }
        return ajaxRs.toJSON();
    }


}
