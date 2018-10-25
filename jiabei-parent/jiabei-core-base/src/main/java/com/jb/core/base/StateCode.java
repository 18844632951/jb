package com.jb.core.base;

/**
 * 数据状态码统一管理
 * Created by 27654 on 2018/10/24.
 */
public interface StateCode {
    public static final byte SJ = 1;   //上架
    public static final byte XJ = 0;   //下架
    public static final byte YC = 0;   //异常
    public static final byte ZC = 1;   //正常

}
