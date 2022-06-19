/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-16 17:15
 */

package cn.cc.dawn.demo.http.feign.service;

import com.alibaba.fastjson.JSONObject;

public interface IHttpService {

    JSONObject apiAlbumCount(String uid);

}
