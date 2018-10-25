package com.jb.core.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Map;

/**
 * 分页插件 数据包装
 * Created by 27654 on 2018/10/19.
 */
public class PageResult implements Serializable {

    private long size;  //  总页数

    private long row;   //当前页数

    private int length = 10;    //分页大小

    private long start = 0;      //开始页

    private Map<String, Object> search;  //查询条件

    private Object data;  //分页查询数据结果集

    public PageResult() {
    }

    public PageResult(long row, Integer length) {
        this.row = row > 0 ? row : 1;
        this.length = length == null ? 10 : length;
    }

    public PageResult(long row, Integer length, Map<String, Object> search) {
        this.row = row > 0 ? row : 1;
        this.length = length == null ? 10 : length;
        this.search = search;
    }

    public PageResult(long size, long row, Integer length, Map<String, Object> search, Object data) {
        this.size = size;
        this.row = row > 0 ? row : 1;
        this.length = length == null ? 10 : length;
        this.search = search;
        this.data = data;
    }

    public long getStart() {
        if(this.row >= 0 && this.length > 0){
            this.start = (this.row-1) * this.length;
        }else{
            this.start = 0;
        }
        return this.start;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length > 1 ? length : 1;
    }

    public Map<String, Object> getSearch() {
        return search;
    }

    public void setSearch(Map<String, Object> search) {
        this.search = search;
    }

    public long getRow() {
        return row;
    }

    public void setRow(long row) {
        this.row = row > 0 ? row : 1;
    }

    public Object getData() {
        return JSON.toJSONString(data);
    }

    public void setData(Object data) {
        this.data = data;
    }
}
