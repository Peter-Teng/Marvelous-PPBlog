package com.marvelouspp.ppblog.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marvelouspp.ppblog.annotations.SystemLog;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.enums.Code;
import com.marvelouspp.ppblog.utils.TencentCosUtils;

@RestController
@RequestMapping("/admin/file")
public class UploadController {

    @PostMapping("/upload")
    @SystemLog(businessName = "Admin:上传文件")
    public ResponseObject<?> upload(@RequestPart("image") MultipartFile image, boolean squeeze) {
        try {
            String url = TencentCosUtils.uploadFile(image, squeeze);
            return ResponseObject.success(url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.failure(Code.UPLOAD_FAILURE);
        }
    }
}
