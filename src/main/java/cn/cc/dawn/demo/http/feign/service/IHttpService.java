/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-16 17:15
 * Copyright
 */

package cn.cc.dawn.demo.http.feign.service;

import com.alibaba.fastjson.JSONObject;

public interface IHttpService {

    JSONObject apiAlbumCount(String uid);

}
