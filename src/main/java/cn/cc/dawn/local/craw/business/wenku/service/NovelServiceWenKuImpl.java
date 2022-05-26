package cn.cc.dawn.local.craw.business.wenku.service;

import cn.cc.dawn.config.init.yml.APPConfiguration;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDataDto;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteDataService;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteService;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteTagService;
import cn.cc.dawn.utils.http.vo.WebSiteReqVO;
import cn.cc.dawn.local.craw.business.novel.service.INovelServiceBusi;
import cn.cc.dawn.utils.http.ISelenium;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("novelServiceWenKuImpl")
public class NovelServiceWenKuImpl implements INovelServiceBusi {

    @Resource
    ISelenium iSelenium;

    @Resource
    IWebSiteService iWebSiteService;
    @Resource
    IWebSiteTagService iWebSiteTagService;
    @Resource
    IWebSiteDataService iWebSiteDataService;
    @Resource
    APPConfiguration appConfiguration;

    /**
     *  a b c
     *  a 没有bc
     *  b 必须有 a
     *  c 必须有 ab
     *  下面两端逻辑修改，在c的里面处理b，
     *  在b的里面处理a
     *  这样不论从哪个走，一定不会缺数据
     * @param webSiteReqVO
     */
    @Override
    @Transactional
    public void novelContent(WebSiteReqVO webSiteReqVO) {
        /**
         * 插入主表数据
         */
//        WebSiteDto webSiteDto = new WebSiteDto(url);
//        webSiteDto.setSofttype("h-小说");
//        webSiteDto.setWebtype("web");
//        webSiteDto = iWebSiteService.insert(webSiteDto);
        /**
         * 子表数据
         */
        //WebSiteTagDto webSiteTagDto = new WebSiteTagDto();
        //webSiteTagDto.setWebrootid(webSiteDto.getId());
        //webSiteTagDto.setWeburl(url);

        //WebSiteTagDto webSiteTagDto = iWebSiteTagService.insert(webSiteReqVO);
        /**
         * html数据
         * TODO 需要优化这里的逻辑
         */
        //WebSiteDataVO webSiteDataVO = iSelenium.getHTML(webSiteReqVO.getWeburl());
//        WebSiteDataDto webSiteDataDto = new WebSiteDataDto();
//        webSiteDataDto.setParentid(webSiteTagDto.getId());
        //webSiteDataDto.setMedium(FileMediumEnum.WINDOWS);
        //webSiteDataDto.setFileTypeEnum(FileTypeEnum.TXT);
        //webSiteDataDto.setHtml(webSiteDataVO.getPageSource());
        //webSiteDataDto.setPath(appConfiguration.getFilepath() + CrawDataParse.urlToPath(url));

        //webSiteDataDto.setFilename(CrawDataParse.urlToFileName(url));

        // 组装文件路径
        WebSiteDataDto webSiteDataDto = iWebSiteDataService.insert(webSiteReqVO);

        // 组装小说数据

    }

}
