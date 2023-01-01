package com.marvelouspp.ppblog.configuration;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.domain.exceptions.SystemException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseObject<?> systemExceptionHandler(SystemException e){
        log.error("An Error has happened!", e);
        //从异常对象中获取提示信息封装返回
        return ResponseObject.failure(e.getCode());
    }


    @ExceptionHandler(Exception.class)
    public ResponseObject<?> exceptionHandler(Exception e){
        //从异常对象中获取提示信息封装返回
        log.error("An Error has happened!", e);
        return ResponseObject.failure(Code.SYSTEM_ERROR, e.getMessage());
    }
}
