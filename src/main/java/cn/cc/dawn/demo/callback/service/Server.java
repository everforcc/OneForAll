/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 16:01
 * Copyright
 */

package cn.cc.dawn.demo.callback.service;

import java.util.Timer;
import java.util.TimerTask;

public class Server {

    private IDownloadStatusCallBack iCallBack;
    private String mDownloadUrl = null;

    private Timer mTimer = null;

    private int mProgress = 0;

    public Server(IDownloadStatusCallBack iCallBack, String url) {
        this.iCallBack = iCallBack;
        this.mDownloadUrl = url;
        mProgress = 0;
    }

    public void Run() {
        iCallBack.showDownloadURL(mDownloadUrl); // 回调下载URL
        iCallBack.startDownload();               // 回调开始下载

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                iCallBack.showDownloadProgress(mProgress); // 回调下载进度
                if (mProgress == 100) {                    // 下载进度至100%
                    iCallBack.stopDownload();              // 回调下载结束
                    mTimer.cancel();
                }
                mProgress += 10;
            }
        }, 0, 1000);
    }
}

