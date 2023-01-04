package com.marvelouspp.ppblog.service.v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Link;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.mapper.LinkMapper;
import com.marvelouspp.ppblog.service.LinkService;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseObject<?> getLinks() {
        List<Link> navigations = list();
        return ResponseObject.success(navigations);
    }

    @Override
    public ResponseObject<?> createLink(Link link) {
        boolean success = saveOrUpdate(link);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> modifyLink(Link link) {
        boolean success = updateById(link);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> deleteLink(BigInteger id) {
        boolean success = removeById(id);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }
    
}
