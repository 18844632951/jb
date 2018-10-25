package com.jb.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.jb.core.base.AjaxResult;
import com.jb.core.base.PageResult;
import com.jb.core.base.RequestPath;
import com.jb.core.dd.DataDictionary;
import com.jb.core.exception.InputParamsNullException;
import com.jb.core.io.FastDFSUtil;
import com.jb.entity.Brand;
import com.jb.facade.service.BrandCenterService;
import com.jb.log.Log;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 品牌管理控制器
 * Created by 27654 on 2018/10/19.
 */
@Controller
@RequestMapping("/" + BrandController.DOMAIN)
public class BrandController {
    public static final String DOMAIN = "brand";

    @Reference(check = false)
    private BrandCenterService brandCenterService;

    @Value("${fileServerIP}")
    private String fileServerIP;     //文件服务器IP地址

    /**
     * 分页查找品牌数据
     *
     * @param row    当前页
     * @param length 页长
     * @param search 搜索条件
     * @return Ajax 统一格式返回结果集
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.RESTFUL + RequestPath.JSON, method = RequestMethod.GET)
    public String get(@PathVariable("value") Long row, @RequestParam(value = "length", required = false) Integer length,
                      @RequestParam(required = false) Map<String, Object> search) {
        AjaxResult ajRs = null;

        try {
            System.err.println("SEARCH == " + JSON.toJSONString(search));
            PageResult page = new PageResult(row, length, search);
            page = brandCenterService.getBrand(page);
            ajRs = new AjaxResult(DataDictionary.OK, page);
        } catch (InputParamsNullException e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.PARAMS_NULL, "err/404").toJSON();
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.NET_BAD, "err/500").toJSON();
        }

        return ajRs.toJSON();
    }

    /**
     * 文件上传接口定义
     *
     * @param file 文件流
     * @return 图片绝对地址
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.UPLOAD, method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file) {

        AjaxResult ajRs = null;

        if (file == null || file.isEmpty()) {
            return new AjaxResult(DataDictionary.PARAMS_NULL).toJSON();
        }
        try {
            FastDFSUtil dfsUtil = new FastDFSUtil();
            String path = dfsUtil.uploadFile(file.getOriginalFilename(), file.getBytes());
            String src = "http://" + fileServerIP + "/" + path;
            ajRs = new AjaxResult(DataDictionary.SUCCESS, src);
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.NET_BAD).toJSON();
        }

        return ajRs.toJSON();
    }

    /**
     * 更新保存品牌信息
     *
     * @param brand 品牌基本信息
     * @param url   将要删除的图片
     * @return AJAX统一格式结果集
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.SAVE, method = RequestMethod.POST)
    public String save(Brand brand, @RequestParam(value = "url", required = false) String url) {
        int row;
        FastDFSUtil dfsUtil = new FastDFSUtil();
        AjaxResult ajaxRs = null;
        if (brand == null) {
            return new AjaxResult(DataDictionary.PARAMS_NULL).toJSON();
        }
        try {
            if (brand.getId() == null || brand.equals("")) {
                row = brandCenterService.addBrand(brand);
            } else {
                row = brandCenterService.editerBrand(brand);
            }
            if (row > 0) {
                ajaxRs = new AjaxResult(DataDictionary.SUCCESS);
                if (url != null) {
                    String group = url.substring(url.indexOf("group"));
                    dfsUtil.delete_file(group);
                }
            } else {
                ajaxRs = new AjaxResult(DataDictionary.FAILED);
            }
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            if (brand.getLogo() != null) {
                try {
                    dfsUtil.delete_file(brand.getLogo());   //删除文件
                } catch (IOException e1) {
                    Log.logger.error(e.getMessage(), e);
                } catch (MyException e1) {
                    Log.logger.error(e.getMessage(), e);
                }
            }
            ajaxRs = new AjaxResult(DataDictionary.NET_BAD);
        }

        return ajaxRs.toJSON();
    }

    /**
     * 删除指定id的品牌
     *
     * @param id 删除的id
     * @return 统一结果集
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.RESTFUL + RequestPath.DELET, method = RequestMethod.POST)
    public String delete(@PathVariable("value") Long id, @RequestParam("url") String url) {
        AjaxResult ajaxRs;
        System.err.println("URL === " + id);
        if (id == null) {
            return new AjaxResult(DataDictionary.PARAMS_NULL).toJSON();
        }
        try {
            int row = brandCenterService.delBrand(id);
            if (row > 0) {
                if (url != null) {      //删除图片
                    String group = url.substring(url.indexOf("group"));
                    new FastDFSUtil().delete_file(group);
                }
                ajaxRs = new AjaxResult(DataDictionary.SUCCESS);
            } else {
                ajaxRs = new AjaxResult(DataDictionary.FAILED);
            }
        } catch (InputParamsNullException e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.NET_BAD).toJSON();
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.NET_BAD).toJSON();
        }

        return ajaxRs.toJSON();
    }

    @ResponseBody
    @RequestMapping(value = RequestPath.RESTFUL + RequestPath.SEARCH, method = RequestMethod.GET)
    public String getBrand(@PathVariable("value") String name) {
        AjaxResult ajaxRs = null;

        try {
            if(name == null || name.equals("")){
                return new AjaxResult(DataDictionary.PARAMS_NULL).toJSON();
            }
            List<Brand> brands = brandCenterService.getBrands(name);
            ajaxRs = new AjaxResult(DataDictionary.SUCCESS, brands);
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            ajaxRs = new AjaxResult(DataDictionary.NET_BAD);
        }

        return ajaxRs.toJSON();
    }
}
