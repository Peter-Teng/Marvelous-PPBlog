package com.marvelouspp.ppblog.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.service.NewsService;

@RestController
@RequestMapping("/general/news")
public class GeneralNewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/list")
    @SystemLog(businessName = "Public:获取全部新闻信息")
    public ResponseObject<?> getNews() {
        return newsService.getNews();
    }
}
