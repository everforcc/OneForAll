package cn.cc.dawn.local.craw.business.sp4u.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "spring4u",url = "")
public interface Sp4uApi {

    @PostMapping(value = "/link_draw/v1/doc/upload_count?uid=%s", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    JSONObject getAlbumCount(@RequestParam("uid")String uid);
}
