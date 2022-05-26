package cn.cc.dawn.local.craw.business.dujuzi.flow;

import cn.cc.dawn.utils.commons.io.JFileNameUtils;
import cn.cc.dawn.utils.http.JsoupUtils;
import cn.cc.dawn.utils.commons.io.JInputStreamCharUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Yukino
 * 2020/7/3
 */
public class PageProcessor_dujuzi {

    private static Logger logger = LoggerFactory.getLogger(PageProcessor_dujuzi.class);
    /**
     *   这个网站重复很严重，要用分词来处理重复的问题，添加的时候也加限制
     */

    /*private static final String url_dujuzi_Info = "https://www.dujuzi.cn/Home/JujiInfo/1947.html";
    private static final String url_dujuzi_List = "https://www.dujuzi.cn/Home/JujiList/1947.html";*/

    /**
     *  拼接数据的资源地址
     */
    private static String path = System.getProperty("user.dir") + File.separator + "temp" + File.separator ;
    private static String dirMenu = "未知" ;
    //private static final String FILE_PATH = IFilePath.pathRoot() + "/aly/out/";
    private static final String WWW = "https://www.dujuzi.cn/";
    private static final String Index = "Home/Index.html";
    private static final String JujiInfo = "Home/JujiInfo/";
    private static final String JujiList = "Home/JujiList/";
    private static final String MenuList = "Home/SentenceClass/";
    private static final String html = ".html";

    int iterator = 0 ;

    JInputStreamCharUtils JInputStream_utils; // = new InputStream_IO_Write(FILE_PATH + "Code Geass 反叛的鲁路修.txt" );
    /*static Document document_info;
    static Document document_list;
    static {
        document_info = HtmlHelper.getDocumentByURL(url_dujuzi_Info);
        document_list = HtmlHelper.getDocumentByURL(url_dujuzi_List);
    }*/

    /**
     *  获取数据流程
     *  1.首页 https://www.dujuzi.cn/Home/Index.html
     *  2.菜单栏链接
     *  3.进入菜单分页数据
     *  4.获取每个作品链接
     *  5.下面的步奏
     */

    /**
     * 1.作品名称
     * 2.简介
     * 3.封面      ----------------------------------------------------------------------------------------------------
     * 4.句子 {
     *     重点
     * }
     */

    /**
     * 找到书号
     */
    public void menuInfoFlow(){
        //String url_List="https://www.dujuzi.cn/Home/SentenceClass/3.html";

        // file:///C:/Users/Yukino/Desktop/%E8%AF%BB%E5%8F%A5%E5%AD%90/%E8%8F%9C%E5%8D%95%E5%B0%8F%E8%AF%B4%E7%83%AD%E5%8F%A5.html
        // String url_List = "file:///C:/Users/Yukino/Desktop/%E8%AF%BB%E5%8F%A5%E5%AD%90/%E8%8F%9C%E5%8D%95%E5%B0%8F%E8%AF%B4%E7%83%AD%E5%8F%A5.html" ;
        Map<String,String> map_menu = homeMenu();
        for(Map.Entry map:map_menu.entrySet()){
            // 首页菜单的链接，先拿出来不用
            System.out.println(map.getKey()+","+ map.getValue());
            // dirMenu = "心灵毒鸡汤";
            dirMenu = map.getKey().toString();
            String menuNum = map.getValue().toString().split("/")[3].split("\\.")[0];

            //  菜单的页数
            int count = pageCount(WWW + map.getValue().toString());

           System.out.println( "总页数:" + count );
           for(int i=1;i<=count;i++){
               String url_List = WWW + MenuList ;
               url_List += menuNum + "-" + i + html ;
               menuInfo(url_List);
           }
        // pageCount   -1
        }
    }


    /**
     * 根据书号下载
     * @param temp
     */
    public void bookInfoFlow(String temp,String bookName){ // 根据链接下载某个书的句子
        // String temp = "1947" ;
        String url_info = WWW + JujiInfo + temp + html ;
        String url_List = WWW + JujiList + temp + html;

        // 下载前做个校验，避免重复
        File f_exists = new File(path + dirMenu + File.separator + bookName + ".txt");
        if(f_exists.exists()){
            logger.debug(path + dirMenu + File.separator + bookName + ".txt"+"文件已存在");// 会有重复问题?
            return;
        }

        // inputStream_io_write 在这里实例化书名
        JInputStream_utils = new JInputStreamCharUtils(path + dirMenu + File.separator , bookName + ".txt" );
        // 1.简介
        logger.debug(url_info);
        System.out.println("简介:"+getAbstract(url_info));
        //getAbstract(url_info);

        //在这里用正则表达式取出名字
        // 2.总页码
        int count = pageCount(url_List);
        for(int i=1;i<=count;i++){
            extractSentenceList(WWW + JujiList + temp + "-" + i + html);
        }
    }

    /**
     * 返回首页的菜单集合
     * @return
     */
    public Map<String,String> homeMenu(){
        Map<String,String> map_menu = new HashMap<String,String>();
        Document document= JsoupUtils.getDocumentByURL( WWW + Index );
        // 菜单的id
        Element element_menu = document.getElementById("menu-list");
        //System.out.println(element_menu.toString());
        Elements elements_menu = element_menu.getElementsByTag("li");
        //System.out.println(elements_menu.size());

        Element element_link;
        // 去掉首页
        for(int home_menu=1;home_menu<elements_menu.size();home_menu++ ){
            element_link = elements_menu.get(home_menu).getElementsByTag("a").get(0);
            map_menu.put(element_link.attr("title"),element_link.attr("href"));
        }
        if(map_menu.isEmpty()){
            try {
                logger.error("读句子获取菜单失败");
                throw new Exception("读句子获取菜单失败");
            } catch (Exception e) {
                logger.error("读句子获取菜单失败异常:"+e.toString());
                e.printStackTrace();
            }
        }
        return map_menu;
    }

    /**
     *  菜单下的详情页
     *  每个书的链接和书名
     * @param url
     */
    public void menuInfo(String url){ // 菜单下的详情页
        //https://www.dujuzi.cn/Home/SentenceClass/8.html
        Document document= JsoupUtils.getDocumentByURL(url );
        // 书列表的class
        Elements elements_book = document.getElementsByClass("col-md-8 main-content ");

        Elements elements_book_page = elements_book.select(".feed-juziclass-element");

        System.out.println(elements_book_page.size());
        //去掉前两个和最后一个 只取中间的详情
        for(int menu_info=0;menu_info<elements_book_page.size();menu_info++){
            //System.out.println("menu_info:"+elements_book_page.get(menu_info).getElementsByTag("h2").get(0).attr("href"));
            Element element = elements_book_page.get(menu_info).getElementsByTag("h2").get(0).getElementsByTag("a").get(0);
            // System.out.println("menu_info:"+ element.attr("href") );
            // 获取了每个作品的后缀 /Home/JujiList/2110.html
            String bookNum = element.attr("href").split("/")[3].split("\\.")[0];
            String bookName = JFileNameUtils.checkFileName(element.text());
            // System.out.println(bookNum);
            bookInfoFlow(bookNum,bookName);
        }
    }

    public void t(){
        String string="/Home/JujiList/2110.html";
        String[] strArray = string.split("/") ;
        System.out.println(strArray[3]);
        System.out.println(strArray[3].split("\\.")[0]);
        /*String temp = string.split("/")[3];
        System.out.println(temp);*/
        System.out.println(string.substring(0,string.lastIndexOf("/")));
    }

    /**
     * 两个问题，
     * 1.找到作品
     * 2.分页
     */

    /**
     * 2.简介
     */
    public String getAbstract(String url_info ){
        Document document= JsoupUtils.getDocumentByURL(url_info);
        Elements elements = document.getElementsByClass("juji-font");
        String content = elements.get(0).text();
        logger.debug(content);

        JInputStream_utils.IO_PrintWriter_Append(content);
        return content;
    }

    public String bookName(String name){

        //String c="《wo》 》";
        Pattern pattern = Pattern.compile("《{1}(.*?)》{1}");
        Matcher matcher = pattern.matcher(name);
        //是否匹配到了
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }else {

        }
        return UUID.randomUUID().toString();
    }

    // 小功能直接在这里测试spring带的测试起来很慢

    //分页

    public int pageCount(String url){
        Document document = JsoupUtils.getDocumentByURL(url);
        Element element_page = document.getElementById("PageInfo");
        String pageindex = element_page.attr("pageindex");
        String pagecount = element_page.attr("pagecount");
        return Integer.valueOf(pagecount);
    }

    /**
     * 某页的句子数据
     */
    public void extractSentenceList(String reloadUrl){
        // 这个位置要重新拼接地址来操作
        Document document= JsoupUtils.getDocumentByURL(reloadUrl);
        // panel-body >> feed-activity-list
        Elements elements = document.getElementsByClass("feed-activity-list");
        //elements.forEach(System.out::println);
        //System.out.println(elements.text());
        Elements elements_list = elements.get(0).getElementsByClass("feed-element well-minren-info");
        //System.out.println(elements_list.size());
        for(Element element:elements_list){
            Elements elements_a = element.getElementsByClass("juzi");
            //System.out.println("elements_a:"+elements_a.size() + "," + elements_a.text());
            System.out.println(elements_a.text());
            JInputStream_utils.IO_PrintWriter_Append(elements_a.text());
            iterator++;
        }
    }

}
