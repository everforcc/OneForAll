/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-16 16:52
 * Copyright
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
