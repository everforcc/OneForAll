package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.open.web.data.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.HttpMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

@Component("httpApacheImpl")
public class HttpApacheImpl implements HttpMethod {

    private Charset charset = CharsetsEnum.UTF_8.charset;

    @Override
    public InputStream getStream(HttpParamDto httpParamDto){
        return null;
    }

    @Override
    public String getMsg(HttpParamDto httpParamDto){
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(httpParamDto.getUrl());
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);

            if(Objects.nonNull(httpParamDto.getCharset())){
                charset = httpParamDto.getCharset().charset;
            }

            return EntityUtils.toString(httpResponse.getEntity(),charset);
        } catch (IOException e) {
            e.printStackTrace();
            throw AppCode.A00100.toUserException(e.toString());
        }

    }

    @Override
    public boolean getFile(HttpParamDto httpParamDto) {
        return false;
    }
}
