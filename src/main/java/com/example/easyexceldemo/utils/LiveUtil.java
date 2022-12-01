package com.example.easyexceldemo.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.example.easyexceldemo.config.ALiYunLiveConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName LiveUtil
 * @Author qianqiangqiang
 * @create 2022/12/1 14:39
 */
public class LiveUtil {
    private static final Logger log = LoggerFactory.getLogger(LiveUtil.class);

    /**
     * 计算md5
     * @param params 参数
     * @return string
     */
    public static String md5(String params){
        if (params == null || params.length() == 0){
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(params.getBytes());
            byte[] digest = md5.digest();
            BigInteger bigInteger = new BigInteger(1, digest);
            //参数16表示16进制
            String result = bigInteger.toString(16);
            //不足32位高位补零
            while (result.length()<32){
                result = "0" + result;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成推流地址
     * @param sourceId
     * @return string
     */
    public static String getPushUrl(String sourceId,ALiYunLiveConfig aLiYunLiveConfig){
        //推流域名
        String pushDoMin = aLiYunLiveConfig.getPushDomin();
        //推流key
        String pushIndentKey = aLiYunLiveConfig.getPushIndentKey();
        String streamName = StrUtil.format(sourceId);
        //获取appName
        String appName = aLiYunLiveConfig.getAppName();
        //有效时间
        Integer identUrlVaildTime = aLiYunLiveConfig.getIdentUrlVaildTime();
        //计算过期时间
        String  timeStrap = String.valueOf(System.currentTimeMillis()/1000L + identUrlVaildTime);
        //组合推流域名前缀
        String rtmpUrl = StrUtil.format("rtmp://{}/{}/{}", pushDoMin, appName, streamName);
        //组合MD5加密串
        String md5Url = StrUtil.format("/{}/{}-{}-0-0-{}",appName,streamName,timeStrap,pushIndentKey);
        log.info("组合md5加密串"+md5Url);
        //md5加密
        String md5HHex = DigestUtil.md5Hex(md5Url);
        //拼接推流url
        String finallyPushUrl = StrUtil.format("{}?auth_key={}-0-0-{}", rtmpUrl, timeStrap, md5HHex);
        log.info("最终鉴权过的推流url=" + finallyPushUrl);
        return finallyPushUrl;
    }

    /**
     * 生成播流地址
     * @param sourceId
     * @param aLiYunLiveConfig
     * @return
     */
    public static String getPullUrl(String sourceId,ALiYunLiveConfig aLiYunLiveConfig){
        String pullDoMin = aLiYunLiveConfig.getPullDomin();
        String pullIdentKey = aLiYunLiveConfig.getPullIdentKey();
        String appName = aLiYunLiveConfig.getAppName();
        String streamName = StrUtil.format(sourceId);
        Integer identUrlVaildTime = aLiYunLiveConfig.getIdentUrlVaildTime();
        //计算过期时间
        String  timeStrap = String.valueOf(System.currentTimeMillis()/1000L + identUrlVaildTime);
        String url = StrUtil.format("{}/{}/{}", pullDoMin, appName, streamName);
        // 组合md5加密串 {appName}/{streamName}-{timestamp}-0-0-{pullIdentKey}
        String md5Url = StrUtil.format("/{}/{}-{}-0-0-{}", appName, streamName, timeStrap, pullIdentKey);
        String md5FlvUrl = StrUtil.format("/{}/{}.flv-{}-0-0-{}", appName, streamName, timeStrap, pullIdentKey);
        String md5M3u8Url = StrUtil.format("/{}/{}.m3u8-{}-0-0-{}", appName, streamName, timeStrap, pullIdentKey);
//        new Client
        // md5加密
        String md5Str = DigestUtil.md5Hex(md5Url);
        String md5FlvStr = DigestUtil.md5Hex(md5FlvUrl);
        String md5M3u8Str = DigestUtil.md5Hex(md5M3u8Url);


        // 组合三种拉流域名前缀
        //rtmp://{pullUrl}?auth_key={timestamp}-0-0-{md5Str}
        String rtmpUrl = StrUtil.format("rtmp://{}?auth_key={}-0-0-{}", url, timeStrap, md5Str);
        //http://{pullUrl}.flv?auth_key={timestamp}-0-0-{md5FlvStr}
        String flvUrl = StrUtil.format("http://{}.flv?auth_key={}-0-0-{}", url, timeStrap, md5FlvStr);
        //http://{pullUrl}.m3u8?auth_key={timestamp}-0-0-{md5M3u8Str}
        String m3u8Url = StrUtil.format("http://{}.m3u8?auth_key={}-0-0-{}", url, timeStrap, md5M3u8Str);

        log.info("最终鉴权过的播流rtmp域名=" + rtmpUrl);
        log.info("最终鉴权过的播流flv域名 =" + flvUrl);
        log.info("最终鉴权过的播流m3u8域名=" + m3u8Url);
        return flvUrl;

    }




}
