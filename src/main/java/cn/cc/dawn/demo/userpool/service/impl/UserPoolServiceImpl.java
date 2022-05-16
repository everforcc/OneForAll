package cn.cc.dawn.demo.userpool.service.impl;


import cn.cc.dawn.demo.userpool.service.UserPoolService;
import cn.cc.dawn.utils.http.ISelenium;
import cn.cc.dawn.utils.http.selenium.SeleniumPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserPoolServiceImpl implements UserPoolService {

    @Resource
    ISelenium iSelenium;

    @Override
    public void tPool() {
        final String url = "http://www.3diyibanzhu.xyz/20/20412/701424_";
        SeleniumPool seleniumPool  = SeleniumPool.getInstantce();
        //iSelenium.getHTML(url + 1 + ".html",seleniumPool);
        for(int i=1; i<6;i++){
                final int index = i;
                Thread thread = new Thread(()->{
                    iSelenium.getHTML(url + index + ".html",seleniumPool);
                });
                thread.start();
        }
    }

    @Override
    public void tPool2() {
        final String url = "https://www.baidu.com/s?wd=";
        SeleniumPool seleniumPool  = SeleniumPool.getInstantce();
        //iSelenium.getHTML(url + 1 + ".html",seleniumPool);
        for(int i=1; i<6;i++){
            final int index = i;
            Thread thread = new Thread(()->{
                iSelenium.getHTML(url + index + ".html",seleniumPool);
            });
            thread.start();
        }
    }
}
