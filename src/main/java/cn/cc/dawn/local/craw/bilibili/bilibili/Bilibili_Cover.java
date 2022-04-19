package cn.cc.dawn.local.craw.bilibili.bilibili;

import cn.cc.dawn.local.craw.bilibili.utils.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Yukino
 * 2020/3/3
 */
public class Bilibili_Cover {

    /**
     * 用最基础的正则表达式来匹配
     * 这一块儿目前不是很完善，有兴趣的自行完善
     */


    /**
     * 随后修改为直接 从链接  获取封面
     */

    /**
     * 根据av号获取html
     * @param avnum
     * @throws Exception
     */
    public static void getImgByAV(String avnum) throws Exception{
        BilHelper bilHelper = new BilHelper();
        //avnum="69345392";
        Connection connection = Jsoup.connect("https://www.bilibili.com/video/av"+ bilHelper.inputToAV(avnum));// 获取连接
        connection.header("User-Agent", BilConstant.userAgent);// 配置模拟浏览器
        Connection.Response login = connection.execute();// 获取响应
        Document d1 = Jsoup.parse(login.body());// 转换为Dom树
        List<Element> et = d1.select("meta[itemprop]");// 获取form表单，可以通过查看页面源码代码得知

        for (Element element : et) {
            //正则匹配url
            Pattern pattern = Pattern.compile(BilConstant.regex);
            Matcher matcher = pattern.matcher(element.attr("content"));
            //是否匹配到了
            if (matcher.matches()) {
                System.out.println(element.attr("content"));
                String url=element.attr("content");
                //如果匹配到了下载
                Method_down.downByUrl(element.attr("content"),"封面\\",avnum+"."+url.substring(url.lastIndexOf(".")+1,url.length()));//传图片后坠
            }
        }
    }

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
        connection.header("User-Agent", cn.cc.dawn.utils.Constant.userAgent);// 配置模拟浏览器
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
