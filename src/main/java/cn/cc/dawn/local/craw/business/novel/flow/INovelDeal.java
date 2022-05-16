package cn.cc.dawn.local.craw.business.novel.flow;

import java.util.List;

public interface INovelDeal {

    /**
     * 从一个链接获取html
     * @param url
     * @return
     */
    String onePageHtml(String url);

    /**
     * 从html 获取章节链接
     * @param html
     * @return
     */
    List<String> htmlToCapterList(String html);

    /**
     * 从html获取小说内容
     * @param html
     * @return
     */
    String htmlToContent(String html);

    /**
     * 从html 获取小说url页码合集，
     * TODO 不一定有
     * @param html
     * @return
     */
    List<String> htmlToContentPageList(String html);

}
