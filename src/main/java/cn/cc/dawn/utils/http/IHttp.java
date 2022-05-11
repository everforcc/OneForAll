package cn.cc.dawn.utils.http;

import cn.cc.dawn.local.craw.web.data.dto.HttpParamDto;

import java.io.InputStream;

public interface IHttp {

    /**
     * 可以注册到bean里面，不用每次都new
     *
     * 选哪个大致分三类
     * 1. json等信息，直接请求获取
     * 2. 页面信息，有的比较复杂，用selenium来请求获取
     * 3. 媒体，图片可以流下载，视频等也用流，
     * 4. 系统间 FeignClient
     */

    /**
     * selenium虽然运行效率一般，但是开发效率高，通用性强
     */

    InputStream getStream(HttpParamDto httpParamDto);

    /**
     * 主要还要模拟请求头啥的麻烦，下次用出错的话直接上selenium
     * @param httpParamDto
     * @return
     */
    String getMsg(HttpParamDto httpParamDto);

    boolean getFile(HttpParamDto httpParamDto);
}
