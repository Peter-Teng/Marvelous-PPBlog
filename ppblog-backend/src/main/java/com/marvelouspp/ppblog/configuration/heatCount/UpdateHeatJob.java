package com.marvelouspp.ppblog.configuration.heatCount;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.entity.Article;
import com.marvelouspp.ppblog.service.ArticleService;
import com.marvelouspp.ppblog.utils.RedisUtils;

@Component
public class UpdateHeatJob {
    @Autowired
    private RedisUtils redisUtils;
    
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/20 * * * ?")
    public void updateHeat() {
        Map<String, Integer> heatMap = redisUtils.getCacheMap(Constant.HEAT_MAP);

        List<Article> articles = heatMap.entrySet().stream()
                                .map(entry -> new Article(BigInteger.valueOf(Long.parseLong(entry.getKey())), entry.getValue()))
                                .collect(Collectors.toList());
        articleService.updateBatchById(articles);
    }
}
