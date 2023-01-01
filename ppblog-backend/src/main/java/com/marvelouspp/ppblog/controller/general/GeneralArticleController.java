package com.marvelouspp.ppblog.controller.general;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.service.ArticleService;

@RestController
@RequestMapping("/general/article")
public class GeneralArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
    @SystemLog(businessName = "Public:获取最近文章列表")
    public ResponseObject<?> getArticlePreviews(@RequestParam Integer pageNum, 
                                                @RequestParam Integer pageSize) {
        return articleService.getArticlePreviews(pageNum, pageSize, true);
    }

    @GetMapping("/tagList")
    @SystemLog(businessName = "Public:按标签分类获取文章列表")
    public ResponseObject<?> getArticlePreviewsByTag(@RequestParam Integer pageNum, 
                                                     @RequestParam Integer pageSize, 
                                                     @RequestParam String tagName) {
        return articleService.getArticlePreviewsByTag(pageNum, pageSize, tagName);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "Public:获取文章详细信息")
    public ResponseObject<?> getArticleByIdPublic(@PathVariable BigInteger id) {
        return articleService.getArticleById(id, true);
    }
}
