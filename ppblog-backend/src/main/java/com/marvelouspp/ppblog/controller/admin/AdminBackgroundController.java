package com.marvelouspp.ppblog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Background;
import com.marvelouspp.ppblog.service.BackgroundService;

@RestController
@RequestMapping("/admin/background")
public class AdminBackgroundController {
    @Autowired
    private BackgroundService backgroundService;

    @PutMapping("/modify")
    @SystemLog(businessName = "Admin:修改背景链接")
    public ResponseObject<?> modifyLink(@RequestBody Background background) {
        return backgroundService.modifyBackground(background);
    }
}
