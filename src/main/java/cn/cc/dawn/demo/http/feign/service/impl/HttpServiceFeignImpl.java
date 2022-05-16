package cn.cc.dawn.demo.http.feign.service.impl;

import cn.cc.dawn.demo.http.feign.api.FeignBilibiliApi;
import cn.cc.dawn.demo.http.feign.service.IHttpService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("httpClientService")
public class HttpServiceFeignImpl implements IHttpService {

    @Autowired
    private FeignBilibiliApi feignBilibiliApi;

    public JSONObject apiAlbumCount(String uid){
        return feignBilibiliApi.apiAlbumCount(uid);
    }

}
