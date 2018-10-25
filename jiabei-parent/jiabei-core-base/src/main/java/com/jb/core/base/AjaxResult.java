package com.jb.core.base;

import com.alibaba.fastjson.JSON;
import com.jb.core.dd.DataDictionary;

import java.io.Serializable;

/**
 * ajax 统一的返回类型格式
 * Created by 27654 on 2018/10/13.
 */
public class AjaxResult implements Serializable{
    /**
     * 状态码
     *  200 ：正常，
     *  400 ：失败，
     *  500 :服务器异常，
     *  5005： 超时为响应
     */
    private int state;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 请求结果（响应数据结果集）
     */
    private Object data;

    public AjaxResult() {}

    public AjaxResult(DataDictionary dd) {
        this.state = dd.getKeyword();
        this.msg = dd.getDescribe();
    }

    public AjaxResult(DataDictionary dd, Object data) {
        this(dd);
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
