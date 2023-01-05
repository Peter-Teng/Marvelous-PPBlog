package com.marvelouspp.ppblog.service;

import java.math.BigInteger;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Link;

public interface LinkService extends IService<Link> {
    public ResponseObject<?> getLinks();

    public ResponseObject<?> createLink(Link link);

    public ResponseObject<?> modifyLink(Link link);

    public ResponseObject<?> deleteLink(BigInteger id);
}
