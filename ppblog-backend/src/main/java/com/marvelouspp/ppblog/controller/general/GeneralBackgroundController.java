package com.marvelouspp.ppblog.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.service.BackgroundService;

@RestController
@RequestMapping("/general/background")
public class GeneralBackgroundController {
    @Autowired
    private BackgroundService backgroundService;

    @GetMapping("/list")
    @SystemLog(businessName = "Public:获取全部背景链接")
    public ResponseObject<?> getBackgrounds() {
        return backgroundService.getBackgrounds();
    }
}
