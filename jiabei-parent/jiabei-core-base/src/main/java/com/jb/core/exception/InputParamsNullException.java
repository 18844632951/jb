package com.jb.core.exception;

/**
 * 参数为空异常！！  报错 值为NUll 和 ‘’两种情况
 * @author 番茄一号
 * Created by Dengsh on 2018/10/18.
 */
public class InputParamsNullException extends  Exception {

    public InputParamsNullException() {
    }

    public InputParamsNullException(String message) {
        super(message);
    }
}
