package com.marvelouspp.ppblog.controller.general;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;

@RestController
@RequestMapping("/general/statistic")
public class GeneralStatisticController {

    @GetMapping("/list")
    @SystemLog(businessName = "Public:获取全部数据")
    public ResponseObject<?> getLinks() {
        return null;
    }
}
