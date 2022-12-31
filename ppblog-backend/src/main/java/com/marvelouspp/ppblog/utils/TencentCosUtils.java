package com.marvelouspp.ppblog.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.marvelouspp.ppblog.configuration.Constant;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

@Component
public class TencentCosUtils {
    private static String secretId;
    private static String secretKey;
    private static String bucketName;
    private static String accessUrl;

    @Value("${tencentCosUtil.secretId}")
    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    @Value("${tencentCosUtil.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Value("${tencentCosUtil.bucketName}")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Value("${tencentCosUtil.accessUrl}")
    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }


    public static String uploadFile(MultipartFile file) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        String temporaryFile = String.format("%s/%s", "images", today);

        File localFile = new File(temporaryFile);
        if(!localFile.exists()) {
            localFile.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String name = "/MarvelousPP" + System.currentTimeMillis() + System.nanoTime() + fileName.substring(fileName.lastIndexOf('.'));
        

        temporaryFile = String.format("%s/%s", temporaryFile, name);
        FileOutputStream fos;
        fos = new FileOutputStream(temporaryFile);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();

        File COSfile = new File(temporaryFile);
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(Constant.COS_REGION);
        ClientConfig clientConfig = new ClientConfig(region);

        clientConfig.setHttpProtocol(HttpProtocol.https);

        COSClient client = new COSClient(cred, clientConfig);

        PutObjectRequest request = new PutObjectRequest(bucketName, temporaryFile, COSfile);
        client.putObject(request);
        String saveUrl = String.format("%s/%s", accessUrl, request.getKey());

        File localImageFile = new File(temporaryFile);
        localImageFile.delete();
        return saveUrl;
    }
}
