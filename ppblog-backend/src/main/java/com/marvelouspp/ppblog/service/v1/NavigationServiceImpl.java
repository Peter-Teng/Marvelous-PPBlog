package com.marvelouspp.ppblog.service.v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Navigation;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.mapper.NavigationMapper;
import com.marvelouspp.ppblog.service.NavigationService;

@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements NavigationService {

    @Override
    public ResponseObject<?> getNavigations() {
        List<Navigation> navigations = list();
        return ResponseObject.success(navigations);
    }

    @Override
    public ResponseObject<?> createNavigation(Navigation navigation) {
        boolean success = saveOrUpdate(navigation);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> modifyNavigation(Navigation navigation) {
        boolean success = updateById(navigation);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }

    @Override
    public ResponseObject<?> deleteNavigation(BigInteger id) {
        boolean success = removeById(id);
        if(success) {
            return ResponseObject.success();
        }
        return ResponseObject.failure(Code.SYSTEM_ERROR);
    }
    
}
