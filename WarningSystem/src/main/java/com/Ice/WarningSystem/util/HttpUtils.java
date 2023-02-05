package com.Ice.WarningSystem.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.Ice.WarningSystem.http.HttpResultRewrite;
import com.Ice.WarningSystem.http.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * HTTP工具类
 *
 * @Author Liwei
 * @Date 2021-08-12 12:10
 */
public class HttpUtils {

    /**
     * 获取HttpServletRequest对象
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 输出信息到浏览器
     *
     * @param response
     * @param code
     * @param msg
     * @throws IOException
     */
    public static void print(HttpServletResponse response, int code, String msg) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        HttpResultRewrite result = ResultUtils.error(msg, code);
        String json = JSONObject.toJSONString(result);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

}
