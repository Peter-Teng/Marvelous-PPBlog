package com.marvelouspp.ppblog.service.serviceImpl_v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.marvelouspp.ppblog.domain.entity.LoginUser;
import com.marvelouspp.ppblog.domain.entity.User;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.domain.exceptions.SystemException;
import com.marvelouspp.ppblog.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null) {
            throw new SystemException(Code.NAME_OR_PASSWORD_ERROR);
        }

        return new LoginUser(user);
    }
    
}
