package com.marvelouspp.ppblog.service;

import java.math.BigInteger;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Tag;

public interface TagService extends IService<Tag>{
    public ResponseObject<?> getTags();

    public ResponseObject<?> createTag(Tag tag);

    public ResponseObject<?> modifyTag(Tag tag);

    public ResponseObject<?> deleteTag(BigInteger id);
}
