package cn.cc.dawn.utils.http;

import cn.cc.dawn.demo.craw.website.dto.HttpParam;

import java.io.InputStream;

public interface HttpMethod {

    /**
     * 发送一个网络请求
     */

    InputStream getStream(HttpParam httpParam);

    String getMsg(HttpParam httpParam);

    boolean getFile(HttpParam httpParam);
}
