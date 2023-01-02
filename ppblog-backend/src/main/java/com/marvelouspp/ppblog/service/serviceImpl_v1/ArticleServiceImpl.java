package com.marvelouspp.ppblog.service.serviceImpl_v1;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.configuration.Constant;
import com.marvelouspp.ppblog.configuration.CopyUtils;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.VO.ArticleDetailVo;
import com.marvelouspp.ppblog.domain.VO.ArticlePreviewVo;
import com.marvelouspp.ppblog.domain.VO.PageVo;
import com.marvelouspp.ppblog.mapper.ArticleMapper;
import com.marvelouspp.ppblog.service.ArticleService;
import com.marvelouspp.ppblog.service.TagService;
import com.marvelouspp.ppblog.utils.RedisUtils;
import com.marvelouspp.ppblog.domain.entity.Article;
import com.marvelouspp.ppblog.domain.entity.Tag;
import com.marvelouspp.ppblog.domain.enums.Code;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private TagService tagService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResponseObject<?> getArticlePreviews(Integer pageNum, Integer pageSize, boolean isPublic) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if(isPublic) {
            //必须是非草稿文章
            queryWrapper.eq(Article::getStatus, Constant.ARTICLE_STATUS_NORMAL);
        }
        queryWrapper.orderByDesc(Article::getCreateDate);
        
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        //转化为VO
        List<ArticlePreviewVo> articlePreviewVos = CopyUtils.copyBeanList(articles, ArticlePreviewVo.class);

        for(ArticlePreviewVo articlePreviewVo : articlePreviewVos) {
            articlePreviewVo.setTagName(tagService.getById(articlePreviewVo.getTagId()).getName());
        }

        PageVo result = new PageVo(articlePreviewVos, page.getTotal());
        return ResponseObject.success(result);
    }

    @Override
    public ResponseObject<?> getArticlePreviewsByTag(Integer pageNum, Integer pageSize, String tagName) {
        
        //获取TagName对应的TagId
        LambdaQueryWrapper<Tag> tagWrapper = new LambdaQueryWrapper<>();
        tagWrapper.eq(Tag::getName, tagName);
        Tag tag = tagService.getOne(tagWrapper);
        BigInteger tagId = tag.getId();

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是非草稿文章
        queryWrapper.eq(Article::getStatus, Constant.ARTICLE_STATUS_NORMAL);
        //符合tagId
        queryWrapper.eq(Article::getTagId, tagId);
        //按发布时间倒序排序
        queryWrapper.orderByDesc(Article::getCreateDate);

        
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        //转化为VO
        List<ArticlePreviewVo> articlePreviewVos = CopyUtils.copyBeanList(articles, ArticlePreviewVo.class);

        for(ArticlePreviewVo articlePreviewVo : articlePreviewVos) {
            articlePreviewVo.setTagName(tagService.getById(tagId).getName());
        }

        PageVo result = new PageVo(articlePreviewVos, page.getTotal());
        return ResponseObject.success(result);
    }

    @Override
    public ResponseObject<?> getArticleById(BigInteger id, Boolean isPublic) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if(isPublic) {
            //必须是非草稿文章
            queryWrapper.eq(Article::getStatus, Constant.ARTICLE_STATUS_NORMAL);
        }
        //根据id查询
        queryWrapper.eq(Article::getId, id);
        Article article = getOne(queryWrapper);
        ArticleDetailVo articleDetailVo = null;
        if(article != null) {
            // 更新redis的heat缓存
            redisUtils.incrementCacheMapValue(Constant.HEAT_MAP, article.getId().toString(), 1);
            // 从redis读取热度
            article.setHeat(redisUtils.getCacheMapValue(Constant.HEAT_MAP, article.getId().toString()));
            // 转换为对应的articleVo
            articleDetailVo = CopyUtils.copyBean(article, ArticleDetailVo.class);
            articleDetailVo.setTagName(tagService.getById(articleDetailVo.getTagId()).getName());
        } else {
            return ResponseObject.failure(Code.ARTICLE_NOT_EXIST);
        }
        return ResponseObject.success(articleDetailVo);
    }

    @Override
    public ResponseObject<?> writeArticle(Article article) {
        if(article.getCreateDate() == null) article.setCreateDate(new Date());
        if(article.getModifyDate() == null) article.setModifyDate(new Date());
        if(StringUtils.isEmpty(article.getSummary())) {
            article.setSummary(article.getContent().substring(50));
        }
        boolean success = save(article);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> modifyArticle(Article article) {
        article.setModifyDate(new Date());
        boolean success = updateById(article);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> deleteArticle(BigInteger id) {
        boolean success = removeById(id);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> postArticle(Article article) {
        article.setStatus(Constant.ARTICLE_STATUS_NORMAL);
        article.setModifyDate(new Date());
        boolean success = updateById(article);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> withdrawArticle(Article article) {
        article.setStatus(Constant.ARTICLE_STATUS_DRAFT);
        article.setModifyDate(new Date());
        boolean success = updateById(article);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }
    
}
