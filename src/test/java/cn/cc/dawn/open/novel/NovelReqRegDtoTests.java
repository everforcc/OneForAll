package cn.cc.dawn.open.novel;

import cn.cc.dawn.open.novel.constant.NovelReqRegDto;
import cn.cc.dawn.open.novel.flow.CommonFlow;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class NovelReqRegDtoTests {

    @Test
    public void tReg(){
        System.out.println(1);
        Map<String,String> headersMap = new HashMap<>();
        Map<String,String> authonMap = new HashMap<>();
        NovelReqRegDto novelReqRegDto = new NovelReqRegDto();

        // 1.
        novelReqRegDto.setHeaders(headersMap);
        // 2.
        novelReqRegDto.setAuthon(authonMap);
        // 3.
        novelReqRegDto.setRegWebName("");
        // 4. 小说 url
        novelReqRegDto.setRegNovelUrl("");
        // 5. 小说 名
        novelReqRegDto.setRegNovelName("");
        // 6. 章节列表 url
        novelReqRegDto.setRegCapterListUrl("");
        // 6.2
        novelReqRegDto.setRegCapterListUrlNext("");
        // 6.3
        novelReqRegDto.setRegCapterListUrlEnd("");
        // 6.4 章节列表 内容
        novelReqRegDto.setRegCapterList("");

        // 7.1 章节内容
        novelReqRegDto.setRegContentUrl("");
        novelReqRegDto.setRegContentUrlNext("");
        novelReqRegDto.setRegContentUrlEnd("");
        // 7.4
        novelReqRegDto.setRegContent("");
    }

    @Test
    public void web_dzwx520(){
        Map<String,String> headersMap = new HashMap<>();
        Map<String,String> authonMap = new HashMap<>();
        NovelReqRegDto novelReqRegDto = new NovelReqRegDto();

        novelReqRegDto.setCharset(CharsetsEnum.GBK);
        // 1.
        novelReqRegDto.setHeaders(headersMap);
        // 2.
        novelReqRegDto.setAuthon(authonMap);
        // 3.
        novelReqRegDto.setRegWebName("");
        // 4. 小说 url 目前直接从已知小说名开始
        novelReqRegDto.setRegNovelUrl("");
        // 5. 小说 名
        novelReqRegDto.setRegNovelName("//div[@class='book_info']//h1/text()");
        // 6. 章节列表 url
        novelReqRegDto.setRegCapterListUrl("");
        // 6.2
        novelReqRegDto.setRegCapterListUrlNext("");
        // 6.3
        novelReqRegDto.setRegCapterListUrlEnd("");
        // 6.4 章节列表 内容
        //novelReqRegDto.setRegCapterNameList("//div[@class='book_list boxm']//ul//a/@href|//div[@class='book_list boxm']//ul//a/@title");
        novelReqRegDto.setRegCapterList("//div[@class='book_list boxm']//ul//a");
        novelReqRegDto.setRegCapterUrl("//a/@href");
        novelReqRegDto.setRegCapterName("//a/@title");

        // 7.1 章节内容
        novelReqRegDto.setRegContentUrl("");
        novelReqRegDto.setRegContentUrlNext("");
        novelReqRegDto.setRegContentUrlEnd("");
        // 7.4
        novelReqRegDto.setRegContent("//div[@id='htmlContent']");

        //CommonFlow commonFlow = new CommonFlow();
        //CommonFlow.novelNameToCapter(novelReqRegDto,"http://www.dzwx520.com/book_8764/");
        //CommonFlow.capterList(novelReqRegDto,"http://www.dzwx520.com/book_8764/");
        CommonFlow.content(novelReqRegDto,"http://www.dzwx520.com/book_11288/4476562.html");
    }

}
