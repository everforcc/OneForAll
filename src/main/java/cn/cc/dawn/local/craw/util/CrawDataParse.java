package cn.cc.dawn.local.craw.util;

import cn.cc.dawn.utils.http.HttpParamUtils;

/**
 * 爬虫时常用的数据处理
 */
public class CrawDataParse {

    /**
     * https://www.baidu.com/
     * 返回 www.baidu.com
     * @param url
     * @return
     */
    public static String urlRoot(String url){
        return HttpParamUtils.getRootUrl(url);
    }

    /**
     * 根据url来生成目录
     * www.baidu.com/a/b/c
     * /www.baidu.com/a/b/c
     * @param url
     * @return
     */
    public static String urlToPath(String url){
        return HttpParamUtils.urlToPathWithOutLast(url);
    }

    /**
     * 默认后缀
     * 从url获取文件名
     * @param url
     * @return
     */
    public static String urlToFileName(String url){
        return HttpParamUtils.fileNameFromUrl(url);
    }

    /**
     * url最后的目录生成文件名
     * 从url获取文件名
     * @param url
     * @param fileType
     * @return
     */
    public static String urlLastPathToName(String url,String fileType){
        return fileType + HttpParamUtils.fileNameFromUrl(url);
    }

    /**
     * 取出链接中指定位置的数据
     * @param url
     * @param index
     * @return
     */
    public static String urlIndexPathToName(String url,int index){
        return "";
    }

}
