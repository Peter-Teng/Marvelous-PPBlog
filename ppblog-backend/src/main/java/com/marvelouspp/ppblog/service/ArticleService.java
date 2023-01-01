package com.marvelouspp.ppblog.service;

import java.math.BigInteger;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    public ResponseObject<?> getArticlePreviews(Integer pageNum, Integer pageSize, boolean isPublic);

    public ResponseObject<?> getArticlePreviewsByTag(Integer pageNum, Integer pageSize, String tagName);

    public ResponseObject<?> getArticleById(BigInteger id, Boolean isPublic);

    public ResponseObject<?> writeArticle(Article article);

    public ResponseObject<?> modifyArticle(Article article);

    public ResponseObject<?> deleteArticle(BigInteger id);

    public ResponseObject<?> postArticle(Article article);

    public ResponseObject<?> withdrawArticle(Article article);
}
