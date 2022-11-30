package com.example.easyexceldemo.controller;

import com.example.easyexceldemo.utils.OssUtil;
import com.example.easyexceldemo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName FileController
 * @Author TJ
 * @create 2022/11/28 17:04
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private OssUtil ossUtil;

    /**
     * 文件上传
     * @param file 文件
     * @return string
     * @throws Exception 异常
     */
    @GetMapping("/upload")
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return Result.success(ossUtil.upload(file));
    }

    /**
     * 流式下载
     * @param fileName 文件名称
     * @param httpServletResponse response
     * @return Result
     * @throws IOException
     */
    @GetMapping("/download")
    public Result download(String fileName, HttpServletResponse httpServletResponse) throws IOException {
        return Result.success(ossUtil.download(fileName,httpServletResponse));
    }




}
