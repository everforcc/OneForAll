/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-30 18:59
 * Copyright
 */

package cn.cc.dawn.local.craw.business.zhaimao;

import cn.cc.dawn.utils.commons.lang.RFastDateFormat;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.constant.DateFormatConstant;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ZMFlowServiceImplTests {

    @Test
    public void flow() {
        IHttp httpApacheImpl = new HttpApacheImpl();
        String url = "https://zhaimaoba.com/467.html";
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setUrl(url);
        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        httpParamDto.setHeaders(headers);
        String msg = httpApacheImpl.getMsg(httpParamDto);
        System.out.println(msg);
        try {
            String title = XSoupUtils.htmlToStr(msg, "/html/body/div[2]/div/div[2]/div/article/div[1]/h1/text()");
            List<String> stringList = XSoupUtils.htmlToList(msg, "//div[@class='article-content clearfix']//img/@src");
            System.out.println(stringList.size());
            for (String imgUrl : stringList) {
                System.out.println("url: " + imgUrl);
                String fileName = HttpParamUtils.fileNameFromUrl(imgUrl);
                HttpParamDto imgHttpParamDto = new HttpParamDto();
                imgHttpParamDto.setUrl(imgUrl);
                // accept-encoding: gzip, deflate, br
                headers.put("accept-encoding", "gzip, deflate, br");
                imgHttpParamDto.setHeaders(headers);
                //InputStream inputStream = httpApacheImpl.getStream(httpParamDto);
                imgHttpParamDto.setTargetFileName(fileName);
                imgHttpParamDto.setTargetFilePath("E:\\filesystem\\project\\OneForAll\\zhaimaoba.com\\" + title + File.separator);
                httpApacheImpl.getFile(imgHttpParamDto);
                //FileUtils.copyInputStreamToFile(inputStream, new File("E:\\filesystem\\project\\OneForAll\\zhaimaoba.com\\" + title + File.separator + fileName));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String title = "test";
        String url = "https://chenfengcdn.com/coten/2022/07/02/x6hvbu.jpg";
        try {
            IHttp httpApacheImpl = new HttpApacheImpl();
            HttpParamDto imgHttpParamDto = new HttpParamDto();
            imgHttpParamDto.setUrl(url);
            // accept-encoding: gzip, deflate, br
            Map<String, String> headers = new HashMap<>();
            headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
            headers.put("accept-encoding", "gzip, deflate, br");
            //FileUtils.copyURLToFile(new URL(url), new File("E:\\filesystem\\project\\OneForAll\\zhaimaoba.com\\" + title + "-" + "x6dehb.jpg"));
            imgHttpParamDto.setHeaders(headers);
            InputStream inputStream = httpApacheImpl.getStream(imgHttpParamDto);
            Date startDate = new Date();
            FileUtils.copyInputStreamToFile(inputStream, new File("E:\\filesystem\\project\\OneForAll\\zhaimaoba.com\\" + title + "-" + "x6hvbu.jpg"));
            Date endDate = new Date();
            System.out.println(
                    RFastDateFormat.format(startDate, DateFormatConstant.yyyy_MM_dd_HH_mm_ss) + "\r\n" +
                            RFastDateFormat.format(endDate, DateFormatConstant.yyyy_MM_dd_HH_mm_ss));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        flow();
    }

}
