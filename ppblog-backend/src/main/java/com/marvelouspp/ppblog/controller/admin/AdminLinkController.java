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
import com.marvelouspp.ppblog.domain.entity.Link;
import com.marvelouspp.ppblog.service.LinkService;

@RestController
@RequestMapping("/admin/link")
public class AdminLinkController {
    
    @Autowired
    private LinkService linkService;

    @PostMapping("/create")
    @SystemLog(businessName = "Admin:新建导航")
    public ResponseObject<?> createLink(@RequestBody Link link) {
        return linkService.createLink(link);
    }

    @PutMapping("/modify")
    @SystemLog(businessName = "Admin:修改导航信息")
    public ResponseObject<?> modifyLink(@RequestBody Link link) {
        return linkService.modifyLink(link);
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "Admin:删除导航")
    public ResponseObject<?> deleteLink(@PathVariable BigInteger id) {
        return linkService.deleteLink(id);
    }


}
