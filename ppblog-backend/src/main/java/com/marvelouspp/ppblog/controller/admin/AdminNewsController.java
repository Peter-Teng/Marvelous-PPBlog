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
import com.marvelouspp.ppblog.domain.entity.News;
import com.marvelouspp.ppblog.service.NewsService;

@RestController
@RequestMapping("/admin/news")
public class AdminNewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/create")
    @SystemLog(businessName = "Admin:新建新闻")
    public ResponseObject<?> createNews(@RequestBody News news) {
        return newsService.createNews(news);
    }

    @PutMapping("/modify")
    @SystemLog(businessName = "Admin:修改新闻")
    public ResponseObject<?> modifyNews(@RequestBody News news) {
        return newsService.modifyNews(news);
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "Admin:删除标签")
    public ResponseObject<?> deleteNews(@PathVariable BigInteger id) {
        return newsService.deleteNews(id);
    }
    
}
