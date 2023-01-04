package com.marvelouspp.ppblog.domain.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticVo {
    private StatisticLineVo<String, Long> articles;

    private StatisticLineVo<String, String> tagWithMostArticle;

    private StatisticLineVo<String, Long> links;

    private StatisticLineVo<String, Long> Tags;

    private StatisticLineVo<String, Long> News;
}
