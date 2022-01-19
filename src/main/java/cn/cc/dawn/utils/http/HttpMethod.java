package cn.cc.dawn.utils.http;

import cn.cc.dawn.common.dto.HttpParam;

import java.io.IOException;
import java.io.InputStream;

public interface HttpMethod {

    /**
     * 发送一个网络请求
     */

    InputStream getStream(HttpParam httpParam) throws IOException;

    String getMsg(HttpParam httpParam) throws Exception;

    boolean getFile(HttpParam httpParam);
}
