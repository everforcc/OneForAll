package cn.cc.dawn.business.novel.service;

import cn.cc.dawn.business.novel.dao.NovelCapterMapper;
import cn.cc.dawn.business.novel.dao.NovelMapper;
import cn.cc.dawn.business.novel.dto.NovelCapterDto;
import cn.cc.dawn.business.novel.dto.NovelDto;
import cn.cc.dawn.business.novel.dto.NovelReqRegDto;
import cn.cc.dawn.business.novel.flow.CommonFlow;
import cn.cc.dawn.common.dao.WebSiteDataMapper;
import cn.cc.dawn.common.dao.WebSiteMapper;
import cn.cc.dawn.common.dao.WebSiteTagMapper;
import cn.cc.dawn.common.dto.WebSiteDataDto;
import cn.cc.dawn.common.dto.WebSiteDto;
import cn.cc.dawn.common.dto.WebSiteTagDto;
import cn.cc.dawn.common.service.WebSiteDataService;
import cn.cc.dawn.common.service.WebSiteService;
import cn.cc.dawn.common.service.WebSiteTagService;
import cn.cc.dawn.utils.constant.LogConstant;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("novelService")
@Transactional
public class NovelService {

    /*@Resource
    private WebSiteMapper webSiteMapper;*/
    @Resource
    WebSiteService webSiteService;
    /*@Resource
    private WebSiteTagMapper webSiteTagDtoMapper;*/
    /*@Resource
    WebSiteDataMapper webSiteDataMapper;*/
    @Resource
    WebSiteDataService webSiteDataService;

    @Resource
    WebSiteTagService webSiteTagService;

    @Resource
    NovelMapper novelMapper;
    @Resource
    NovelCapterMapper novelCapterMapper;

    /**
     * 测试展示某一个
     * @return
     */
    public String show(){
        return novelCapterMapper.selectUrl("http://www.dzwx520.com/book_8764/4089171.html").getContent();
    }

    public List<NovelDto> listNovel(int pn,int size){
        return novelMapper.selectPage(pn,size);
    }

    public List<NovelCapterDto> listCap(int parentid,int pn,int size){
        // 根据传入的id和小说名来校验是否正确然后返回数据，随后全局改为uuid
        return novelCapterMapper.selectPage(parentid,pn,size);
    }

    public String readContent(int id,String capterName){
        // 根据传入的id和小说名来校验是否正确然后返回数据，随后全局改为uuid
        return novelCapterMapper.selectContent(id,capterName);
    }

    /**
     * 1. 插入web表，文件名字插入小说表
     * 待优化逻辑
     */
    @Transactional
    public void crawFlow(){
        String webName = "大众文学网";
        String novelUrl = "http://www.dzwx520.com/book_8764/";
        String webroot = "http://www.dzwx520.com";

        // 1. web主表
        WebSiteDto webSiteDto = new WebSiteDto();
//        if("1".equals(webSiteMapper.existByUrl(webroot))){
//            webSiteDto = webSiteMapper.selectByUrl(webroot);
//            log.info("web主表已存在: " + webroot);
//        }else {
            webSiteDto.setWebroot(webroot);
            webSiteDto.setWebname(webName);
//            int webResult = webSiteMapper.insert(webSiteDto);
//            log.info("webResult: " + webResult);
//        }
        webSiteDto = webSiteService.insert(webSiteDto);

        NovelReqRegDto reqRegDto = web_dzwx520();

        String html = CommonFlow.regToParam(reqRegDto,novelUrl);

        String novelName = CommonFlow.novelName(reqRegDto,novelUrl,html);
        String capterUrl = CommonFlow.capterUrl(reqRegDto,novelUrl,html);


        // 2.1. tag表
        WebSiteTagDto webSiteTagDto = new WebSiteTagDto();
//        if("1".equals(webSiteTagDtoMapper.existByUrl(novelUrl))){
//            webSiteTagDto = webSiteTagDtoMapper.selectByUrl(novelUrl);
//            log.info("tag表已存在: " + novelUrl);
//        }else {
            webSiteTagDto.setWebrootid(webSiteDto.getId());
            webSiteTagDto.setWeburl(novelUrl);
            webSiteTagDto.setWebname(novelName);
//            int tagResult = webSiteTagDtoMapper.insert(webSiteTagDto);
//            log.info("tagResult: " + tagResult);
//        }
        webSiteTagDto = webSiteTagService.insert(webSiteTagDto);

        // 2.2. 数据表
        WebSiteDataDto webSiteDataDto = new WebSiteDataDto();
//        if("1".equals(webSiteDataMapper.existBYParentid(webSiteTagDto.getId()))){
//            webSiteDataDto = webSiteDataMapper.selectBYParentid(webSiteTagDto.getId());
//            log.info("数据表已存在: " + webSiteDataDto.getParentid());
//        }else {
            webSiteDataDto.setParentid(webSiteTagDto.getId());
            webSiteDataDto.setType(1);
            webSiteDataDto.setHtml(html);
//            int dataResult = webSiteDataMapper.insert(webSiteDataDto);
//            log.info("dataResult: " + dataResult);
//        }
        webSiteDataDto = webSiteDataService.insert(webSiteDataDto);

        // 2.3. 小说主表
        NovelDto novelDto = new NovelDto();
//        if("1".equals(novelMapper.existUrl(novelUrl))){
//            novelDto = novelMapper.selectSourceurl(novelUrl);
//            log.info("小说主表: " + novelUrl);
//        }else {
            novelDto.setName(novelName);
            novelDto.setSourcename(webName);
            novelDto.setSourceurl(novelUrl);
//            int novelResult = novelMapper.insert(novelDto);
//            log.info("novelResult: " + novelResult);
//        }
        novelDto = novel(novelDto);

        log.info(LogConstant.SPLIT + "111111111111111111");

        Map<String,String> map = CommonFlow.capterList(reqRegDto,html);

        map.forEach((k,v)->log.info(LogConstant.SPLIT + "k: " + k + "v: " + v));


        for(Map.Entry entry:map.entrySet()){
            String mapUrlCapter = (String) entry.getKey();
            String mapNameCapter = (String) entry.getValue();

            log.info("mapUrlCapter: " + mapUrlCapter);
            log.info("mapNameCapter: " + mapNameCapter);
            // 3.1 tag表
            WebSiteTagDto webSiteTagCapter = new WebSiteTagDto();
            webSiteTagCapter.setWebname(mapNameCapter);
            webSiteTagCapter.setWebrootid(webSiteDto.getId());
            webSiteTagCapter.setWeburl(mapUrlCapter);
            webSiteTagCapter = webSiteTagService.insert(webSiteTagCapter);

            // 3.2 数据表
            String htmlCapter = CommonFlow.regToParam(reqRegDto,mapUrlCapter);
            WebSiteDataDto webSiteDataCapter = new WebSiteDataDto();
            webSiteDataCapter.setType(1);
            webSiteDataCapter.setParentid(webSiteTagCapter.getId());
            webSiteDataCapter.setHtml(htmlCapter);
            webSiteDataCapter = webSiteDataService.insert(webSiteDataCapter);

            // 3.3 小说子表
            NovelCapterDto novelCapterDto = new NovelCapterDto();
            novelCapterDto.setCaptername(mapNameCapter);
            novelCapterDto.setSourceurl(mapUrlCapter);
            novelCapterDto.setContenttype(1);
            novelCapterDto.setParentid(novelDto.getId());
            novelCapterDto.setContentid(webSiteDataCapter.getId());

            String contentCapter = CommonFlow.content(reqRegDto,htmlCapter);
            novelCapterDto.setContent(contentCapter);

            novelCapterDto = novelCapter(novelCapterDto);
        }

    }

    public NovelDto novel(NovelDto novelDto){
        if("1".equals(novelMapper.existUrl(novelDto.getSourceurl()))){
            novelDto = novelMapper.selectSourceurl(novelDto.getSourceurl());
            log.info("小说主表: " + novelDto.getSourceurl());
        }else {
            int novelResult = novelMapper.insert(novelDto);
            log.info("novelResult: " + novelResult);
        }
        return novelDto;
    }

    public NovelCapterDto novelCapter(NovelCapterDto novelCapterDto){
        if("1".equals(novelCapterMapper.existUrl(novelCapterDto.getSourceurl()))){
            novelCapterDto = novelCapterMapper.selectUrl(novelCapterDto.getSourceurl());
        }else {
            int novelCapterResult = novelCapterMapper.insert(novelCapterDto);
            log.info("novelCapterResult: " + novelCapterResult);
        }
        return novelCapterDto;
    }

    public NovelReqRegDto web_dzwx520() {
        Map<String, String> headersMap = new HashMap<>();
        Map<String, String> authonMap = new HashMap<>();
        NovelReqRegDto novelReqRegDto = new NovelReqRegDto();

        novelReqRegDto.setCharset(CharsetsEnum.GBK);
        // 1.
        novelReqRegDto.setHeaders(headersMap);
        // 2.
        novelReqRegDto.setAuthon(authonMap);
        // 3.
        novelReqRegDto.setRegWebName("");
        // 4. 小说 url 目前直接从已知小说名开始
        novelReqRegDto.setRegNovelUrl("");
        // 5. 小说 名
        novelReqRegDto.setRegNovelName("//div[@class='book_info']//h1/text()");
        // 6. 章节列表 url
        novelReqRegDto.setRegCapterListUrl("");
        // 6.2
        novelReqRegDto.setRegCapterListUrlNext("");
        // 6.3
        novelReqRegDto.setRegCapterListUrlEnd("");
        // 6.4 章节列表 内容
        //novelReqRegDto.setRegCapterNameList("//div[@class='book_list boxm']//ul//a/@href|//div[@class='book_list boxm']//ul//a/@title");
        novelReqRegDto.setRegCapterList("//div[@class='book_list boxm']//ul//a");
        novelReqRegDto.setRegCapterUrl("//a/@href");
        novelReqRegDto.setRegCapterName("//a/@title");

        // 7.1 章节内容
        novelReqRegDto.setRegContentUrl("");
        novelReqRegDto.setRegContentUrlNext("");
        novelReqRegDto.setRegContentUrlEnd("");
        // 7.4
        novelReqRegDto.setRegContent("//div[@id='htmlContent']");

        return novelReqRegDto;
    }
}
