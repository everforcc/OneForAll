package cn.cc.dawn.business.novel.controller;

import cn.cc.dawn.common.dto.HttpParam;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/novelController")
@RestController
@Slf4j
public class NovelController {

    private HttpMethod httpMethod = new HttpApacheImpl();

    @GetMapping("/chapter")
    public String content(){

        String result = "";
        HttpParam httpParam = new HttpParam();
        //httpParam.setUrl("https://gitee.com/MyYukino/media/raw/master/README.md");

        httpParam.setUrl("http://www.w2ks.org/detail/202/14786.html");
        try {
            String html = httpMethod.getMsg(httpParam);
            log.info(html);
            Document document = XSoupUtils.htmlToDocument(html);
            result = XSoupUtils.compileStr("//div[@id=contents]",document);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
