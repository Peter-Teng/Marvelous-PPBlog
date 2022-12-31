package com.marvelouspp.ppblog.controller.general;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.User;
import com.marvelouspp.ppblog.service.UserService;

@RestController
@RequestMapping("/general/user")
public class GeneralUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    @SystemLog(businessName = "Public:获取用户公开信息")
    public ResponseObject<?> info() {
        return userService.info();
    }

    @PostMapping("/login")
    @SystemLog(businessName = "登录接口")
    public ResponseObject<?> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    @SystemLog(businessName = "注册接口")
    public ResponseObject<?> register(@RequestBody User user) {
        return userService.register(user);
    }
}
