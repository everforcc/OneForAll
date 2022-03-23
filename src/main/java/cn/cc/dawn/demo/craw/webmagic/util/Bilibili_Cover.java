package cn.cc.dawn.demo.craw.webmagic.util;



import cn.cc.dawn.utils.Constant;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Yukino
 * 2020/6/22
 */
public class Bilibili_Cover {
    BilHelper bilHelper = new BilHelper();
    Logger logger = LoggerFactory.getLogger("Bilibili_Cover");

    public String  getBilCover(String avnum){
        String url = "https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2810627290,1080409091&fm=58&s=8197C732C535FA313E526557030030BB&bpow=121&bpoh=75";
        avnum = bilHelper.inputToAV(avnum);
        if(avnum.equals("false")){
            return url;
        }
        //String avnum="69345392";
        Connection connection = Jsoup.connect("https://www.bilibili.com/video/av"+avnum);// 获取连接
        connection.header("User-Agent", Constant.userAgent);// 配置模拟浏览器
        Connection.Response login = null;// 获取响应
        try {
            login = connection.execute();
        } catch (IOException e) {
            logger.error("getBilCover---catch");
            e.printStackTrace();
        }
        Document d1 = Jsoup.parse(login.body());// 转换为Dom树

        Elements elements_meta = d1.getElementsByTag("meta");
        //System.out.println(elements_meta);
        Element element_url = elements_meta.get(10);
        if("image".equals(element_url.attr("itemprop"))){
            url = element_url.attr("content");
            System.out.println(url);
        }
        return url;
    }
}
