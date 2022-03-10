package cn.cc.dawn.business.novel.controller;

import cn.cc.dawn.business.novel.dto.NovelCapterDto;
import cn.cc.dawn.business.novel.dto.NovelDto;
import cn.cc.dawn.business.novel.service.NovelService;
import cn.cc.dawn.common.dto.HttpParam;
import cn.cc.dawn.utils.constant.HttpConstant;
import cn.cc.dawn.utils.constant.LogConstant;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequestMapping("/novelController")
@RestController
@Slf4j
public class NovelController {

    private HttpMethod httpMethod = new HttpApacheImpl();

    @Resource
    NovelService novelService;

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

    @GetMapping("/test")
    public void test(){
        novelService.crawFlow();
    }

    @GetMapping("/show")
    public void show(HttpServletResponse response) throws IOException {
        String content = novelService.show();
        log.info(content);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        response.setDateHeader(HttpConstant.EXPIRES, System.currentTimeMillis() + 1 * 1000);
        // 返回类型 txt
        response.setContentType(HttpConstant.txt_html_UTF_8);
        //
        //response.setHeader(HttpConstant.CACHE_CONTROL, "max-age=" + bytes.length);
        @Cleanup ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
    }

    @GetMapping("/listNovel/{pn}/{size}")
    public ResultE<NovelDto> listNovel(@PathVariable int pn, @PathVariable int size){
        return new ResultE<NovelDto>().execute(e ->{
            e.setSuccess(novelService.listNovel(pn,size));
        });
    }

    @GetMapping("/listCapter/{parentid}/{pn}/{size}")
    public ResultE<NovelCapterDto> listCapter(@PathVariable int parentid,@PathVariable int pn, @PathVariable int size){
        return new ResultE<NovelCapterDto>().execute(e ->{
            e.setSuccess(novelService.listCap(parentid,pn,size));
        });
    }

    @GetMapping("/readContent/{id}/{capterName}")
    public ResultE<String> listCapter(@PathVariable int id, @PathVariable String capterName){
        return new ResultE<String>().execute(e ->{
            e.setSuccess(novelService.readContent(id,capterName));
        });
    }

}
