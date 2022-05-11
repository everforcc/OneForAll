package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.utils.http.ISelenium;
import cn.cc.dawn.utils.http.selenium.SeleniumPool;
import cn.cc.dawn.utils.http.selenium.WebDriverPDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ISeleniumChrome implements ISelenium {

    @Override
    public String getHTML(String url,SeleniumPool seleniumPool) {
        WebDriverPDto webDriverPDto = seleniumPool.getDriverDto();
        String html = webDriverPDto.getHtml(url);
        seleniumPool.close(webDriverPDto);
        return html;
    }

}
