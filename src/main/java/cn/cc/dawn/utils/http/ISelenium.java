package cn.cc.dawn.utils.http;

import cn.cc.dawn.utils.http.vo.WebSiteDataVO;
import cn.cc.dawn.utils.http.selenium.SeleniumPool;

public interface ISelenium {

    /**
     * 并发下使用，需要先传入连接池
     * @param url
     * @param seleniumPool
     * @return
     */
    WebSiteDataVO getHTML(String url, SeleniumPool seleniumPool);

    /**
     * 单线程下使用，有默认的连接池
     * @param url
     * @return
     */
    WebSiteDataVO getHTML(String url);

}
