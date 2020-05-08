package com.data.datawaytest.exceptions;

import com.data.datawaytest.common.Result;
import com.data.datawaytest.common.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** @author Baijl
 * @date 2020-04-20
 * @time 11:52
 * @description 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕获处理
     * @param e
     * @return
     */
    /**
     表示捕获什么样的异常，如果是exception就是捕获全部异常
     这段代码的意思就是在controller层所有往外抛出的异常都会被在这里进行捕获并进行处理
     *
     */
    @ExceptionHandler(Exception.class)
    public Result catchException(Exception e) {
        log.error(e.getMessage(),e);
        Result result = ResultGenerator.genFailResult(e.getMessage());
        return result;
    }

}
