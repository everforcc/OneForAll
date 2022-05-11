package cn.cc.dawn.local.craw.business.bilibili.flow;

import cn.cc.dawn.local.craw.business.bilibili.constant.BilConstant;
import cn.cc.dawn.local.craw.business.bilibili.utils.BilHelper;
import cn.cc.dawn.local.craw.business.bilibili.utils.Method_down;
import cn.cc.dawn.local.craw.constant.CrawConstant;
import cn.cc.dawn.utils.file.FilePath;
import cn.cc.dawn.utils.http.HttpParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Yukino
 * 2020/3/3
 */
@Slf4j
public class Bilibili_Cover {

    /**
     * 用最基础的正则表达式来匹配
     * 这一块儿目前不是很完善，有空再完善
     */

    /**
     * 随后修改为直接 从链接  获取封面
     */

    /**
     * 根据av号获取html
     * @param avnum
     * @throws Exception
     */
    @Deprecated
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
            String url = element.attr("content");
            Matcher matcher = pattern.matcher(url);
            //是否匹配到了
            if (matcher.matches()) {
                log.info(url);
                //如果匹配到了下载
                Method_down.down(
                        url,
                        FilePath.build(BilConstant.BILIBILI_FILE_PATH_BUSI).ofPath("封面").path(),
                        avnum + HttpParamUtils.fileSuffixFromUrl(url,true)
                );//传图片后坠
            }
        }
    }

    BilHelper bilHelper = new BilHelper();
    //log log = logFactory.getlog("Bilibili_Cover");

    public String  getBilCover(String avnum){
        /**
         * 默认给个bilibili_icon
         */
        String url = "https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2810627290,1080409091&fm=58&s=8197C732C535FA313E526557030030BB&bpow=121&bpoh=75";
        avnum = bilHelper.inputToAV(avnum);
        if(avnum.equals("false")){
            return url;
        }
        //String avnum="69345392";
        Connection connection = Jsoup.connect("https://www.bilibili.com/video/av"+avnum);// 获取连接
        connection.header("User-Agent", CrawConstant.userAgent);// 配置模拟浏览器
        Connection.Response login = null;// 获取响应
        try {
            login = connection.execute();
        } catch (IOException e) {
            log.error("getBilCover---catch");
            e.printStackTrace();
        }

        log.info("----------");
        log.info(login.body());
        Document d1 = Jsoup.parse(login.body());// 转换为Dom树

        Elements elements_meta = d1.getElementsByTag("meta");

        log.info("----------");
        log.info(elements_meta.toString());

        int size = elements_meta.size();
        for(int i = 10; i<size; i++){
            Element element_url = elements_meta.get(i);
            if("image".equals(element_url.attr("itemprop"))){
                url = element_url.attr("content");
                break;
            }
        }
//        Element element_url = elements_meta.get(10);
//        log.info("----------");
//        log.info(element_url.toString());
//        if("image".equals(element_url.attr("itemprop"))){
//            url = element_url.attr("content");
//        }

        return url;
    }

}
