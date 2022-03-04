package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.common.dto.HttpParam;
import cn.cc.dawn.common.enums.CharsetsEnum;
import cn.cc.dawn.utils.http.HttpMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class HttpApacheImpl implements HttpMethod {

    private Charset charset = CharsetsEnum.UTF_8.charset;

    @Override
    public InputStream getStream(HttpParam httpParam) throws IOException {
        return null;
    }

    @Override
    public String getMsg(HttpParam httpParam) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(httpParam.getUrl());
        HttpResponse httpResponse = httpClient.execute(httpGet);

        if(Objects.nonNull(httpParam.getCharset())){
            charset = httpParam.getCharset().charset;
        }

        return EntityUtils.toString(httpResponse.getEntity(),charset);
    }

    @Override
    public boolean getFile(HttpParam httpParam) {
        return false;
    }
}
