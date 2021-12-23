package cn.cc.dawn.demo.httpclient.service;

import cn.cc.dawn.demo.httpclient.feign.FeignBilibiliApi;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("httpClientService")
public class HttpClientService {

    @Autowired
    private FeignBilibiliApi feignBilibiliApi;

    public JSONObject feignPost_AlbumCount(String uid){
        return feignBilibiliApi.getAlbumCount(uid);
    }

}
