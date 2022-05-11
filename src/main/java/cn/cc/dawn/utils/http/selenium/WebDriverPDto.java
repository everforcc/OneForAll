package cn.cc.dawn.utils.http.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class WebDriverPDto {

    private int index;
    private WebDriver webDriver;

    public int getIndex() {
        return index;
    }

    /*public WebDriver getWebDriver() {
        return webDriver;
    }*/

    protected WebDriverPDto() {
    }

    public String getHtml(String url){
        webDriver.get(url);
        String html = webDriver.getPageSource();
        String title = webDriver.getTitle();
        log.info("title {}",title);
        log.info("驱动 {},请求的url: {}",index,url);
        return html;
    }

    protected WebDriverPDto(int index, WebDriver webDriver) {
        this.index = index;
        this.webDriver = webDriver;
        log.info("创建webDriver: 坐标:{} 驱动类型{}",index,webDriver.getClass());
    }



}
