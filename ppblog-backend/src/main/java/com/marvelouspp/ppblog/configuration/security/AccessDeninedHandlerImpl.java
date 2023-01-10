package com.marvelouspp.ppblog.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.enums.Code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccessDeninedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        log.warn("[WARN] =======ACCESS DENINED=======");
        log.warn("IP             : {}", request.getRemoteHost());
        log.warn("URL            : {}", request.getRequestURL());
        log.warn("=======End=======" + System.lineSeparator());

        ResponseObject<?> result = ResponseObject.failure(Code.NO_AUTH);
        ResponseObject.renderResult(response, JSON.toJSONString(result));
    }
    
}
