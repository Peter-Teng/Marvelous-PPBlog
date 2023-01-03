package com.marvelouspp.ppblog.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.service.NavigationService;

@RestController
@RequestMapping("/general/navigation")
public class GeneralNavitionCotroller {
    @Autowired
    private NavigationService navigationService;

    @GetMapping("/list")
    @SystemLog(businessName = "Public:获取全部导航链接")
    public ResponseObject<?> getNavigations() {
        return navigationService.getNavigations();
    }
}
