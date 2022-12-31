package com.marvelouspp.ppblog.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.service.TagService;

@RestController
@RequestMapping("/general/tag")
public class GeneralTagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    @SystemLog(businessName = "Public:获取全部标签信息")
    public ResponseObject<?> getTags() {
        return tagService.getTags();
    }
}
