package com.marvelouspp.ppblog.service;

import com.marvelouspp.ppblog.domain.ResponseObject;

public interface StatisticService {
    public ResponseObject<?> overview();

    public ResponseObject<?> getTagsStatictics();

    public ResponseObject<?> getLinksStatistics();
}
