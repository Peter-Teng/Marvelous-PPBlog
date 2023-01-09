package com.marvelouspp.ppblog.service.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Background;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.mapper.BackgroundMapper;
import com.marvelouspp.ppblog.service.BackgroundService;
import com.marvelouspp.ppblog.utils.RedisUtils;

@Service
public class BackgroundServiceImpl extends ServiceImpl<BackgroundMapper, Background> implements BackgroundService{

    @Autowired
    RedisUtils redisUtils;

    @Override
    public ResponseObject<?> getBackgrounds() {
        List<Background> bglist = redisUtils.getCacheObject(Constant.BACKGROUND_CACHE);
        if(bglist == null) {
            bglist = list();
            redisUtils.setCacheObject(Constant.BACKGROUND_CACHE, bglist);
        }
        return ResponseObject.success(bglist);
    }

    @Override
    public ResponseObject<?> modifyBackground(Background background) {
        boolean success = updateById(background);
        if(success) {
            redisUtils.deleteObject(Constant.BACKGROUND_CACHE);
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }
    
}
