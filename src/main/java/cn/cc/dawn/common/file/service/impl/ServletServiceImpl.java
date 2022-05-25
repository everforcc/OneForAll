package cn.cc.dawn.common.file.service.impl;

import cn.cc.dawn.common.file.service.IServletService;
import cn.cc.dawn.common.file.service.ResponseBytesService;
import cn.cc.dawn.common.file.vo.ServletVO;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import cn.cc.dawn.utils.constant.LogConstant;
import cn.cc.dawn.utils.exception.AppCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class ServletServiceImpl implements IServletService {

    @Autowired
    private ResponseBytesService responseBytesService;

    @Override
    public void down(HttpServletResponse response, String uuname) {
        ServletOutputStream outputStream = null;
        try {
            log.info("returnFile: " + uuname);

            ServletVO servletVO = responseBytesService.getFileBytes(uuname);

            //servletVO.getHeaderMap().forEach((k, v) -> log.info("servletVO: k: " + k + " ,v: " + v));

            AppCode.A00150.assertHasTrue(RObjectsUtils.nonNull(servletVO));
            AppCode.A00161.assertHasTrue(RObjectsUtils.nonNull(servletVO.getBytes()));


            //1. 返回文件类型
            if (!RStringUtils.isEmpty(servletVO.getContentType())) {
                response.setContentType(servletVO.getContentType());
            }

            if (RObjectsUtils.nonNull(servletVO.getHeaderMap())) {
                Map<String, String> headerMap = servletVO.getHeaderMap();
                headerMap.forEach((k, v) -> response.setHeader(k, v));

                //2.
                //response.setHeader(HttpConstant.CACHE_CONTROL, "max-age=" + bytes.length);
                //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
                //response.setHeader("content-disposition", "attachment;filename="+ new String( "webmagic中文.txt".getBytes("gb2312"), "ISO8859-1" ));
                //response.setHeader("content-disposition", "attachment;filename=" + "webmagic中文.txt");

            }

            if (RObjectsUtils.nonNull(servletVO.getDateHeaderMap())) {
                Map<String, Long> dateHeaderMap = servletVO.getDateHeaderMap();
                dateHeaderMap.forEach((k, v) -> response.setDateHeader(k, v));
                //4. 有效期
                //response.setDateHeader(HttpConstant.EXPIRES, System.currentTimeMillis() + 1 * 1000);
            }

            outputStream = response.getOutputStream();
            outputStream.write(servletVO.getBytes());
            outputStream.flush();
        }catch (Exception e){
            throw AppCode.A00150.toUserException(e.toString());
        }finally {
            if(RObjectsUtils.nonNull(outputStream)){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw AppCode.A00150.toUserException(e.toString());
                }
            }
        }
    }

    @Override
    public void read(HttpServletResponse response, String uuname) {
        ServletOutputStream outputStream = null;
        try {
            log.info("returnTXT: " + LogConstant.SPLIT);
            byte[] bytes = responseBytesService.getTXTBytes();

            response.setDateHeader(HttpHeadersConstant.EXPIRES, System.currentTimeMillis() + 1 * 1000);
            // 返回类型 txt/html
            response.setContentType(HttpHeadersConstant.txt_html_UTF_8);
            //
            response.setHeader(HttpHeadersConstant.CACHE_CONTROL, "max-age=" + bytes.length);
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
            throw AppCode.A00150.toUserException(e.toString());
        }finally {
            if(RObjectsUtils.nonNull(outputStream)){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw AppCode.A00150.toUserException(e.toString());
                }
            }
        }
    }


}
