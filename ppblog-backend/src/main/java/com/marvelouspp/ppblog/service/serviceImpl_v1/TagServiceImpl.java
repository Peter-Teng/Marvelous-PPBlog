package com.marvelouspp.ppblog.service.serviceImpl_v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Tag;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.mapper.TagMapper;
import com.marvelouspp.ppblog.service.TagService;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseObject<?> getTags() {
        List<Tag> tags = list();
        return ResponseObject.success(tags);
    }

    @Override
    public ResponseObject<?> createTag(Tag tag) {
        boolean success = saveOrUpdate(tag);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> modifyTag(Tag tag) {
        boolean success = updateById(tag);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> deleteTag(BigInteger id) {
        boolean success = removeById(id);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }
    
}
