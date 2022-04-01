package cn.cc.dawn.http;

import cn.cc.dawn.open.web.data.dto.HttpParamDto;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class HttpMethodT {

    HttpMethod httpMethod = new HttpApacheImpl();

    @Test
    public void readSimpUrl(){
        HttpParamDto httpParamDto = new HttpParamDto();
        //httpParam.setUrl("https://gitee.com/MyYukino/media/raw/master/README.md");

        httpParamDto.setUrl("http://www.w2ks.org/detail/202/14786.html");
        try {
            String html = httpMethod.getMsg(httpParamDto);
            //log.info(html);

            Document document = XSoupUtils.htmlToDocument(html);
            String result =  XSoupUtils.compileStr("//div[@id=contents]",document);
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
