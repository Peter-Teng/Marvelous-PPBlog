package com.marvelouspp.ppblog.domain.VO;


import com.marvelouspp.ppblog.domain.support.Pair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverviewVo {
    private Pair<String, Long> articles;

    private Pair<String, String> tagWithMostArticle;

    private Pair<String, Long> links;

    private Pair<String, Long> Tags;

    private Pair<String, Long> News;
}
