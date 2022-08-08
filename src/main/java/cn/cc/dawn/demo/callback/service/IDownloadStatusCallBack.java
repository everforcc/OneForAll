package cn.cc.dawn.demo.callback.service;

/**
 * 模拟接口
 */
public interface IDownloadStatusCallBack {

    void download(String url);

    void startDownload();

    void stopDownload();

    void showDownloadURL(String url);

    void showDownloadProgress(int progress);
}

