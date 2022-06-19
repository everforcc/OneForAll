/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-16 16:52
 */

package cn.cc.dawn.demo.http.feign.controller;

import cn.cc.dawn.demo.http.feign.service.IHttpService;
import cn.cc.dawn.demo.http.feign.service.impl.HttpServiceFeignImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class FeignController {


    @Autowired
    private IHttpService httpServiceFeignImpl;

    @GetMapping("/feign_post/{uid}")
    public JSONObject feignClient(@PathVariable("uid")String uid){
        return httpServiceFeignImpl.apiAlbumCount(uid);
    }

}
