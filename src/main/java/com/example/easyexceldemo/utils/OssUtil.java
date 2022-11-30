package com.example.easyexceldemo.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName OssUtil
 * @Author QianQiangQiang
 * @create 2022/11/28 14:46
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssUtil {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    /**
     * 文件上传
     * @param file 文件
     * @return
     */
    public String upload(MultipartFile file) throws Exception {
        if (file.isEmpty()){
            throw new Exception("文件不能为空!");
        }
        //创建OSSClient实例
        OSS build = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String path = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String originalFilename = file.getOriginalFilename();
        String fileName = path + "/" + UuidUtils.sampleUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        try{
            PutObjectResult putObjectResult = build.putObject(bucketName, fileName, file.getInputStream());
            //url过期时间
            Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 10);
            return build.generatePresignedUrl(bucketName, fileName, expiration).toString();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            //关闭ossClient
            build.shutdown();
        }

    }


    /**
     * 下载文件
     * @param fileName 文件名称
     * @param httpServletResponse response
     * @return
     * @throws IOException
     */
    public String download(String fileName, HttpServletResponse httpServletResponse) throws IOException {
        OSS build = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OSSObject object = build.getObject(bucketName, fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        object.close();
        return line;
    }

}
