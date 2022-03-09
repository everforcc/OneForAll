package cn.cc.dawn.utils.constant;

import cn.cc.dawn.utils.enums.ContentTypeEnum;
import org.apache.http.HttpHeaders;

public class HttpConstant {

    /**
     * apache的工具包常用的请求头
     */
    public static String apacheHTTPHeaders = HttpHeaders.ACCEPT;
    /**
     * google的工具包常用的请求头
     */
    public static String googleHTTPHeaders = com.google.common.net.HttpHeaders.ACCEPT;

    public static final String image_jpeg = "image/jpeg";

    public static final String txt_plain = ContentTypeEnum.txt.type;

    public static final String txt_plain_UTF_8 = ContentTypeEnum.txt_UTF_8.type;

    public static final String txt_html = ContentTypeEnum.html.type;

    public static String max_age= "max-age=";

    public static String Content_Type = HttpHeaders.CONTENT_TYPE;

    public static String CACHE_CONTROL = HttpHeaders.CACHE_CONTROL;

    public static String LAST_MODIFIED = HttpHeaders.LAST_MODIFIED;

    public static String EXPIRES = HttpHeaders.EXPIRES;

}
