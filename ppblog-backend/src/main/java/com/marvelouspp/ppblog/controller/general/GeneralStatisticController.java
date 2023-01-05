package com.marvelouspp.ppblog.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.service.StatisticService;

@RestController
@RequestMapping("/general/statistic")
public class GeneralStatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping("/overview")
    @SystemLog(businessName = "Public:获取总览数据")
    public ResponseObject<?> overview() {
        return statisticService.overview();
    }

    @GetMapping("/tag")
    @SystemLog(businessName = "Public:获取标签数据")
    public ResponseObject<?> getTagsStatictics() {
        return statisticService.getTagsStatictics();
    }

    @GetMapping("/link")
    @SystemLog(businessName = "Public:获取导航数据")
    public ResponseObject<?> getLinksStatistics() {
        return statisticService.getLinksStatistics();
    }
}
