package com.marvelouspp.ppblog.service.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.configuration.CopyUtils;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.VO.TokenVo;
import com.marvelouspp.ppblog.domain.VO.UserProfileVo;
import com.marvelouspp.ppblog.domain.entity.LoginUser;
import com.marvelouspp.ppblog.domain.entity.User;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.domain.exceptions.SystemException;
import com.marvelouspp.ppblog.mapper.UserMapper;
import com.marvelouspp.ppblog.service.UserService;
import com.marvelouspp.ppblog.utils.JWTUtils;
import com.marvelouspp.ppblog.utils.RedisUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public ResponseObject<?> info() {
        UserProfileVo profile = null;
        profile = redisUtils.getCacheObject(Constant.USER_PROFILE);
        if(profile == null) {
            User user = getOne(null);
            if(user == null) {
                throw new RuntimeException("No users in the database");
            }
            profile = CopyUtils.copyBean(user, UserProfileVo.class);
            redisUtils.setCacheObject(Constant.USER_PROFILE, profile);
        }
        return ResponseObject.success(profile);
    }

    @Override
    public ResponseObject<?> login(User user) {
        boolean exist = count() > 0l;
        if (!exist) {
            throw new SystemException(Code.NO_USER);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(authenticate == null) {
            // 认证不通过
            throw new SystemException(Code.NAME_OR_PASSWORD_ERROR);
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String jwt = JWTUtils.createJWT(user.getUsername());

        redisUtils.setCacheObject(String.format(Constant.USER_LOGIN_OBJECT, user.getUsername()), loginUser);

        TokenVo token = new TokenVo(loginUser.getUserId(), loginUser.getUsername(), jwt);

        return ResponseObject.success(token);
    }

    @Override
    public ResponseObject<?> register(User user) {
        boolean exist = count() > 0l;
        if (exist) {
            throw new SystemException(Code.USER_EXIST);
        }
        if(!StringUtils.hasText(user.getUsername())){
            throw new SystemException(Code.PARAM_FAULT);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(Code.PARAM_FAULT);
        }
        String encodedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);
        boolean success = save(user);
        if(success) {
            return ResponseObject.success();
        } else {
            return ResponseObject.failure(Code.REGISTER_FAILURE);
        }
    }

    @Override
    public ResponseObject<?> logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取username
        String username = loginUser.getUsername();
        //删除redis中的用户信息
        redisUtils.deleteObject(String.format(Constant.USER_LOGIN_OBJECT, username));
        return ResponseObject.success();
    }

    @Override
    public ResponseObject<?> updateInfo(User user) {
        if(StringUtils.hasText(user.getPassword())) {
            String encodedPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPwd);
        } else {
            user.setPassword(null);
        }
        redisUtils.deleteObject(String.format(Constant.USER_LOGIN_OBJECT, user.getUsername()));
        redisUtils.deleteObject(Constant.USER_PROFILE);

        boolean success = updateById(user);
        if(success) {
            return ResponseObject.success();
        } else {
            return ResponseObject.failure(Code.UPDATE_FAILURE);
        }
    }
    
}

