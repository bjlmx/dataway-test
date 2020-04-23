package com.data.datawaytest.exceptions;

import com.data.datawaytest.common.Result;
import com.data.datawaytest.common.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
     * 方法参数校验
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(),e);
        Result result = ResultGenerator.genFailResult(e.getBindingResult().getFieldError().getDefaultMessage());
        return result;
    }

}
