package com.data.datawaytest.common;

/** @author Baijl
 * @date 2020-04-20
 * @time 11:28
 * @description
 */

import com.data.datawaytest.common.enums.ResultCode;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static Result genSuccessResult() {
        return new Result(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage());
    }

    public static Result genSuccessResult(Object data) {
        return new Result(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);

    }

    public static Result genFailResult(String message) {

        Result result = new Result(ResultCode.FAIL.getCode(), message);
        return result;
    }
}
