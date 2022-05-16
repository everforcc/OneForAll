package cn.cc.dawn.demo.http.feign.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "bili",url = "https://api.vc.bilibili.com")
public interface FeignBilibiliApi {

    @PostMapping(value = "/link_draw/v1/doc/upload_count?uid=%s", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    JSONObject apiAlbumCount(@RequestParam("uid")String uid);

}
