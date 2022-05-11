package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.local.craw.web.data.dto.HttpParamDto;
import cn.cc.dawn.utils.http.IHttp;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.InputStream;

@Slf4j
public class IHttpSeleniumImpl implements IHttp {
    @Override
    public InputStream getStream(HttpParamDto httpParamDto) {

        return null;
    }

    @Override
    public String getMsg(HttpParamDto httpParamDto) {
        WebDriver driver = getDriver();
        driver.get(httpParamDto.getUrl());
        String html = driver.getPageSource();
        System.out.println("html");
        System.out.println(html);
        log.info(html);
        driver.quit();
        return "success";
    }

    /**
     * 默认使用这个无头 PhantomJSDriver
     * @return
     */
    private static WebDriver getDriver(){
        if (false) {
            ChromeOptions chromeOptions = new ChromeOptions();
            Proxy proxy = new Proxy();

            proxy.setHttpProxy("120.0.0.1:41091");

            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",41091));

            chromeOptions.setProxy(proxy);
            return new ChromeDriver(chromeOptions);
        }else {
            System.setProperty("phantomjs.binary.path","D:/java/environment/driver/phantomjs.exe");
            return new PhantomJSDriver();
            //return null;
        }
    }

    @Override
    public boolean getFile(HttpParamDto httpParamDto) {
        return false;
    }


}
