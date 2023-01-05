package com.marvelouspp.ppblog.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.service.LinkService;

@RestController
@RequestMapping("/general/link")
public class GeneralLinkCotroller {
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    @SystemLog(businessName = "Public:获取全部导航链接")
    public ResponseObject<?> getLinks() {
        return linkService.getLinks();
    }
}
