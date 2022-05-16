package cn.cc.dawn.utils.http;

import cn.cc.dawn.local.craw.constant.CrawConstant;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.Map;

/**
 * Yukino
 * 2020/7/3
 */
@Slf4j
public class JsoupUtils {


    /**
     *  用来处理链接返回html
     *  旧时代的东西，信息在页面藏着，需要解析
     */

    //private static log log = logFactory.getlog(HtmlHelper.class);

    public static Document getDocumentByURL(String url){
        return getDocument(url,null);
    }

    public static Document getDocumentByURL(String url, Map<String,String> mapHeards){
        return getDocument(url,mapHeards);
    }
    /**
     * 不带请求头的
     * @param url
     * @return
     */
    private static Document getDocument(String url, Map<String,String> mapHeards){
        log.debug( "url:" + url );
        Connection connection = Jsoup.connect(url);// 获取连接
        // 配置模拟浏览器, 通用请求头，
        connection.timeout(1000*20);
        if(null==mapHeards) {
            connection.header("User-Agent", CrawConstant.userAgent);
        }else {
            log.debug( "mapHeards:" + mapHeards.toString() );
            for (Map.Entry<String, String> entry : mapHeards.entrySet()) {
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                System.out.println(mapKey + ":" + mapValue);
                connection.header(mapKey, mapValue);// 配置模拟浏览器
            }
        }
        Connection.Response login = null;// 获取响应
        try {
            login = connection.execute();
        } catch (IOException e) {
            log.error("HtmlHelper.getDocument()"+e.toString());
            e.printStackTrace();
        }
        Document document = Jsoup.parse(login.body());
        return document;
    }




}
