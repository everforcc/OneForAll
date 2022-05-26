package cn.cc.dawn.local.craw.business.data.service.impl;

import cn.cc.dawn.config.init.yml.APPConfiguration;
import cn.cc.dawn.local.craw.business.data.dao.WebSiteDataMapper;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDataDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteTagDto;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteDataService;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteTagService;
import cn.cc.dawn.utils.http.vo.WebSiteDataVO;
import cn.cc.dawn.utils.http.vo.WebSiteReqVO;
import cn.cc.dawn.local.craw.util.CrawDataParse;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.enums.impl.FileTypeEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.ISelenium;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
//@Validated
@Transactional
public class WebSiteDataServiceImpl implements IWebSiteDataService {

    /**
     * save web数据数据表
     */
    @Resource
    WebSiteDataMapper webSiteDataMapper;
    @Resource
    IWebSiteTagService iWebSiteTagService;
    @Resource
    ISelenium iSelenium;
    @Resource
    IHttp httpApacheImpl;
    @Resource
    IFile iFile;
    @Resource
    APPConfiguration appConfiguration;

    // @NotNull(message = "[obj] 不能为null")
    @Transactional(rollbackFor = Exception.class)
    public WebSiteDataDto insert(WebSiteReqVO webSiteReqVO){
        WebSiteDataDto webSiteDataDto = new WebSiteDataDto();
        String weburl = webSiteReqVO.getWeburl();
        /**
         * 1. 先插入tag表，然后校验tag是否存在
         */
        WebSiteTagDto webSiteTagDto = iWebSiteTagService.insert(webSiteReqVO);

        // 根据插入后返回的数据查，当前数据是否存在
        WebSiteDataDto condation = new WebSiteDataDto();
        condation.setParentid(webSiteTagDto.getId());

        if("1".equals(webSiteDataMapper.existBYParentid(condation))){
            // 如果存在直接取出
            webSiteDataDto = webSiteDataMapper.selectBYParentid(condation);
        }else {
            webSiteDataDto.setMedium(webSiteReqVO.getMedium());
            // 如果不存在，入库并保存磁盘
            if(FileMediumEnum.WINDOWS == webSiteDataDto.getMedium()){
                String filePath = appConfiguration.getFilepath();


                //AppCode.A01005.assertHasTrue(StringUtils.isNotEmpty(webSiteDataDto.getPath()));
                //AppCode.A01005.assertHasTrue(StringUtils.isNotEmpty(webSiteDataDto.getFilename()));
                //AppCode.A01006.assertHasTrue(StringUtils.isNotEmpty(webSiteDataDto.getHtml()));
                AppCode.A01006.assertHasTrue(RObjectsUtils.nonNull(webSiteReqVO.getFiletype()));
                webSiteDataDto.setFileTypeEnum(webSiteReqVO.getFiletype());
                webSiteDataDto.setFilename(CrawDataParse.urlToFileName(weburl));
                webSiteDataDto.setPath(appConfiguration.getFilepath() + CrawDataParse.urlToPath(weburl));

                // 将数据放入磁盘
                if(FileTypeEnum.TXT.equals(webSiteDataDto.getFileTypeEnum())) {
                    WebSiteDataVO webSiteDataVO = iSelenium.getHTML(webSiteReqVO.getWeburl());
                    webSiteDataDto.setHtml(webSiteDataVO.getPageSource());
                    // 文本类型
                    iFile.write(webSiteDataDto.getPath(),
                            webSiteDataDto.getFilename() + "." + webSiteDataDto.getFileTypeEnum().toString(),
                            webSiteDataDto.getHtml());
                }else {
                    HttpParamDto httpParamDto = new HttpParamDto();
                    httpParamDto.setUrl(weburl);
                    webSiteDataDto.setBytes(httpApacheImpl.getBytes(httpParamDto));
                    // 字节类型
                    iFile.write(webSiteDataDto.getPath(),
                            webSiteDataDto.getFilename(),
                            webSiteDataDto.getBytes());
                }
            }else {
                throw AppCode.A01007.toUserException();
            }
            webSiteDataDto.setParentid(webSiteTagDto.getId());
            /**
             * 暂时不入库
             */
            webSiteDataDto.setHtml(null);
            webSiteDataDto.setBytes(null);
            int dataResult = webSiteDataMapper.insert(webSiteDataDto);
            log.info("dataResult: " + dataResult);
        }
        return webSiteDataDto;
    }

    public WebSiteDataDto select(@NotNull int parentid){
        WebSiteDataDto condation = new WebSiteDataDto();
        condation.setParentid(parentid);
        WebSiteDataDto result = webSiteDataMapper.selectBYParentid(condation);
        if(FileMediumEnum.WINDOWS == result.getMedium()){
            String path = result.getPath();
            /**
             * 将html从磁盘中读出来
             */
            if(FileTypeEnum.TXT.equals(result.getFileTypeEnum())) {
                result.setHtml(iFile.readTXT(path));
            }else {
                result.setBytes(iFile.readBytes(path));
            }
        }else {
            throw AppCode.A01007.toUserException();
        }
        return result;
    }

}
