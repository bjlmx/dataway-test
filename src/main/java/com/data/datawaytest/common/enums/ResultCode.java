package com.data.datawaytest.common.enums;

/** @author Baijl
 * @date 2020-04-20
 * @time 11:23
 * @description
 */
/**
 * 响应码枚举，参考HTTP状态码的语义
 */

public enum ResultCode {
    //成功
    SUCCESS(200,"成功"),
    //失败
    FAIL(400,"失败"),
    //未认证（签名错误）
    UNAUTHORIZED(401,"签名错误"),
    //接口不存在
    NOT_FOUND(404,"接口不存在"),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500,"服务器内部错误");

    public int code;
    public String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
