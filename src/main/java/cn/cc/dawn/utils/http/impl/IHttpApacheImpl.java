package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.utils.RandomUtils;
import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.IHttp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component("httpApacheImpl")
public class IHttpApacheImpl implements IHttp {

    private Charset charset = CharsetsEnum.UTF_8.charset;

    @Override
    public InputStream getStream(HttpParamDto httpParamDto){
        return null;
    }

    @SneakyThrows
    @Override
    public String getMsg(HttpParamDto httpParamDto){

        HttpResponse httpResponse = commonFlow(httpParamDto);

//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(httpParamDto.getUrl());
//        HttpResponse httpResponse = commonFlow(httpParamDto);
//
//        int sleep  = (1 + RandomUtils.randomInt(1,5)) * 1000;;
//        log.info("随机休眠:" + sleep);
//        Thread.sleep(sleep);
//
//        httpResponse = httpClient.execute(httpGet);

        if(Objects.nonNull(httpParamDto.getCharset())){
            charset = httpParamDto.getCharset().charset;
        }
        return EntityUtils.toString(httpResponse.getEntity(),charset);

    }

    @SneakyThrows
    @Override
    public byte[] getBytes(HttpParamDto httpParamDto) {

        HttpResponse httpResponse = commonFlow(httpParamDto);
        return EntityUtils.toByteArray(httpResponse.getEntity());
    }

    private HttpResponse commonFlow(HttpParamDto httpParamDto){
        HttpClient httpClient = HttpClients.createDefault();

        HttpTypeEnum httpTypeEnum = httpParamDto.getHttpTypeEnum();
        String url = httpParamDto.getUrl();

        HttpResponse httpResponse = null;
        try {
            int sleep  = (1 + RandomUtils.randomInt(0,10)) * 100;;
            log.info("随机休眠:" + sleep);
            Thread.sleep(sleep);

            if(HttpTypeEnum.POST.equals(httpTypeEnum)){
                HttpPost httpPost = new HttpPost(url);
                if(StringUtils.isNotEmpty(httpParamDto.getContent())) {
                    HttpEntity httpEntity = new StringEntity(httpParamDto.getContent());
                    httpPost.setEntity(httpEntity);
                }
                if(ObjectUtils.nonNull(httpParamDto.getHeaders())){
                    Map<String,String> map = httpParamDto.getHeaders();
                    for(Map.Entry<String,String> entry:map.entrySet()){
                        httpPost.setHeader(entry.getKey(),entry.getValue());
                    }
                }
                if(ObjectUtils.nonNull(httpParamDto.getFormDates())){
                    Map<String,String> map = httpParamDto.getFormDates();
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    for(Map.Entry<String,String> entry:map.entrySet()){
                        //httpPost.setHeader(entry.getKey(),entry.getValue());
                        // 添加非文件
                        builder.addTextBody(entry.getKey(),entry.getValue(), ContentType.MULTIPART_FORM_DATA);
                    }
                    HttpEntity httpEntity = builder.build();
                    httpPost.setEntity(httpEntity);
                }
                //
                if(ObjectUtils.nonNull(httpParamDto.getFile())) {
                    File file = httpParamDto.getFile();
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    HttpEntity httpEntity = builder.addBinaryBody("file",file).build();

                    httpPost.setEntity(httpEntity);
                }
                httpResponse = httpClient.execute(httpPost);
            }else {
                HttpGet httpGet = new HttpGet(url);
                httpResponse = httpClient.execute(httpGet);
            }
            return httpResponse;
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
