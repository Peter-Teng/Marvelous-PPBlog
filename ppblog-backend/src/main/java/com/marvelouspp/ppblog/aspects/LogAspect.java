package com.marvelouspp.ppblog.aspects;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.marvelouspp.ppblog.annotations.SystemLog;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.marvelouspp.ppblog.annotations.SystemLog)")
    public void pt() {}

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            logBefore(joinPoint);
            ret = joinPoint.proceed();
            logAfter(ret);
        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }

    private void logBefore(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SystemLog SystemLog = signature.getMethod().getAnnotation(SystemLog.class);

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", SystemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature) joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", generateArgsString(joinPoint.getArgs()));
    }

    private String generateArgsString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for(Object arg : args) {
            sb.append(arg.toString());
        }
        return sb.toString();
    }

    private void logAfter(Object result) {
        // 打印结果
        log.info("Response       : {}", JSON.toJSONString(result));
    }
}
