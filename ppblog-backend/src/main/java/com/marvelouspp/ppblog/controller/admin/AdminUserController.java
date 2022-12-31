package com.marvelouspp.ppblog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.User;
import com.marvelouspp.ppblog.service.UserService;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/logout")
    @SystemLog(businessName = "Admin:退出登录")
    public ResponseObject<?> logout() {
        return userService.logout();
    }

    @PutMapping("/info")
    @SystemLog(businessName = "Admin:修改用户信息")
    public ResponseObject<?> updateInfo(@RequestBody User user) {
        return userService.updateInfo(user);
    }
}
