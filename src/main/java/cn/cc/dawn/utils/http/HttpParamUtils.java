package cn.cc.dawn.utils.http;

import cn.cc.dawn.utils.constant.CharsetsConstant;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.constant.NumberConstant;
import cn.cc.dawn.utils.exception.AppCode;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class HttpParamUtils {


    /**
     * google的工具包
     * @param source
     * @return
     */
    public static String asUrlParamsGuava(Map<String, String> source){
        // TODO 如果要编码的话自己加下编码逻辑
        return Joiner.on(CommonCharConstant.AND)
                // 用指定符号代替空值,key 或者value 为null都会被替换
                .useForNull(CommonCharConstant.EMP)
                .withKeyValueSeparator(CommonCharConstant.EQ)
                .join(source);
    }

    /**
     * 只要确保你的编码输入是正确的,就可以忽略掉 UnsupportedEncodingException
     */
    public static String asUrlParams(Map<String, String> source){
        Iterator<String> it = source.keySet().iterator();
        StringBuilder paramStr = new StringBuilder();
        while (it.hasNext()){
            String key = it.next();
            String value = source.get(key);
            if (StringUtils.isBlank(value)){
                continue;
            }
            try {
                // URL 编码
                value = URLEncoder.encode(value, CharsetsConstant.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                // do nothing
            }
            paramStr.append(CommonCharConstant.AND).append(key).append(CommonCharConstant.EQ).append(value);
        }
        // 去掉第一个&
        return paramStr.substring(NumberConstant.N_1);
    }

    public static String getRootUrl(String weburl){
        URL url = null;
        try {
            url = new URL(weburl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url.getProtocol() + CommonCharConstant.URL_SPLIT + url.getHost();
    }

    public static boolean checkUrlEffect(String weburl){
        URL url = null;
        try {
            url = new URL(weburl);
        } catch (MalformedURLException e) {
            AppCode.A09000.toUserException(e.toString());
            return false;
        }
        return true;
    }

}
