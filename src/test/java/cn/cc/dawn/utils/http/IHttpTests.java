package cn.cc.dawn.utils.http;

import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.jsoup.XSoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@SpringBootTest
@Slf4j
public class IHttpTests {

    IHttp IHttp = new HttpApacheImpl();

    @Test
    public void readSimpUrl(){
        HttpParamDto httpParamDto = new HttpParamDto();
        //httpParam.setUrl("https://gitee.com/MyYukino/media/raw/master/README.md");

        httpParamDto.setUrl("http://www.w2ks.org/detail/202/14786.html");
        try {
            String html = IHttp.getMsg(httpParamDto);
            //log.info(html);

            Document document = XSoupUtils.htmlToDocument(html);
            String result =  XSoupUtils.compileStr("//div[@id=contents]",document);
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void decode(){
        try {
            String ASCII = "UTF-8";
            String fileName = "水晶蝶 - 情动 - 副本.mp3";
            String encode = URLEncoder.encode(fileName, ASCII);
            String decode = URLDecoder.decode(encode,ASCII);
            System.out.println(encode);
            System.out.println(decode);

            System.out.println(URLEncoder.encode("水晶蝶 - 情动 - 副本.mp3", "iso-8859-1"));
            System.out.println(new String("水晶蝶 - 情动 - 副本.mp3".getBytes("UTF-8"), "iso-8859-1"));
            System.out.println(new String("水晶蝶 - 情动 - 副本.mp3".getBytes("UTF-8"), "ASCII"));
            System.out.println(new String("水晶蝶 - 情动 - 副本.mp3".getBytes("UTF-8"),"UTF-8"));
            System.out.println(new String("%3F%3F%3F+-+%3F%3F+-+%3F%3F.mp3".getBytes("ASCII"),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
