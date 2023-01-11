package com.marvelouspp.ppblog.service;

import java.math.BigInteger;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.News;

public interface NewsService extends IService<News> {
    
    public ResponseObject<?> getNews();

    public ResponseObject<?> getAllNews();

    public ResponseObject<?> createNews(News news);

    public ResponseObject<?> modifyNews(News news);

    public ResponseObject<?> deleteNews(BigInteger id);
}
