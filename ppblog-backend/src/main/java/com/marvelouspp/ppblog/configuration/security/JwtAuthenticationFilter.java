package com.marvelouspp.ppblog.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.LoginUser;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.utils.JWTUtils;
import com.marvelouspp.ppblog.utils.RedisUtils;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(Constant.JWT_HEADER);
        if(!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = null;
        try {
            claims = JWTUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObject<?> result = ResponseObject.failure(Code.TOKEN_ISSUE);
            ResponseObject.renderResult(response, JSON.toJSONString(result));
            return;
        }
        String username = claims.getSubject();
        LoginUser user = redisUtils.getCacheObject(String.format(Constant.USER_LOGIN_OBJECT, username));
        if(user == null) {
            ResponseObject<?> result = ResponseObject.failure(Code.TOKEN_ISSUE);
            ResponseObject.renderResult(response, JSON.toJSONString(result));
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
    
}
