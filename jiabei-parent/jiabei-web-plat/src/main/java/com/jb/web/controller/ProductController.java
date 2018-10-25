package com.jb.web.controller;

import com.jb.core.dd.DataDictionary;
import com.jb.entity.Product;
import com.jb.entity.ProductExt;
import com.jb.entity.ProductMedia;
import com.jb.log.Log;
import com.jb.core.base.AjaxResult;
import com.jb.core.base.RequestPath;
import com.jb.core.io.FastDFSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品中心 商品控制器
 * Created by 27654 on 2018/10/12.
 */
@Controller
@RequestMapping("/" + ProductController.BASE)
public class ProductController {
    public static final String BASE = "product";

    //  @Value("#{fdfs_ip}")
    private String basePath = "192.168.150.132";        //读取文件服务器IP地址

    @RequestMapping(RequestPath.ADD)
    @ResponseBody
    public String add(Product product) {
        return null;
    }

    /**
     * 多/单 文件上传工作
     *
     * @param file 文件流
     * @return AjaxResulet JSON格式结果集
     */
    @RequestMapping(value = RequestPath.UPLOAD, method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String upload(MultipartFile[] file) {
        List<String> paths;     //保存文件地址
        AjaxResult aRs;
        FastDFSUtil fast;

        paths = new ArrayList<>();
        if (file == null || file.length == 0) {
            aRs = new AjaxResult(DataDictionary.FILE_NULL);
        } else {
            fast = new FastDFSUtil();
            try {
                for (MultipartFile mf : file) {
                    String path = fast.uploadFile(mf.getOriginalFilename(), mf.getBytes());
                    paths.add("http://" + basePath + "/" + path);  //拼接完整地址：uri =http:// + IP + fastFds文件ID
                }
                aRs = new AjaxResult(DataDictionary.SUCCESS, paths);
            } catch (Exception e) {
                Log.logger.error(e.getMessage(), e);
                aRs = new AjaxResult(DataDictionary.NET_BAD);

                return aRs.toJSON();
            } finally {
                fast.close();
            }
        }

        return aRs.toJSON();
    }

    /**
     * 新增商品信息
     * @param productExt 商品详细信息
     *  @param  productMedia  媒体资源
     * @param product 商品基本信息
     * @return 统一响应结果集
     */
    @ResponseBody
    @RequestMapping(value = RequestPath.SAVE, method = RequestMethod.POST)
    public String save(Product product, ProductExt productExt, ProductMedia productMedia) {
        AjaxResult ajaxRs = null;
        try {
            if (product == null) {
                return new AjaxResult(DataDictionary.PARAMS_NULL).toJSON();
            }
            //新增商品
            if (product.getId() == null || product.getId().equals("")) {
                //TODO
                ajaxRs = new AjaxResult(DataDictionary.SUCCESS);
            } else {    //更新商品信息
                //TODO
                ajaxRs = new AjaxResult(DataDictionary.SUCCESS);
            }
        } catch (Exception e) {
            Log.logger.error(e.getMessage(), e);
            return new AjaxResult(DataDictionary.NET_BAD).toJSON();
        }

        return ajaxRs.toJSON();
    }

}
