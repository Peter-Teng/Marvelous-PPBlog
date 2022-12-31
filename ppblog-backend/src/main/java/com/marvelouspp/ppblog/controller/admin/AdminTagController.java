package com.marvelouspp.ppblog.controller.admin;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Tag;
import com.marvelouspp.ppblog.service.TagService;

@RestController
@RequestMapping("/admin/tag")
public class AdminTagController {
    
    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    @SystemLog(businessName = "Admin:新建标签")
    public ResponseObject<?> createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @PutMapping("/modify")
    @SystemLog(businessName = "Admin:修改标签")
    public ResponseObject<?> modifyTag(@RequestBody Tag tag) {
        return tagService.modifyTag(tag);
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "Admin:删除标签")
    public ResponseObject<?> deleteTag(@PathVariable BigInteger id) {
        return tagService.deleteTag(id);
    }

}
