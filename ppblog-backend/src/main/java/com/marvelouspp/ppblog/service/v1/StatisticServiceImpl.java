package com.marvelouspp.ppblog.service.v1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.VO.OverviewVo;
import com.marvelouspp.ppblog.domain.entity.Article;
import com.marvelouspp.ppblog.domain.entity.Link;
import com.marvelouspp.ppblog.domain.entity.Tag;
import com.marvelouspp.ppblog.domain.support.Pair;
import com.marvelouspp.ppblog.service.ArticleService;
import com.marvelouspp.ppblog.service.LinkService;
import com.marvelouspp.ppblog.service.NewsService;
import com.marvelouspp.ppblog.service.StatisticService;
import com.marvelouspp.ppblog.service.TagService;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    @Lazy
    TagService tagService;

    @Autowired
    @Lazy
    ArticleService articleService;

    @Autowired
    @Lazy
    LinkService linkService;

    @Autowired
    @Lazy
    NewsService newsService;

    @Override
    public ResponseObject<?> overview() {
        OverviewVo overviewVO = new OverviewVo();
        overviewVO.setArticles(new Pair<>("文章总数", articleService.count()));
        overviewVO.setTags(new Pair<>("标签总数", tagService.count()));
        overviewVO.setTagWithMostArticle(new Pair<>("最多文章的标签", tagService.getTagWithMostArticle()));
        overviewVO.setLinks(new Pair<>("导航链接总数", linkService.count()));
        overviewVO.setNews(new Pair<>("累计发布公告", newsService.count()));
        return ResponseObject.success(overviewVO);
    }

    @Override
    public ResponseObject<?> getTagsStatictics() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("COUNT(id) as number, tag_id as id");
        queryWrapper.eq("status", Constant.ARTICLE_STATUS_NORMAL);
        queryWrapper.groupBy("tag_id");
        List<Map<String, Object>> lines = articleService.listMaps(queryWrapper);

        List<Pair<String, String>> result = new ArrayList<>();
        for (Map<String, Object> line : lines) {
            BigInteger id = new BigInteger(line.get("id").toString());
            LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Tag::getId, id);
            Tag tag = tagService.getOne(lambdaQueryWrapper);
            result.add(new Pair<>(tag.getName(), line.get("number").toString()));
        }
        return ResponseObject.success(result);
    }

    @Override
    public ResponseObject<?> getLinksStatistics() {
        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("COUNT(DISTINCT id) AS amount, category");
        queryWrapper.groupBy("category");
        List<Map<String, Object>> lines = linkService.listMaps(queryWrapper);

        List<Pair<String, BigInteger>> result = new ArrayList<>();
        for (Map<String, Object> line : lines) {
            BigInteger amount = new BigInteger(line.get("amount").toString());
            result.add(new Pair<>(line.get("category").toString(), amount));
        }
        return ResponseObject.success(result);
    }

}
