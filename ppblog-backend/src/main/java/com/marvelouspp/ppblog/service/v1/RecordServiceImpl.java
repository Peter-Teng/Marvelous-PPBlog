package com.marvelouspp.ppblog.service.v1;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Record;
import com.marvelouspp.ppblog.mapper.RecordMapper;
import com.marvelouspp.ppblog.service.ArticleService;
import com.marvelouspp.ppblog.service.LinkService;
import com.marvelouspp.ppblog.service.NewsService;
import com.marvelouspp.ppblog.service.RecordService;
import com.marvelouspp.ppblog.service.TagService;
import com.marvelouspp.ppblog.utils.RedisUtils;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    @Lazy
    ArticleService articleService;

    @Autowired
    @Lazy
    TagService tagService;

    @Autowired
    @Lazy
    LinkService linkService;

    @Autowired
    @Lazy
    NewsService newsService;

    @Override
    public ResponseObject<?> getRecords() {
        List<Record> records = redisUtils.getCacheObject(Constant.RECORDS_CACHE);
        if(records == null) {
            QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("date").last("limit 6");
            records = list(queryWrapper);
            redisUtils.setCacheObject(Constant.RECORDS_CACHE, records, Constant.CACHE_TTL, TimeUnit.SECONDS);
        }
        return ResponseObject.success(records);
    }

    @Override
    public void createRecord() {
        Record record = new Record();
        record.setDate(new Date());
        record.setArticles(articleService.count());
        record.setLinks(linkService.count());
        record.setNews(newsService.count());
        record.setTags(tagService.count());
        saveOrUpdate(record);
    }
    
}
