package com.marvelouspp.ppblog.service.v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Link;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.mapper.LinkMapper;
import com.marvelouspp.ppblog.service.LinkService;
import com.marvelouspp.ppblog.utils.RedisUtils;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Autowired
    RedisUtils redisUtils;

    @Override
    public ResponseObject<?> getLinks() {
        List<Link> links = redisUtils.getCacheList(Constant.LINKS_CACHE);
        if(links.size() == 0) {
            links = list();
            redisUtils.setCacheList(Constant.LINKS_CACHE, links);   
        }   
        return ResponseObject.success(links);
    }

    @Override
    public ResponseObject<?> createLink(Link link) {
        boolean success = saveOrUpdate(link);
        if(success) {
            redisUtils.deleteObject(Constant.LINKS_CACHE);
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> modifyLink(Link link) {
        boolean success = updateById(link);
        if(success) {
            redisUtils.deleteObject(Constant.LINKS_CACHE);
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> deleteLink(BigInteger id) {
        boolean success = removeById(id);
        if(success) {
            redisUtils.deleteObject(Constant.LINKS_CACHE);
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }
    
}
