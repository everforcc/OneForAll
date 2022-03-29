package cn.cc.dawn.demo.craw.webcommon.flow;

import cn.cc.dawn.demo.craw.webcommon.dao.WebSiteDataMapper;
import cn.cc.dawn.demo.craw.webcommon.dao.WebSiteMapper;
import cn.cc.dawn.demo.craw.webcommon.dao.WebSiteTagMapper;
import cn.cc.dawn.demo.craw.webcommon.dto.HttpParam;
import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteDataDto;
import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteDto;
import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteTagDto;
import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.constant.XSoupCommonConstant;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.HttpParamUtils;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
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

    HttpMethod httpMethod = new HttpApacheImpl();

    /**
     * 1. 拿到链接
     * 2. 校验主表是否存在， 如果不存在，添加
     * 3. 存入子表链接和标题
     * 4. 存入数据表数据
     * 5. 处理业务逻辑
     * @param httpParam
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean commonflow(HttpParam httpParam){
        log.info("web公共流程: " + httpParam);
        // 非空校验
        AppCode.A00301.assertHasTrue(ObjectUtils.nonNull(httpParam));
        String weburl = httpParam.getUrl();
        AppCode.A00302.assertHasTrue(!StringUtils.isEmpty(weburl));
        // 校验链接是否有效
        AppCode.A00100.assertHasTrue(HttpParamUtils.checkUrlEffect(weburl));
        String rooturl = HttpParamUtils.getRootUrl(weburl);
        String flag = webSiteDtoMapper.existByUrl(rooturl);
        WebSiteDto webSiteDto;
        if(StringUtils.isEmpty(flag)){
            // 2. 如果主表没有插入主表
            webSiteDto = new WebSiteDto();
            webSiteDto.setWebroot(rooturl);
            AppCode.A00201.assertHasUpdate(webSiteDtoMapper.insert(webSiteDto));
        }else {
            webSiteDto = webSiteDtoMapper.selectByUrl(rooturl);
        }
        log.info("主表信息: " + webSiteDto.toString());

        // 3. 处理子表标题
        String html = httpMethod.getMsg(httpParam);
        String title = XSoupUtils.htmlToStr(html, XSoupCommonConstant.REG_TITLE);

        WebSiteTagDto webSiteTagDto = new WebSiteTagDto();
        webSiteTagDto.setWeburl(weburl);

        flag = webSiteTagMapper.existByUrl(weburl);

        if(StringUtils.isEmpty(flag)){
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
        flag = webSiteDataMapper.existBYParentid(parentid);
        if(StringUtils.isEmpty(flag)){
            webSiteDataDto.setParentid(parentid);
            webSiteDataDto.setType(1);
            webSiteDataDto.setHtml(html);
            webSiteDataMapper.insert(webSiteDataDto);
        }else {
            webSiteDataDto = webSiteDataMapper.selectBYParentid(parentid);
        }

        log.info("数据表表信息: " + webSiteDataDto);

        return true;
    }

}
