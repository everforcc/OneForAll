package cn.cc.dawn.local.craw.business.sp4u.service;

import cn.cc.dawn.local.craw.business.sp4u.constant.Sp4uUrl;
import cn.cc.dawn.local.craw.business.data.dao.WebSiteDataMapper;
import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.config.init.properties.PropertiesHeader;
import cn.cc.dawn.config.init.yml.APPConfigurationTest;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.impl.IHttpUrlConnectImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("sp4uService")
@Transactional
public class Sp4uService {

    /**
     * 完整流程
     */
    IHttp IHttp = new IHttpUrlConnectImpl();

    @Autowired
    private APPConfigurationTest configurationData;
    @Resource
    WebSiteDataMapper webSiteDoMapper;

    public int flow(String url){
//        int result = 0;
//        WebSiteDataDto webSiteDo = new WebSiteDataDto();
//        try {
//            // 1. 组装请求数据
//            HttpParam httpParam = getHttpParam(url);
////            webSiteDo.dealUrl(httpParam.getUrl());
//            WebSiteDataDto resultVO = webSiteDoMapper.selectBYUrlParams(webSiteDo.getUrl(),webSiteDo.getParams());
//
//            if(Objects.isNull(resultVO)) {
//                log.info("网络获取数据: " + httpParam.toString());
//                // 2. 请求返回HTML
//                String html = httpMethod.getMsg(httpParam);
//                log.info(html);
//                webSiteDo.setData(html);
//                // 3. 将数据入库
//                result = webSiteDoMapper.insert(webSiteDo);
//                log.info("result:" + result);
//            }else {
//                log.info("从数据库获取: " + resultVO.toString());
//            }
//
//            //
//            extract(resultVO.getHtml());
//
//            // 4. 随机数延迟
//            ThreadUtils.sleep(MathUtils.getRandom(0,1));
//            // 5. 进行下一步
//
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return 0;
    }

    private HttpParamDto getHttpParam(String url){
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setCharset(CharsetsEnum.big5);
        httpParamDto.setProxy(configurationData.getGproxy().getIp(),configurationData.getGproxy().getPort());
        httpParamDto.setHttpTypeEnum(HttpTypeEnum.GET);
        httpParamDto.setTimeout(600);
        //httpParam.setUrl("http://spring4u.info/viewthread.php?tid=61626&extra=page%3D2");
        httpParamDto.setUrl(url);
        httpParamDto.setHeaders(PropertiesHeader.spring4uMap());
        return httpParamDto;
    }

    /**
     * 1. 拿到地址请求数据
     * 2. 下载完整页面到公共表/包括分页
     * 3. 提取出标题到小说表
     */
    public void extract(String html){
        Document document = XSoupUtils.htmlToDocument(html);
        //  章节列表
        List<String> stringList = XSoupUtils.compileList(Sp4uUrl.xp_chapter_list,document);
        int size = stringList.size();
        log.info("测试size: " + stringList.size() + "");
        //log.info(str.toString());
        // 剔除前两个后两个

        for(int i=size-3;i<size-2;i++){
            // 列表取出每个章节的信息

            String chapterListHtml = stringList.get(i);
            Document chapterListDivdocument = XSoupUtils.htmlToDocument(chapterListHtml);
            List<String> chaptermsgList = XSoupUtils.compileList(Sp4uUrl.xp_chaptermsg_list,chapterListDivdocument);
            // 从里面取出本章节的信息

            int divstringListSize = chaptermsgList.size();
            log.info("章节信息数量: " + divstringListSize);
            String chapterNameUrlHtml = chaptermsgList.get(2);
            log.info("chapterNameUrlHtml:\r\n" + chapterNameUrlHtml);
            Document chapterNameUrldocument = XSoupUtils.htmlToDocument(chapterNameUrlHtml);
            // TODO Xsoup 格式化问题
            log.info("td/body 格式化之后 td消失，只剩下body下的内容，随后看下源码分析");
            //log.info("chapterNameUrldocument:\r\n" + chapterNameUrldocument.body());
            List<String> chaptermsgUrl = XSoupUtils.compileList(Sp4uUrl.xp_chaptermsg_url,chapterNameUrldocument);
            log.info("章节地址: " + chaptermsgUrl.toString());
            String chaptermsgName = XSoupUtils.compileStr(Sp4uUrl.xp_chaptermsg_name,chapterNameUrldocument);
            log.info("章节名: " + chaptermsgName);
            String chapterIssueTimeHtml = chaptermsgList.get(4);
            //log.info("chapterNameUrlHtml:\r\n" + chapterIssueTimeHtml);
            Document chapterIssueTimedocument = XSoupUtils.htmlToDocument(chapterIssueTimeHtml);
            //log.info("chapterIssueTimedocument:\r\n" + chapterIssueTimedocument);
            String chapterIssueTime = XSoupUtils.compileStr(Sp4uUrl.xp_chaptermsg_issuetime,chapterIssueTimedocument);
            log.info("章节更新时间:"+ chapterIssueTime);
            /*for (int j=0; j<divstringListSize; j++){
                log.info(j + LogConstant.START);
                log.info(chaptermsgList.get(j));
                *//*
                 * 章节名
                 * 章节链接 list
                 * 作者名
                 * 作者 链接
                 * 发布日期
                 * 评论数量
                 * 阅读数
                 *
                 *//*

                log.info(j + LogConstant.END);
            }*/

        }
    }

}
