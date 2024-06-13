/**
 * @Description
 * @Author everforcc
 * @Date 2023-05-27 22:19
 * Copyright
 */

package cn.cc.dawn.craw.ai9w.menu;

import cn.cc.dawn.local.craw.constant.CrawConstant;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MenuImpl implements IMenu {
    @Override
    public String html(String url) {
        Connection connection = Jsoup.connect(url);// 获取连接
        connection.header("User-Agent", CrawConstant.userAgent);// 配置模拟浏览器
        Connection.Response login = null;// 获取响应
        try {
            login = connection.execute();
        } catch (IOException e) {
            System.err.println("e: " + e);
            e.printStackTrace();
        }

        String html = login.body();
        //System.out.println(html);

        return html;
    }

    @Override
    public String title(String html) {
        Document d1 = Jsoup.parse(html);// 转换为Dom树
        String title = d1.getElementsByClass("nr_function").get(0)
                .getElementsByTag("h1").get(0).text();
        System.out.println(title);
        return title;
    }

    @Override
    public String content(String html) {
        Document d1 = Jsoup.parse(html);// 转换为Dom树
        String content = d1.getElementById("novelcontent").text();
        System.out.println(content);
        return content;
    }

    @Override
    public String nextUrl(String html) {
        Document d1 = Jsoup.parse(html);// 转换为Dom树
        String href = d1.getElementsByClass("page_chapter").get(0)
                .getElementsByTag("a").get(3).attr("href");
        System.out.println(href);
        return href;
    }

    @Override
    public void write(int i, String title, String content) {
        try {
            title = "第" + i + "章 " + title;
            FileUtils.writeLines(new File("D:/java/test/novel.txt"), Arrays.asList(title, content), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
