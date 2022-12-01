package com.example.easyexceldemo.controller;

import com.example.easyexceldemo.config.ALiYunLiveConfig;
import com.example.easyexceldemo.utils.LiveUtil;
import com.example.easyexceldemo.utils.Result;
import com.example.easyexceldemo.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LiveController
 * @Author TJ
 * @create 2022/12/1 15:20
 */
@RestController
@RequestMapping("/live")
public class LiveController {

    @Autowired
    private ALiYunLiveConfig aLiYunLiveConfig;

    @GetMapping("/getAuthentication")
    public Result<Map<String,String>> authentication(){
        HashMap<String, String> map = new HashMap<>();
        String sourceId = UuidUtils.sampleUUID();
        String pushUrl = LiveUtil.getPushUrl(sourceId, aLiYunLiveConfig);
        String pullUrl = LiveUtil.getPullUrl(sourceId, aLiYunLiveConfig);
        map.put("pushUrl",pushUrl);
        map.put("pullUrl",pullUrl);
        return Result.success(map);
    }

}
