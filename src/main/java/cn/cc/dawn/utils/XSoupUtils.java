package cn.cc.dawn.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.util.List;

public class XSoupUtils {

    public static List<String> compileList(String pattern, Document document){
        return Xsoup.compile(pattern).evaluate(document).list();
    }

    public static String compileStr(String pattern,Document document){
        return Xsoup.compile(pattern).evaluate(document).get();
    }

    public static Document htmlToDocument(String html){
        return Jsoup.parse(html);
    }

}
