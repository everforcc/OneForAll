/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 16:00
 * Copyright
 */

package cn.cc.dawn.demo.callback.service.impl;

import cn.cc.dawn.demo.callback.service.IDownloadStatusCallBack;
import cn.cc.dawn.demo.callback.service.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DownloadStatusCallBack implements IDownloadStatusCallBack {

    private Server mServer = null;

    public void download(String url) {
        log.info("初始化server");
        mServer = new Server(DownloadStatusCallBack.this, url);
        mServer.Run();
    }

    @Override
    public void startDownload() {
        log.info("startDownload");
    }

    @Override
    public void stopDownload() {
        log.info("stopDownload");
    }

    @Override
    public void showDownloadURL(String url) {
        log.info("Download URL: " + url);
    }

    @Override
    public void showDownloadProgress(int progress) {
        log.info("DownloadProgress: " + progress + "%");
    }

}

