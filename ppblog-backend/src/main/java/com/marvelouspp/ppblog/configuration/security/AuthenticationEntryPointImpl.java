package com.marvelouspp.ppblog.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.enums.Code;


@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
                authException.printStackTrace();

        ResponseObject<?> result = null;
        if(authException instanceof BadCredentialsException) {
            result = ResponseObject.failure(Code.NAME_OR_PASSWORD_ERROR);
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseObject.failure(Code.NO_AUTH);
        } else {
            result = ResponseObject.failure(Code.SYSTEM_ERROR, "System Error!");
        }
        ResponseObject.renderResult(response, JSON.toJSONString(result));
    }
    
}
