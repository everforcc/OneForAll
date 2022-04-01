package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.open.web.data.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class HttpUrlConnectImpl implements HttpMethod {
    @Override
    public InputStream getStream(HttpParamDto httpParamDto){
        log.info("getStream():" + httpParamDto.toString());
        InputStream inputStream = getStreamFlow(httpParamDto);
        log.info("getStream(): end");
        return inputStream;
    }

    @Override
    public String getMsg(HttpParamDto httpParamDto){
        InputStream inputStream = getStream(httpParamDto);
        return bufferReaderToStr(inputStream, httpParamDto.getCharset().charset.toString(), BooleanEnum.FALSE.flag);
    }

    @Override
    public boolean getFile(HttpParamDto httpParamDto) {
        return false;
    }

    private static InputStream getStreamFlow(HttpParamDto httpParamDto){

        try {
            // 1. 声明
            HttpURLConnection conn;
            if (Objects.nonNull(httpParamDto.getProxy())) {
                conn = (HttpURLConnection) new URL(httpParamDto.getUrl()).openConnection(httpParamDto.getProxy());
            } else {
                conn = (HttpURLConnection) new URL(httpParamDto.getUrl()).openConnection();
            }
            // 2. 设置请求头
            Map<String, String> map = httpParamDto.getHeaders();
            if (map != null && map.size() > 0) {
                for (Map.Entry entry : map.entrySet()) {
                    //System.out.println((String) entry.getKey() + "---" + conn.getRequestProperty((String) entry.getKey()));
                    String key = (String) entry.getKey();
                    if (conn.getRequestProperty(key) == null) {
                        String value = (String) entry.getValue();
                        conn.setRequestProperty(key, value);
                        // 大小写有时候不一样
                    /*if("Accept-Encoding".equalsIgnoreCase(key)&&value.contains("gzip")){
                        gzip = true; // 需要解压
                    }*/
                    }
                }
            }


            //3. 设置超时时间
            if (0 != httpParamDto.getTimeout()) {
                conn.setConnectTimeout(httpParamDto.getTimeout());
            }
            //4. 设置提交类型
            if (Objects.nonNull(httpParamDto.getHttpTypeEnum())) {
                conn.setRequestMethod(httpParamDto.getHttpTypeEnum().type);
            }


            //5. 设置允许写出数据,默认是不允许 false
            // 当前的连接可以从服务器读取内容, 默认是true
            if (Objects.nonNull(httpParamDto.getDoinput())) {
                conn.setDoInput(httpParamDto.getDoinput().flag);
            }

            // 6. 向服务器输出内容
            if (!StringUtils.isEmpty(httpParamDto.getContent())) {

                if (Objects.nonNull(httpParamDto.getOutput())) {
                    conn.setDoOutput(httpParamDto.getOutput().flag);
                }
                //5. 获取向服务器写出数据的流
                OutputStream os = conn.getOutputStream();
                //参数是键值队  , 不以"?"开始
                //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
                os.write(httpParamDto.getContent().getBytes());
                os.flush();
            }
            // charset 在这里 <meta http-equiv="Content-Type" content="text/html; charset=gbk" />

            // 7. 打印响应头信息
            log.info("ContentLength:" + conn.getContentLength(), "[%s]");
            conn.getHeaderFields().forEach((k, v) -> {
                log.info("响应头 key:" + k + ", value:" + v);
            });
            log.info("ResponseCode:" + conn.getResponseCode(), "[%s]");

            // 7. 获取响应的流
            // 得到服务器写回的响应数据
            return conn.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
            throw AppCode.A00100.toUserException(e.toString());
        }
    }

    private static String bufferReaderToStr(InputStream inputStream, String charSet, boolean gzip){
        try {
//        if(gzip){
//            return ZipUtils.gzipRestore(inputStream);
//        }
            BufferedReader br = null;
            // 默认字符编码GBK
            if (StringUtils.isNotBlank(charSet)) {
                br = new BufferedReader(new InputStreamReader(inputStream, charSet));
            } else {
                br = new BufferedReader(new InputStreamReader(inputStream));
            }
            String readLine;
            StringBuilder builder = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                // 一次读一行，所以我也要换行
                builder.append(readLine + "\r\n");
            }
            String result = builder.toString();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw AppCode.A00100.toUserException(e.toString());
        }
    }

}
