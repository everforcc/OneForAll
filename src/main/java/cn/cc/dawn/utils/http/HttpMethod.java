package cn.cc.dawn.utils.http;

import cn.cc.dawn.open.web.data.dto.HttpParamDto;

import java.io.InputStream;

public interface HttpMethod {

    /**
     * 可以注册到bean里面，不用每次都new
     */

    /**
     * 发送一个网络请求
     */

    InputStream getStream(HttpParamDto httpParamDto);

    String getMsg(HttpParamDto httpParamDto);

    boolean getFile(HttpParamDto httpParamDto);
}
