package cn.cc.dawn.open.novel.flow;

import cn.cc.dawn.open.novel.dto.NovelReqRegDto;
import cn.cc.dawn.local.craw.web.data.dto.HttpParamDto;
import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.constant.LogConstant;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

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
    public static String novelName(NovelReqRegDto novelReqRegDto, String novelUrl, String html){

        if(ObjectUtils.isNull(novelReqRegDto)){
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

        if(ObjectUtils.isNull(novelReqRegDto)){
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
        if(ObjectUtils.isNull(novelReqRegDto)){
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
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setHeaders(novelReqRegDto.getHeaders());
        httpParamDto.setUrl(url);
        httpParamDto.setHttpTypeEnum(
                ObjectUtils.isNull(novelReqRegDto.getRegNovelUrlReqType())?novelReqRegDto.getDefaultRequestType():novelReqRegDto.getRegNovelUrlReqType()
        );
        httpParamDto.setCharset(novelReqRegDto.getCharset());
        httpParamDto.setTimeout(novelReqRegDto.getDefaultTimeout());
        String html = httpMethod.getMsg(httpParamDto);
        return html;
    }

    public static String regexList(String regex){
        return "";
    }

}
