package cn.cc.dawn.business.novel.flow;

import cn.cc.dawn.business.novel.dto.NovelReqRegDto;
import cn.cc.dawn.common.dto.HttpParam;
import cn.cc.dawn.utils.ObectUtils;
import cn.cc.dawn.utils.StringUtils;
import cn.cc.dawn.utils.constant.LogConstant;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CommonFlow {

    /**
     * 公共的一些流程
     */

    private static HttpMethod httpMethod = new HttpApacheImpl();

    /**
     * 小说名到章节清单
     * @param novelReqRegDto
     * @param novelUrl
     */
    public static String novelName(NovelReqRegDto novelReqRegDto,String novelUrl,String html){

        if(ObectUtils.isNull(novelReqRegDto)){
            return "";
        }

        //
        //String html = regToParam(novelReqRegDto,novelUrl);

        // 1. 取出小说名
        String novelName = XSoupUtils.htmlToStr(html,novelReqRegDto.getRegNovelName());
        log.info("获取小说名: " + novelName);

        return novelName;
    }

    public static String capterUrl(NovelReqRegDto novelReqRegDto,String novelUrl,String html){
        // 2. 取出章节清单首页
        // 如果没有对应正则说明当前就是首页的模式如果有则取出
        String capterUrl = "";
        if(StringUtils.isEmpty(novelReqRegDto.getRegCapterListUrl())){
            capterUrl = novelUrl;
        }else {
            capterUrl = XSoupUtils.htmlToStr(html,novelReqRegDto.getRegCapterListUrl());
        }

        return capterUrl;
    }

    /**
     * 获取章节名和链接
     * @param novelReqRegDto
     * @param html
     * @return
     */
    public static Map<String,String> capterList(NovelReqRegDto novelReqRegDto,String html){
        Map<String,String> map = new LinkedHashMap<>();

        if(ObectUtils.isNull(novelReqRegDto)){
            return map;
        }

        //
        //String html = regToParam(novelReqRegDto,capterListUrl);

        // 1. 取出小说名
        List<String> novelCapterList = XSoupUtils.htmlToList(html,novelReqRegDto.getRegCapterList());

        //novelName.forEach(e -> log.info("章节清单: " + e));
        for(String strA: novelCapterList){
            Document document = XSoupUtils.htmlToDocument(strA);
            String capterUrlTemp = XSoupUtils.compileStr(novelReqRegDto.getRegCapterUrl(),document);
            String capterNameTemp = XSoupUtils.compileStr(novelReqRegDto.getRegCapterName(),document);
            log.info("k: " + capterUrlTemp + " \r\nv: " + capterNameTemp);
            map.put(capterUrlTemp,capterNameTemp);
        }

        // 等待返回入库
        return map;
    }

    /**
     * 获取章节内容
     * @param novelReqRegDto
     * @param html
     */
    public static String content(NovelReqRegDto novelReqRegDto,String html){
        if(ObectUtils.isNull(novelReqRegDto)){
            return "";
        }

        //
        // String html = regToParam(novelReqRegDto,capterUrl);

        // 1. 取出小说名
        String novelContent = XSoupUtils.htmlToStr(html,novelReqRegDto.getRegContent());

        log.info(LogConstant.SPLIT);
        log.info(novelContent);
        return novelContent;
    }

    public static String regToParam(NovelReqRegDto novelReqRegDto,String url){
        HttpParam httpParam = new HttpParam();
        httpParam.setHeaders(novelReqRegDto.getHeaders());
        httpParam.setUrl(url);
        httpParam.setHttpTypeEnum(
                ObectUtils.isNull(novelReqRegDto.getRegNovelUrlReqType())?novelReqRegDto.getDefaultRequestType():novelReqRegDto.getRegNovelUrlReqType()
        );
        httpParam.setCharset(novelReqRegDto.getCharset());
        httpParam.setTimeout(novelReqRegDto.getDefaultTimeout());
        String html = httpMethod.getMsg(httpParam);
        return html;
    }


}