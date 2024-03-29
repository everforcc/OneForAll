package cn.cc.dawn.local.craw.business.data.flow;

import cn.cc.dawn.local.craw.business.data.dao.WebSiteDataMapper;
import cn.cc.dawn.local.craw.business.data.dao.WebSiteMapper;
import cn.cc.dawn.local.craw.business.data.dao.WebSiteTagMapper;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDataDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteTagDto;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.local.craw.constant.XSoupCommonConstant;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class WebSiteCommonService {

    @Resource
    private WebSiteMapper webSiteDtoMapper;

    @Resource
    private WebSiteTagMapper webSiteTagMapper;

    @Resource
    private WebSiteDataMapper webSiteDataMapper;

    @Resource
    IHttp httpApacheImpl;

    /**
     * 1. 拿到链接
     * 2. 校验主表是否存在， 如果不存在，添加
     * 3. 存入子表链接和标题
     * 4. 存入数据表数据
     * 5. 处理业务逻辑
     * @param httpParamDto
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean commonflow(HttpParamDto httpParamDto){

        /**
         * 应该提前维护到数据库,存入redis，然后每次使用，后台取出
         */

        log.info("web公共流程: " + httpParamDto);
        // 非空校验
        AppCode.A00301.assertHasTrue(RObjectsUtils.nonNull(httpParamDto));
        String weburl = httpParamDto.getUrl();
        AppCode.A00302.assertHasTrue(!RStringUtils.isEmpty(weburl));
        // 校验链接是否有效
        AppCode.A00100.assertHasTrue(HttpParamUtils.checkUrlEffect(weburl));
        String rooturl = HttpParamUtils.getRootUrl(weburl);
        String flag = webSiteDtoMapper.existByUrl(rooturl);
        WebSiteDto webSiteDto;
        if(RStringUtils.isEmpty(flag)){
            // 2. 如果主表没有插入主表
            webSiteDto = new WebSiteDto();
            webSiteDto.setWebroot(rooturl);
            AppCode.A00201.assertHasUpdate(webSiteDtoMapper.insert(webSiteDto));
        }else {
            webSiteDto = webSiteDtoMapper.selectByUrl(rooturl);
        }
        log.info("主表信息: " + webSiteDto.toString());

        // 3. 处理子表标题
        String html = httpApacheImpl.getMsg(httpParamDto);
        String title = XSoupUtils.htmlToStr(html, XSoupCommonConstant.REG_TITLE);

        WebSiteTagDto webSiteTagDto = new WebSiteTagDto();
        webSiteTagDto.setWeburl(weburl);

        flag = webSiteTagMapper.existByUrl(weburl);

        if(RStringUtils.isEmpty(flag)){
            webSiteTagDto.setWebrootid(webSiteDto.getId());
            webSiteTagDto.setWebname(title);
            webSiteTagMapper.insert(webSiteTagDto);
        }else {
            webSiteTagDto = webSiteTagMapper.selectByUrl(weburl);
        }
        log.info("子表信息: " + webSiteTagDto);

        // 4. 插入数据表
        WebSiteDataDto webSiteDataDto = new WebSiteDataDto();
        int parentid = webSiteTagDto.getId();

        WebSiteDataDto condation = new WebSiteDataDto();
        condation.setParentid(parentid);

        flag = webSiteDataMapper.existBYParentid(condation);
        if(RStringUtils.isEmpty(flag)){
            webSiteDataDto.setParentid(parentid);
            webSiteDataDto.setMedium(FileMediumEnum.WINDOWS);
            webSiteDataDto.setHtml(html);
            webSiteDataMapper.insert(webSiteDataDto);
        }else {

//            WebSiteDataDto condation = new WebSiteDataDto();
//            condation.setParentid(parentid);
//            condation.setStatus(StatusEnum.EFFECT);
//            condation.setEffect(StatusEnum.EFFECT);

            webSiteDataDto = webSiteDataMapper. selectBYParentid(condation);
        }

        log.info("数据表表信息: " + webSiteDataDto);

        return true;
    }

}
