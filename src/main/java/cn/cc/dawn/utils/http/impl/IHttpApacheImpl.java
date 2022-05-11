package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.local.craw.web.data.dto.HttpParamDto;
import cn.cc.dawn.utils.RandomUtils;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.IHttp;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

@Slf4j
@Component("httpApacheImpl")
public class IHttpApacheImpl implements IHttp {

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
            int sleep  = (1 + RandomUtils.randomInt(1,5)) * 1000;;
            log.info("随机休眠:" + sleep);
            Thread.sleep(sleep);

            httpResponse = httpClient.execute(httpGet);

            if(Objects.nonNull(httpParamDto.getCharset())){
                charset = httpParamDto.getCharset().charset;
            }

            return EntityUtils.toString(httpResponse.getEntity(),charset);
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("测试jar定义异常: " + e);
            throw AppCode.A00100.toUserException(e.getMessage());
        }

    }

    @Override
    public boolean getFile(HttpParamDto httpParamDto) {
        return false;
    }
}
