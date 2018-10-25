package com.jb.core.dd;

/**
 * 全局的数据字典 各类状态码关键字如下：
 *  成功码： 20*   响应成功
 *  失败码: 40*  客户端错误
 *  警告码：30*  转向、警告等
 *  服务器异常码：50*  服务器错误
 *  文件码： 60*    文件错误
 * Created by 27654 on 2018/10/13.
 */
public enum DataDictionary {
    SUCCESS(200, "操作成功!"),
    OK(201,"正常"),
    WARNING(300, "警告"),
    FAILED(400, "操作失败！"),
    ERROR(401, "错误"),
    PARAMS_NULL(402,"参数为空，请检验数据是否完整！"),
    UPDATE_ID_NULL(404,"更新记录的主键丢失！"),
    INFO_TAMPED(403,"信息篡改"),
    UNDEFINED(404,"您访问的资源没有找到"),
    NO_POWER(403,"无权访问的资源"),
    NET_BAD(500, "网络异常"),
    SERVER_BAD(505, "服务器异常"),
    FILE_TOO_LONG(600,"文件过大"),
    FILE_NULL(601,"文件内容为空，请重新选择图片！");

    /**
     * 关键字
     */
    private int keyword;
    /**
     * 描述
     */
    private String describe;

    DataDictionary(int keyword, String describe) {
        this.keyword = keyword;
        this.describe = describe;
    }

    /**
     * 通过关键字查找词典
     *
     * @param keyword 关键字
     * @return 一个字典内容
     */
    public static DataDictionary indexOf(int keyword) {
        for (DataDictionary dd : DataDictionary.values()) {
            if (keyword == dd.keyword) {
                return dd;
            }
        }
        return null;
    }

    public int getKeyword() {
        return keyword;
    }

    public String getDescribe() {
        return describe;
    }
}
