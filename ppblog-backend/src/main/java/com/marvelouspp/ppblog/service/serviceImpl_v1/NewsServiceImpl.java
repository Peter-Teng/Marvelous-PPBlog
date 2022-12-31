package com.marvelouspp.ppblog.service.serviceImpl_v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.News;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.mapper.NewsMapper;
import com.marvelouspp.ppblog.service.NewsService;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService{

    @Override
    public ResponseObject<?> getNews() {
        List<News> newsList = list();
        return ResponseObject.success(newsList);
    }

    @Override
    public ResponseObject<?> createNews(News news) {
        boolean success = saveOrUpdate(news);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> modifyNews(News news) {
        boolean success = updateById(news);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> deleteNews(BigInteger id) {
        boolean success = removeById(id);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    
    
}
