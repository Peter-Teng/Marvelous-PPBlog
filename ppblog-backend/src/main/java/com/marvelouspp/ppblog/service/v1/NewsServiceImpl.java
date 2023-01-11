package com.marvelouspp.ppblog.service.v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.News;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.mapper.NewsMapper;
import com.marvelouspp.ppblog.service.NewsService;
import com.marvelouspp.ppblog.utils.RedisUtils;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService{

    @Autowired
    RedisUtils redisUtils;

    @Override
    public ResponseObject<?> getNews() {
        List<News> newsList = redisUtils.getCacheObject(Constant.NEWS_CACHE);
        if(newsList == null) {
            LambdaQueryWrapper<News> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(News::getValid, Constant.VALID);
            newsList = list(lambdaQueryWrapper);
            redisUtils.setCacheObject(Constant.NEWS_CACHE, newsList);
        }
        return ResponseObject.success(newsList);
    }

    @Override
    public ResponseObject<?> getAllNews() {
        List<News> newsList = list();
        return ResponseObject.success(newsList);
    }

    @Override
    public ResponseObject<?> createNews(News news) {
        boolean success = saveOrUpdate(news);
        if(success) {
            redisUtils.deleteObject(Constant.NEWS_CACHE);
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> modifyNews(News news) {
        boolean success = updateById(news);
        if(success) {
            redisUtils.deleteObject(Constant.NEWS_CACHE);
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> deleteNews(BigInteger id) {
        boolean success = removeById(id);
        if(success) {
            redisUtils.deleteObject(Constant.NEWS_CACHE);
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    
    
}
