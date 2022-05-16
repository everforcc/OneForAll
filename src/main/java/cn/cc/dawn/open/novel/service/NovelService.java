//package cn.cc.dawn.open.novel.service;
//
//import cn.cc.dawn.open.novel.dao.NovelCapterMapper;
//import cn.cc.dawn.open.novel.dao.NovelMapper;
//import cn.cc.dawn.open.novel.dto.NovelCapterDto;
//import cn.cc.dawn.open.novel.dto.NovelDto;
//import cn.cc.dawn.open.novel.constant.NovelReqRegDto;
//import cn.cc.dawn.open.novel.flow.CommonFlow;
//import cn.cc.dawn.local.craw.business.data.dto.WebSiteDataDto;
//import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
//import cn.cc.dawn.local.craw.business.data.dto.WebSiteTagDto;
//import cn.cc.dawn.local.craw.business.data.service.impl.WebSiteDataServiceImpl;
//import cn.cc.dawn.local.craw.business.data.service.impl.WebSiteServiceImpl;
//import cn.cc.dawn.local.craw.business.data.service.impl.WebSiteTagServiceImpl;
//import cn.cc.dawn.utils.constant.LogConstant;
//import cn.cc.dawn.utils.enums.CharsetsEnum;
//import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Service("novelService")
//@Transactional
//public class NovelService {
//
//    /*@Resource
//    private WebSiteMapper webSiteMapper;*/
//    @Resource
//    WebSiteServiceImpl webSiteService;
//    /*@Resource
//    private WebSiteTagMapper webSiteTagDtoMapper;*/
//    /*@Resource
//    WebSiteDataMapper webSiteDataMapper;*/
//    @Resource
//    WebSiteDataServiceImpl webSiteDataServiceImpl;
//
//    @Resource
//    WebSiteTagServiceImpl webSiteTagServiceImpl;
//
//    @Resource
//    NovelMapper novelMapper;
//    @Resource
//    NovelCapterMapper novelCapterMapper;
//
//    /**
//     * 测试展示某一个
//     * @return
//     */
//    public String show(){
//        return novelCapterMapper.selectUrl("http://www.dzwx520.com/book_8764/4089171.html").getContent();
//    }
//
//    public List<NovelDto> listNovel(int pn, int size){
//        return novelMapper.selectPage(pn,size);
//    }
//
//    public List<NovelCapterDto> listCap(int parentid, int pn, int size){
//        // 根据传入的id和小说名来校验是否正确然后返回数据，随后全局改为uuid
//        return novelCapterMapper.selectPage(parentid,pn,size);
//    }
//
//    public String readContent(int id,String capterName){
//        // 根据传入的id和小说名来校验是否正确然后返回数据，随后全局改为uuid
//        return novelCapterMapper.selectContent(id,capterName);
//    }
//
//    /**
//     * 1. 插入web表，文件名字插入小说表
//     * 待优化逻辑
//     */
//    @Transactional
//    public void crawFlow(){
//        String webName = "大众文学网";
//        String novelUrl = "http://www.dzwx520.com/book_8764/";
//        String webroot = "http://www.dzwx520.com";
//
//        // 1. web主表
//        WebSiteDto webSiteDto = new WebSiteDto();
////        if("1".equals(webSiteMapper.existByUrl(webroot))){
////            webSiteDto = webSiteMapper.selectByUrl(webroot);
////            log.info("web主表已存在: " + webroot);
////        }else {
//            webSiteDto.setWebroot(webroot);
//            webSiteDto.setWebname(webName);
////            int webResult = webSiteMapper.insert(webSiteDto);
////            log.info("webResult: " + webResult);
////        }
//        webSiteDto = webSiteService.insert(webSiteDto);
//
//        NovelReqRegDto reqRegDto = web_dzwx520();
//
//        String html = CommonFlow.regToParam(reqRegDto,novelUrl);
//
//        String novelName = CommonFlow.novelName(reqRegDto,novelUrl,html);
//        String capterUrl = CommonFlow.capterUrl(reqRegDto,novelUrl,html);
//
//
//        // 2.1. tag表
//        WebSiteTagDto webSiteTagDto = new WebSiteTagDto();
////        if("1".equals(webSiteTagDtoMapper.existByUrl(novelUrl))){
////            webSiteTagDto = webSiteTagDtoMapper.selectByUrl(novelUrl);
////            log.info("tag表已存在: " + novelUrl);
////        }else {
//            webSiteTagDto.setWebrootid(webSiteDto.getId());
//            webSiteTagDto.setWeburl(novelUrl);
//            webSiteTagDto.setWebname(novelName);
////            int tagResult = webSiteTagDtoMapper.insert(webSiteTagDto);
////            log.info("tagResult: " + tagResult);
////        }
//        webSiteTagDto = webSiteTagServiceImpl.insert(webSiteTagDto);
//
//        // 2.2. 数据表
//        WebSiteDataDto webSiteDataDto = new WebSiteDataDto();
////        if("1".equals(webSiteDataMapper.existBYParentid(webSiteTagDto.getId()))){
////            webSiteDataDto = webSiteDataMapper.selectBYParentid(webSiteTagDto.getId());
////            log.info("数据表已存在: " + webSiteDataDto.getParentid());
////        }else {
//            webSiteDataDto.setParentid(webSiteTagDto.getId());
//            webSiteDataDto.setMedium(FileMediumEnum.WINDOWS);
//            webSiteDataDto.setHtml(html);
////            int dataResult = webSiteDataMapper.insert(webSiteDataDto);
////            log.info("dataResult: " + dataResult);
////        }
//        webSiteDataDto = webSiteDataServiceImpl.insert(webSiteDataDto);
//
//        // 2.3. 小说主表
//        NovelDto novelDto = new NovelDto();
////        if("1".equals(novelMapper.existUrl(novelUrl))){
////            novelDto = novelMapper.selectSourceurl(novelUrl);
////            log.info("小说主表: " + novelUrl);
////        }else {
//            novelDto.setName(novelName);
//            novelDto.setSourcename(webName);
//            novelDto.setSourceurl(novelUrl);
////            int novelResult = novelMapper.insert(novelDto);
////            log.info("novelResult: " + novelResult);
////        }
//        novelDto = novelMain(novelDto);
//
//        log.info(LogConstant.SPLIT + "111111111111111111");
//
//        Map<String,String> map = CommonFlow.capterList(reqRegDto,html);
//
//        map.forEach((k,v)->log.info(LogConstant.SPLIT + "k: " + k + "v: " + v));
//
//
//        for(Map.Entry entry:map.entrySet()){
//            String mapUrlCapter = (String) entry.getKey();
//            String mapNameCapter = (String) entry.getValue();
//
//            log.info("mapUrlCapter: " + mapUrlCapter);
//            log.info("mapNameCapter: " + mapNameCapter);
//            // 3.1 tag表
//            WebSiteTagDto webSiteTagCapter = new WebSiteTagDto();
//            webSiteTagCapter.setWebname(mapNameCapter);
//            webSiteTagCapter.setWebrootid(webSiteDto.getId());
//            webSiteTagCapter.setWeburl(mapUrlCapter);
//            webSiteTagCapter = webSiteTagServiceImpl.insert(webSiteTagCapter);
//
//            // 3.2 数据表
//            String htmlCapter = CommonFlow.regToParam(reqRegDto,mapUrlCapter);
//            WebSiteDataDto webSiteDataCapter = new WebSiteDataDto();
//            webSiteDataCapter.setMedium(FileMediumEnum.WINDOWS);
//            webSiteDataCapter.setParentid(webSiteTagCapter.getId());
//            webSiteDataCapter.setHtml(htmlCapter);
//            webSiteDataCapter = webSiteDataServiceImpl.insert(webSiteDataCapter);
//
//            // 3.3 小说子表
//            NovelCapterDto novelCapterDto = new NovelCapterDto();
//            novelCapterDto.setCaptername(mapNameCapter);
//            novelCapterDto.setSourceurl(mapUrlCapter);
//            novelCapterDto.setContenttype(1);
//            novelCapterDto.setParentid(novelDto.getId());
//            novelCapterDto.setContentid(webSiteDataCapter.getId());
//
//            String contentCapter = CommonFlow.content(reqRegDto,htmlCapter);
//            novelCapterDto.setContent(contentCapter);
//
//            novelCapterDto = novelCapter(novelCapterDto);
//        }
//
//    }
//
//    public void crawFlow_test(String webName,String novelUrl,String webroot){
//        //String webName = "大众文学网";
//        //String novelUrl = "http://www.dzwx520.com/book_8764/";
//        //String webroot = "http://www.dzwx520.com";
//
//        /**
//         * 1. web主表
//         * 2.1 tag表
//         * 2.2 数据表
//         * 2.3 小说主表
//         * 3.1 tag
//         * 3.2 数据表
//         * 3.3 小说子表
//         *
//         */
//
//        /**
//         * 1. web主表
//         * 插入前先校验数据是否存在
//         */
//        WebSiteDto webSiteDto = new WebSiteDto(webroot,webName);
//        webSiteDto = webSiteService.insert(webSiteDto);
//
//        NovelReqRegDto reqRegDto = web_dzwx520();
//
//        String html = CommonFlow.regToParam(reqRegDto,novelUrl);
//
//        /**
//         * 利用正则匹配出网站名
//         */
//        String novelName = CommonFlow.novelName(reqRegDto,novelUrl,html);
//        //String capterUrl = CommonFlow.capterUrl(reqRegDto,novelUrl,html);
//
//
//        // 2.1. tag表
//        WebSiteTagDto webSiteTagDto = new WebSiteTagDto();
//        webSiteTagDto.setWebrootid(webSiteDto.getId());
//        webSiteTagDto.setWeburl(novelUrl);
//        webSiteTagDto.setWebname(novelName);
//        webSiteTagDto = webSiteTagServiceImpl.insert(webSiteTagDto);
//
//        // 2.2. 数据表
//        WebSiteDataDto webSiteDataDto = new WebSiteDataDto();
//        webSiteDataDto.setParentid(webSiteTagDto.getId());
//        webSiteDataDto.setMedium(FileMediumEnum.WINDOWS);
//        webSiteDataDto.setHtml(html);
//        webSiteDataDto = webSiteDataServiceImpl.insert(webSiteDataDto);
//
//        // 2.3. 小说主表
//        NovelDto novelDto = new NovelDto();
//        novelDto.setName(novelName);
//        novelDto.setSourcename(webName);
//        novelDto.setSourceurl(novelUrl);
//        novelDto = novelMain(novelDto);
//
//        log.info(LogConstant.SPLIT);
//
//        /**
//         * 章节信息
//         */
//        Map<String,String> map = CommonFlow.capterList(reqRegDto,html);
//        map.forEach((k,v)->log.info(LogConstant.SPLIT + "k: " + k + "v: " + v));
//        for(Map.Entry entry:map.entrySet()){
//            String mapUrlCapter = (String) entry.getKey();
//            String mapNameCapter = (String) entry.getValue();
//
//            log.info("mapUrlCapter: " + mapUrlCapter);
//            log.info("mapNameCapter: " + mapNameCapter);
//            //
//            /**
//             * 3.1 tag表
//             * 网页地址和小说名
//             */
//            WebSiteTagDto webSiteTagCapter = new WebSiteTagDto();
//            webSiteTagCapter.setWebname(mapNameCapter);
//            webSiteTagCapter.setWebrootid(webSiteDto.getId());
//            webSiteTagCapter.setWeburl(mapUrlCapter);
//            webSiteTagCapter = webSiteTagServiceImpl.insert(webSiteTagCapter);
//
//            /**
//             * 3.2 数据表
//             * 网页html
//             */
//            String htmlCapter = CommonFlow.regToParam(reqRegDto,mapUrlCapter);
//            WebSiteDataDto webSiteDataCapter = new WebSiteDataDto();
//            webSiteDataCapter.setMedium(FileMediumEnum.WINDOWS);
//            webSiteDataCapter.setParentid(webSiteTagCapter.getId());
//            webSiteDataCapter.setHtml(htmlCapter);
//            webSiteDataCapter = webSiteDataServiceImpl.insert(webSiteDataCapter);
//
//            //
//            /**
//             * 3.3 小说子表
//             * 处理为可以直接阅读的数据
//             */
//            NovelCapterDto novelCapterDto = new NovelCapterDto();
//            novelCapterDto.setCaptername(mapNameCapter);
//            novelCapterDto.setSourceurl(mapUrlCapter);
//            novelCapterDto.setContenttype(1);
//            novelCapterDto.setParentid(novelDto.getId());
//            novelCapterDto.setContentid(webSiteDataCapter.getId());
//
//            String contentCapter = CommonFlow.content(reqRegDto,htmlCapter);
//            novelCapterDto.setContent(contentCapter);
//
//            novelCapterDto = novelCapter(novelCapterDto);
//        }
//
//    }
//
//    public NovelDto novelMain(NovelDto novelDto){
//        if("1".equals(novelMapper.existUrl(novelDto.getSourceurl()))){
//            novelDto = novelMapper.selectSourceurl(novelDto.getSourceurl());
//            log.info("小说主表: " + novelDto.getSourceurl());
//        }else {
//            int novelResult = novelMapper.insert(novelDto);
//            log.info("novelResult: " + novelResult);
//        }
//        return novelDto;
//    }
//
//    public NovelCapterDto novelCapter(NovelCapterDto novelCapterDto){
//        if("1".equals(novelCapterMapper.existUrl(novelCapterDto.getSourceurl()))){
//            novelCapterDto = novelCapterMapper.selectUrl(novelCapterDto.getSourceurl());
//        }else {
//            int novelCapterResult = novelCapterMapper.insert(novelCapterDto);
//            log.info("novelCapterResult: " + novelCapterResult);
//        }
//        return novelCapterDto;
//    }
//
//    /**
//     * 组装处理的数据，
//     * @return
//     */
//    public NovelReqRegDto web_dzwx520() {
//        Map<String, String> headersMap = new HashMap<>();
//        Map<String, String> authonMap = new HashMap<>();
//        NovelReqRegDto novelReqRegDto = new NovelReqRegDto();
//
//        novelReqRegDto.setCharset(CharsetsEnum.GBK);
//        // 1.
//        novelReqRegDto.setHeaders(headersMap);
//        // 2.
//        novelReqRegDto.setAuthon(authonMap);
//        // 3.
//        novelReqRegDto.setRegWebName("");
//        // 4. 小说 url 目前直接从已知小说名开始
//        novelReqRegDto.setRegNovelUrl("");
//        // 5. 小说 名
//        novelReqRegDto.setRegNovelName("//div[@class='book_info']//h1/text()");
//        // 6. 章节列表 url
//        novelReqRegDto.setRegCapterListUrl("");
//        // 6.2
//        novelReqRegDto.setRegCapterListUrlNext("");
//        // 6.3
//        novelReqRegDto.setRegCapterListUrlEnd("");
//        // 6.4 章节列表 内容
//        //novelReqRegDto.setRegCapterNameList("//div[@class='book_list boxm']//ul//a/@href|//div[@class='book_list boxm']//ul//a/@title");
//        novelReqRegDto.setRegCapterList("//div[@class='book_list boxm']//ul//a");
//        novelReqRegDto.setRegCapterUrl("//a/@href");
//        novelReqRegDto.setRegCapterName("//a/@title");
//
//        // 7.1 章节内容
//        novelReqRegDto.setRegContentUrl("");
//        novelReqRegDto.setRegContentUrlNext("");
//        novelReqRegDto.setRegContentUrlEnd("");
//        // 7.4
//        novelReqRegDto.setRegContent("//div[@id='htmlContent']");
//
//        return novelReqRegDto;
//    }
//}
