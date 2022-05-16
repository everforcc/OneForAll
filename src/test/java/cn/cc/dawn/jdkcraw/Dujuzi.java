package cn.cc.dawn.jdkcraw;

import cn.cc.dawn.utils.http.JsoupUtils;
import cn.cc.dawn.utils.data.io.InputStream_IO_Write;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Yukino
 * 2020/7/3
 */
@SpringBootTest
public class Dujuzi {

    @Test
    public void compile(){
        System.out.println("1");
    }

    private static final String url_dujuzi_Info = "https://www.dujuzi.cn/Home/JujiInfo/1947.html";

    private static final String url_dujuzi_List = "https://www.dujuzi.cn/Home/JujiList/1947.html";

    /**
     * 用来测试需要框架的功能
     */
    @Test
    void dujuzi_Info(){
        System.out.println(JsoupUtils.getDocumentByURL(url_dujuzi_List).body());
        /*Document document=HtmlHelper.getDocumentByURL(url_dujuzi_Info);
        Elements elements = document.getElementsByClass("juji-font");
        System.out.println(elements.get(0).text());*/
    }

    @Test
    void dujuzi_List(){
        System.out.println(JsoupUtils.getDocumentByURL(url_dujuzi_List));
    }


    @Test
    void pageNum(){
        //<input type="hidden" id="PageInfo" pageindex="1" pagecount="25" value="1947">
        //System.out.println(HtmlHelper.getDocumentByURL(url_dujuzi_List).body());
        Document document = JsoupUtils.getDocumentByURL(url_dujuzi_List);
        Element element_page = document.getElementById("PageInfo");
        String pageindex = element_page.attr("pageindex");
        String pagecount = element_page.attr("pagecount");
        System.out.println(pageindex);
        System.out.println(pagecount);
    }

    @Test
   void testIO(){
        String path = System.getProperty("user.dir") + File.separator + "temp" + File.separator ;
        if(!new File(path).exists()){
            new File(path).mkdirs();
        }
        InputStream_IO_Write inputStream_io_write = new InputStream_IO_Write(path , "Code Geass 反叛的鲁路修.txt" );
        inputStream_io_write.IO_PrintWriter_Append("321123123");
        inputStream_io_write.IO_PrintWriter_Append(new String[]{"123","321123123"});

        System.out.println(System.getProperty("user.dir"));
   }

   void a1(){
       HashMap<String,String> hashMap = new HashMap<String, String>();
       hashMap.put("","");
       for(Map.Entry entry:hashMap.entrySet()){

       }

   }

}
