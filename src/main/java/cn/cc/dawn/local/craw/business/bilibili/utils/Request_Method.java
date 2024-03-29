package cn.cc.dawn.local.craw.business.bilibili.utils;

import cn.cc.dawn.local.craw.business.bilibili.constant.BilConstant;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Yukino
 * 2020/3/3
 */
@Slf4j
public class Request_Method {
    
    /**
     * 用来处理普通的没有特殊请求头的js请求
     * 用来获取数据
     * 第二个参数不用的时候传 null
     * @param urlPath
     * @param sendMsg
     * @param requistType
     * @return
     * @throws Exception
     */
    public static String js_commom(String urlPath,String sendMsg,String requistType)throws Exception{
        log.info("js_commom请求地址:"+urlPath);
        //2, 打开连接
        HttpURLConnection conn = Request_Heard.request_UserAgent(urlPath,sendMsg,requistType);
        //得到服务器写回的响应数据
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String str = br.readLine();
        log.info("js_commom响应内容为:  " + str);
        return  str;
    }

    public static String js_commom(String urlPath,String requistType)throws Exception{
        return  js_commom(urlPath,null,requistType);
    }

    /**
     * 为了获取json数据
     * @param urlPath
     * @param requistType
     * @return
     * @throws Exception
     */
    public static String js_headers(String urlPath,String requistType)throws Exception{
        log.info("js_headers请求地址为:"+urlPath);
        HttpURLConnection conn = Request_Heard.requestHeard_FlvUrl(urlPath,requistType);
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String str = br.readLine();
        log.info("js_headers响应内容为:" + str);
        return  str;
    }

    /**
     * 用来处理普通的没有特殊请求头的，
     * 获取html
     * @param urlPath
     * @throws Exception
     */
    public static void html_common(String urlPath)throws Exception{
        Connection connection1 = Jsoup.connect(urlPath);// 获取连接
        connection1.header("User-Agent", BilConstant.userAgent);// 配置模拟浏览器
        Connection.Response response = connection1.execute();// 获取响应
        System.out.println(response.body());
    }

}
