package com.marvelouspp.ppblog.service;

import java.math.BigInteger;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Navigation;

public interface NavigationService extends IService<Navigation> {
    public ResponseObject<?> getNavigations();

    public ResponseObject<?> createNavigation(Navigation navigation);

    public ResponseObject<?> modifyNavigation(Navigation navigation);

    public ResponseObject<?> deleteNavigation(BigInteger id);
}
