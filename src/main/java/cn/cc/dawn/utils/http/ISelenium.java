package cn.cc.dawn.utils.http;

import cn.cc.dawn.utils.http.selenium.SeleniumPool;

public interface ISelenium {

    String getHTML(String url, SeleniumPool seleniumPool);

}
