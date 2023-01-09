package com.marvelouspp.ppblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Background;

public interface BackgroundService extends IService<Background>{

    public ResponseObject<?> getBackgrounds();

    public ResponseObject<?> modifyBackground(Background background);
    
}
