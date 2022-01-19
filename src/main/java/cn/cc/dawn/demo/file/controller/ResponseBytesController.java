package cn.cc.dawn.demo.file.controller;

import cn.cc.dawn.demo.file.service.ResponseBytesService;
import cn.cc.dawn.utils.constant.HttpContentTypeConstant;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(value = "/demo/file")
@RestController
public class ResponseBytesController {

    @Autowired
    private ResponseBytesService responseBytesService;

    @GetMapping("/returnFile")
    @ResponseBody
    public void returnFile(HttpServletResponse response) throws Exception {
        byte[] bytes = responseBytesService.getFileBytes();

        response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + 1 * 1000);

        /**
         * - [ ] 待总结其他格式
         */
        response.setContentType(HttpContentTypeConstant.image_jpeg);

        response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=" + bytes.length);

        @Cleanup ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
    }

}
