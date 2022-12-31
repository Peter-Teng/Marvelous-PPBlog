package com.marvelouspp.ppblog.configuration.heatCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.entity.Article;
import com.marvelouspp.ppblog.mapper.ArticleMapper;
import com.marvelouspp.ppblog.utils.RedisUtils;

@Component
public class HeatCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> heatMap = new HashMap<>();
        for(Article article : articles) {
            heatMap.put(article.getId().toString(), article.getHeat());
        }
        redisUtils.setCacheMap(Constant.HEAT_MAP, heatMap);
    }
    
}
