package com.Ice.WarningSystem.http;

/**
 * @Author 郭乐源
 * @Date 2022/7/22 11:00
 * @Version 1.0
 */

/**
 * 数据返回工具类
 */
public class ResultUtils {
    /**
     * 无参数返回
     *
     * @return
     */
    public static HttpResultRewrite success(){
        return new HttpResultRewrite<>(null,StatusCode.ERROR_CODE,null);
    }

    public static HttpResultRewrite success(String msg) {
        return new HttpResultRewrite(msg, StatusCode.SUCCESS_CODE, null);
    }

    /**
     * 返回带参数
     *
     * @param msg
     * @param data
     * @return
     */
    public static HttpResultRewrite success(String msg, Object data) {
        return new HttpResultRewrite(msg, StatusCode.SUCCESS_CODE, data);
    }

    public static HttpResultRewrite success(String msg, int code, Object data) {
        return new HttpResultRewrite(msg, code, data);
    }
    // public static ResultVo Vo(String msg, int code, Object data) {
    //     return new ResultVo(msg, code, data);
    // }

    /**
     * 错误返回
     *
     * @return
     */
    public static HttpResultRewrite error() {
        return new HttpResultRewrite(null, StatusCode.ERROR_CODE, null);
    }

    public static HttpResultRewrite error(String msg) {
        return new HttpResultRewrite(msg, StatusCode.ERROR_CODE, null);
    }

    public static HttpResultRewrite error(String msg, int code, Object data) {
        return new HttpResultRewrite(msg, code, data);
    }

    public static HttpResultRewrite error(String msg, int code) {
        return new HttpResultRewrite(msg, code, null);
    }

    public static HttpResultRewrite error(String msg, Object data) {
        return new HttpResultRewrite(msg, StatusCode.ERROR_CODE, data);
    }
}