package com.marvelouspp.ppblog.service.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.VO.StatisticLineVo;
import com.marvelouspp.ppblog.domain.VO.StatisticVo;
import com.marvelouspp.ppblog.service.ArticleService;
import com.marvelouspp.ppblog.service.LinkService;
import com.marvelouspp.ppblog.service.NewsService;
import com.marvelouspp.ppblog.service.StatisticService;
import com.marvelouspp.ppblog.service.TagService;

public class StatisticServiceImpl implements StatisticService {

    @Autowired
    TagService tagService;

    @Autowired
    ArticleService articleService;

    @Autowired
    LinkService LinkService;

    @Autowired
    NewsService newsService;

    @Override
    public ResponseObject<?> listStatistics() {
        StatisticVo statisticVo = new StatisticVo();
        statisticVo.setArticles(new StatisticLineVo<>("文章总数", articleService.count()));
        statisticVo.setTags(new StatisticLineVo<>("标签总数", tagService.count()));
        statisticVo.setTagWithMostArticle(new StatisticLineVo<>("最多文章的标签", tagService.getTagWithMostArticle()));
        statisticVo.setLinks(new StatisticLineVo<>("导航链接总数", LinkService.count()));
        statisticVo.setNews(new StatisticLineVo<>("累计发布公告", newsService.count()));
        return null;
    }
    
}
