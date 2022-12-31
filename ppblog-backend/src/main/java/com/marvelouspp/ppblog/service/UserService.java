package com.marvelouspp.ppblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.User;

public interface UserService extends IService<User>{

    public ResponseObject<?> info();

    public ResponseObject<?> login(User user);

    public ResponseObject<?> register(User user);

    public ResponseObject<?> logout();

    public ResponseObject<?> updateInfo(User user);

}
