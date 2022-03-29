package cn.cc.dawn.demo.servlet.controller;

import cn.cc.dawn.demo.servlet.service.ResponseBytesService;
import cn.cc.dawn.demo.servlet.vo.ServletVO;
import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.constant.HttpConstant;
import cn.cc.dawn.utils.constant.LogConstant;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RequestMapping(value = "/open/file")
@RestController
public class ResponseBytesController {

    @Autowired
    private ResponseBytesService responseBytesService;

    @GetMapping("/returnFile15")
    @ResponseBody
    public void returnFile(HttpServletResponse response) throws Exception {
        log.info("returnFile: " + LogConstant.SPLIT);
        ServletVO servletVO = responseBytesService.getFileBytes();

        servletVO.getHeaderMap().forEach((k,v) -> log.info("servletVO: k: " + k + " ,v: " + v));

        if(ObjectUtils.isNull(servletVO)){
            return;
        }

        //1. 返回文件类型
        if(!StringUtils.isEmpty(servletVO.getContentType())){
            response.setContentType(servletVO.getContentType());
        }

        if(ObjectUtils.nonNull(servletVO.getHeaderMap())){
            Map<String,String> headerMap = servletVO.getHeaderMap();
            headerMap.forEach((k,v) -> response.setHeader(k,v));

            //2.
            //response.setHeader(HttpConstant.CACHE_CONTROL, "max-age=" + bytes.length);
            //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
            //response.setHeader("content-disposition", "attachment;filename="+ new String( "webmagic中文.txt".getBytes("gb2312"), "ISO8859-1" ));
            //response.setHeader("content-disposition", "attachment;filename=" + "webmagic中文.txt");

        }

        if(ObjectUtils.nonNull(servletVO.getDateHeaderMap())){
            Map<String,Long> dateHeaderMap = servletVO.getDateHeaderMap();
            dateHeaderMap.forEach((k,v) -> response.setDateHeader(k,v));
            //4. 有效期
            //response.setDateHeader(HttpConstant.EXPIRES, System.currentTimeMillis() + 1 * 1000);
        }

        if(ObjectUtils.nonNull(servletVO.getBytes())){
            @Cleanup ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(servletVO.getBytes());
            outputStream.flush();
        }
    }

    @GetMapping("/returnTXT")
    @ResponseBody
    public void returnTXT(HttpServletResponse response) throws Exception {
        log.info("returnTXT: " + LogConstant.SPLIT);
        byte[] bytes = responseBytesService.getTXTBytes();

        response.setDateHeader(HttpConstant.EXPIRES, System.currentTimeMillis() + 1 * 1000);
        // 返回类型 txt
        response.setContentType(HttpConstant.txt_plain_UTF_8);
        //
        response.setHeader(HttpConstant.CACHE_CONTROL, "max-age=" + bytes.length);
        @Cleanup ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
    }



}
