package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.utils.commons.lang.RRandomUtils;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.http.HttpHeaderUtils;
import cn.cc.dawn.utils.http.IHttp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component("httpApacheImpl")
public class IHttpApacheImpl implements IHttp {

    private Charset charset = CharsetsEnum.UTF_8.charset;
    @Resource
    IFile iFile;

    /**
     * 根据参数返回文件流
     * @param httpParamDto
     * @return
     */
    @SneakyThrows
    @Override
    public InputStream getStream(HttpParamDto httpParamDto){
        HttpResponse httpResponse = commonFlow(httpParamDto);
        return httpResponse.getEntity().getContent();
    }

    /**
     * 请求返回str
     * @param httpParamDto
     * @return
     */
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

    /**
     * 请求返回字节
     * @param httpParamDto
     * @return
     */
    @SneakyThrows
    @Override
    public byte[] getBytes(HttpParamDto httpParamDto) {
        HttpResponse httpResponse = commonFlow(httpParamDto);
        return EntityUtils.toByteArray(httpResponse.getEntity());
    }

    /**
     * 返回文件
     * @param httpParamDto
     * @return
     */
    @SneakyThrows
    @Override
    public boolean getFile(HttpParamDto httpParamDto) {
        HttpResponse httpResponse = commonFlow(httpParamDto);

        String contentType = httpResponse.getFirstHeader(HttpHeadersConstant.Content_Type).getValue();

        AppCode.A00151.assertHasTrue(!contentType.contains(HttpHeadersConstant.txt_plain),EntityUtils.toString(httpResponse.getEntity(),charset));
        // 从响应头获取文件名 value
        String content_dispositon = httpResponse.getFirstHeader(HttpHeadersConstant.CONTENT_DISPOSITION).getValue();
        String fileName = httpParamDto.getTargetFileName();
        // 处理文件名value
        if(RStringUtils.isEmpty(fileName)) {
            fileName = HttpHeaderUtils.contentDispositionToFileName(content_dispositon);
        }

        InputStream inputStream = httpResponse.getEntity().getContent();
        iFile.write(httpParamDto.getTargetFilePath() + File.separator + fileName, inputStream);
        return false;
    }


    /**
     * 从 params处理参数到client
     * @param httpParamDto
     * @return
     */
    private HttpResponse commonFlow(HttpParamDto httpParamDto){
        HttpClient httpClient = HttpClients.createDefault();

        HttpTypeEnum httpTypeEnum = httpParamDto.getHttpTypeEnum();
        String url = httpParamDto.getUrl();

        HttpResponse httpResponse = null;
        try {
            int sleep  = (1 + RRandomUtils.randomInt(0,10)) * 100;;
            log.info("随机休眠:" + sleep);
            Thread.sleep(sleep);

            // post参数
            if(HttpTypeEnum.POST.equals(httpTypeEnum)){
                HttpPost httpPost = new HttpPost(url);
                // 1. 文本内容
                if(RStringUtils.isNotEmpty(httpParamDto.getContent())) {
                    HttpEntity httpEntity = new StringEntity(httpParamDto.getContent());
                    httpPost.setEntity(httpEntity);
                }
                // 2. headers
                if(RObjectsUtils.nonNull(httpParamDto.getHeaders())){
                    Map<String,String> map = httpParamDto.getHeaders();
                    for(Map.Entry<String,String> entry:map.entrySet()){
                        httpPost.setHeader(entry.getKey(),entry.getValue());
                    }
                }
                // 3. form_data
                if(RObjectsUtils.nonNull(httpParamDto.getFormDates())){
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
                // 4. 文件
                if(RObjectsUtils.nonNull(httpParamDto.getFile())) {
                    File file = httpParamDto.getFile();
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    HttpEntity httpEntity = builder.addBinaryBody("file",file).build();

                    httpPost.setEntity(httpEntity);
                }
                // 返回response
                httpResponse = httpClient.execute(httpPost);
            }else {
                // get参数
                // 1. ?参数直接拼接到url
                HttpGet httpGet = new HttpGet(url);
                // 2. 请求头
                if(RObjectsUtils.nonNull(httpParamDto.getHeaders())){
                    Map<String,String> map = httpParamDto.getHeaders();
                    for(Map.Entry<String,String> entry:map.entrySet()){
                        httpGet.setHeader(entry.getKey(),entry.getValue());
                    }
                }

                // 返回response
                httpResponse = httpClient.execute(httpGet);
            }
            // return
            return httpResponse;
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("httpclient处理异常: " + e);
            throw AppCode.A00100.toUserException(e.getMessage());
        }
    }
}
