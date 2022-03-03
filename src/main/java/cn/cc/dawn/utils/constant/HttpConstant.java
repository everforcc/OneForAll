package cn.cc.dawn.utils.constant;

import org.apache.http.HttpHeaders;

public class HttpConstant {

    public static final String image_jpeg = "image/jpeg";

    private static String max_age= "max-age=";

    /**
     * apache的工具包常用的请求头
     */
    public static String apacheHTTPHeaders = HttpHeaders.ACCEPT;
    /**
     * google的工具包常用的请求头
     */
    public static String googleHTTPHeaders = com.google.common.net.HttpHeaders.ACCEPT;


}
