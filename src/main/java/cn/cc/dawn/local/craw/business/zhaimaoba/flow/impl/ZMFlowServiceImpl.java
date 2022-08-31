/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-30 18:54
 * Copyright
 */

package cn.cc.dawn.local.craw.business.zhaimaoba.flow.impl;

import cn.cc.dawn.local.craw.business.zhaimaoba.constant.ZhaiMaoConstant;
import cn.cc.dawn.local.craw.business.zhaimaoba.flow.IZMFlowService;
import cn.cc.dawn.local.craw.constant.CrawConstant;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ZMFlowServiceImpl implements IZMFlowService {

    @Autowired
    IHttp httpApacheImpl;
    @Resource
    IFile iFile;

    /**
     * <a href="https://zhaimaoba.com/219.html">...</a>
     *
     * @param url mryt地址
     */
    @Override
    public void flow_MRYT(String url) {

        // 请求html
        HttpParamDto urlHttpParamDto = new HttpParamDto();
        urlHttpParamDto.setUrl(url);
        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", CrawConstant.userAgent);
        urlHttpParamDto.setHeaders(headers);

        String html = httpApacheImpl.getMsg(urlHttpParamDto);
        // 打印html日志太多了不打印吧
        //log.info(html);
        try {
            String title = XSoupUtils.htmlToStr(html, "/html/body/div[2]/div/div[2]/div/article/div[1]/h1/text()");
            List<String> stringList = XSoupUtils.htmlToList(html, "//div[@class='article-content clearfix']//img/@src");
            log.info("有{}条数据待处理", stringList.size());
            for (String imgUrl : stringList) {
                log.info("url: " + imgUrl);
                String fileName = HttpParamUtils.fileNameFromUrl(imgUrl);

                // 请求图片信息
                HttpParamDto imgHttpParamDto = new HttpParamDto();
                imgHttpParamDto.setUrl(imgUrl);

                Map<String, String> imgHeaders = new HashMap<>();
                headers.put("user-agent", CrawConstant.userAgent);
                //headers.put("accept-encoding", "gzip, deflate, br");
                imgHttpParamDto.setHeaders(imgHeaders);
                // 名字不能乱起
                // 没放对正确的param...
                InputStream inputStream = httpApacheImpl.getStream(imgHttpParamDto);
                File file = new File(ZhaiMaoConstant.fileRoot + title + File.separator + fileName);
                FileUtils.copyInputStreamToFile(inputStream, file);

                log.info("图片下载结束:\r\n大小: {}\r\n路径: {} ", FileUtils.byteCountToDisplaySize(file.length()), file.getAbsolutePath());
            }

        } catch (Exception e) {
            log.error("zhaimao获取异常: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}
