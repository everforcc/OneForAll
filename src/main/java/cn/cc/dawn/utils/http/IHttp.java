package cn.cc.dawn.utils.http;

import cn.cc.dawn.utils.http.dto.HttpParamDto;

import java.io.InputStream;

public interface IHttp {

    /**
     * 可以注册到bean里面，不用每次都new
     *
     * 选哪个大致分三类
     * 1. 媒体，图片可以流下载，视频等也用流，
     * 2. str等信息，json等 直接请求获取
     * 3. bytes
     * 4. file
     */

    /**
     * 返回流
     * @param httpParamDto
     * @return
     */
    InputStream getStream(HttpParamDto httpParamDto);

    /**
     * 主要还要模拟请求头啥的麻烦，下次用出错的话直接上selenium
     * 返回string
     * @param httpParamDto
     * @return
     */
    String getMsg(HttpParamDto httpParamDto);

    /**
     * 返回数组
     * @param httpParamDto
     * @return
     */
    byte[] getBytes(HttpParamDto httpParamDto);

    /**
     * 生成文件
     * @param httpParamDto
     * @return
     */
    boolean getFile(HttpParamDto httpParamDto);
}
