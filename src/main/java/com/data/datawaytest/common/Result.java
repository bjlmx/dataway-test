package com.data.datawaytest.common;

/** @author Baijl
 * @date 2020-04-20
 * @time 11:20
 * @description
 */
/*
统一响应结果封装及生成工具
 */

import lombok.*;

/**
 * 统一API响应结果封装
 */
@Setter
@Getter
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
