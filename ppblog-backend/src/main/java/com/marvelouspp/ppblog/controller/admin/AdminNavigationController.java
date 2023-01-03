package com.marvelouspp.ppblog.controller.admin;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Navigation;
import com.marvelouspp.ppblog.service.NavigationService;

@RestController
@RequestMapping("/admin/navigation")
public class AdminNavigationController {
    
    @Autowired
    private NavigationService navigationService;

    @PostMapping("/create")
    @SystemLog(businessName = "Admin:新建导航")
    public ResponseObject<?> createNavigation(@RequestBody Navigation navigation) {
        return navigationService.createNavigation(navigation);
    }

    @PutMapping("/modify")
    @SystemLog(businessName = "Admin:修改导航信息")
    public ResponseObject<?> modifyNavigation(@RequestBody Navigation navigation) {
        return navigationService.modifyNavigation(navigation);
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "Admin:删除导航")
    public ResponseObject<?> deleteNavigation(@PathVariable BigInteger id) {
        return navigationService.deleteNavigation(id);
    }


}
