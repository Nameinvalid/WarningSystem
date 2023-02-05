package com.Ice.WarningSystem.result;

import org.springframework.http.HttpStatus;

/**
 * 业务异常信息的描述
 *
 * @author galaxy
 * @date 2019/10/05 16:19
 */
public enum ResultStatus {

    SUCCESS(HttpStatus.OK, 200, "OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error"),
    FAIL1(2001,"数据不存在"),
    FAIL2(3001,"传入参数错误"),
    FAIL3(4004,"查询对应的静态资源在服务器上没有找到"),
    FAIL4(5000,"查询对应的资源服务端发生错误"),
    FAIL5(5001,"在后台进行数据处理时发生错误"),
    FAIL6(6000,"给定的Token错误"),
    FAIL7(6001,"给定的Token已失效"),
    FAIL8(3002,"给定参数无数据"),
    FAIL9(9000,"未到达考试开始时间");


    /** 返回的HTTP状态码,  符合http请求 */
    private HttpStatus httpStatus;
    /** 业务异常码 */
    private Integer code;
    /** 业务异常信息描述 */
    private String message;

    ResultStatus(HttpStatus httpStatus, Integer code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}