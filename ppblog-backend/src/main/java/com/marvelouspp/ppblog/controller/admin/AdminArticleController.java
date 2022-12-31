package com.marvelouspp.ppblog.controller.admin;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Article;
import com.marvelouspp.ppblog.service.ArticleService;

@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {
    
    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
    @SystemLog(businessName = "Admin:获取最近文章列表")
    public ResponseObject<?> getArticlePreviews(@RequestParam Integer pageNum, 
                                                @RequestParam Integer pageSize) {
        return articleService.getArticlePreviews(pageNum, pageSize, false);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "Admin:获取文章详细信息")
    public ResponseObject<?> getArticleByIdPublic(@PathVariable BigInteger id) {
        return articleService.getArticleById(id, false);
    }

    @PostMapping("/create")
    @SystemLog(businessName = "Admin:编写文章信息")
    public ResponseObject<?> writeArticle(@RequestBody Article article) {
        return articleService.writeArticle(article);
    }

    @PutMapping("/modify")
    @SystemLog(businessName = "Admin:修改文章信息")
    public ResponseObject<?> modifyArticle(@RequestBody Article article) {
        return articleService.modifyArticle(article);
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "Admin:删除文章")
    public ResponseObject<?> deleteArticle(@PathVariable BigInteger id) {
        return articleService.deleteArticle(id);
    }

    @PostMapping("/publish")
    @SystemLog(businessName = "Admin:发布文章（草稿->正式文章）")
    public ResponseObject<?> postArticle(@RequestBody Article article) {
        return articleService.postArticle(article);
    }

    @PostMapping("/withdraw")
    @SystemLog(businessName = "Admin:收回文章（正式文章->草稿）")
    public ResponseObject<?> withdrawArticle(@RequestBody Article article) {
        return articleService.withdrawArticle(article);
    }
}
