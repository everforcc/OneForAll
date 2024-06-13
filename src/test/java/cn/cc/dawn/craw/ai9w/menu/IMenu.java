package cn.cc.dawn.craw.ai9w.menu;

public interface IMenu {

    /**
     * 拿到 html
     *
     * @param url 地址
     * @return html
     */
    String html(String url);

    /**
     * 从html取出totle
     *
     * @param html html
     * @return title
     */
    String title(String html);

    /**
     * 从html取出内容
     *
     * @param html html
     * @return 内容
     */
    String content(String html);

    /**
     * 从html取出下一页
     *
     * @param html html
     * @return 下一页链接
     */
    String nextUrl(String html);

    /**
     * 写入到文件
     *
     * @param i       第几章节
     * @param title   标题
     * @param content 内容
     */
    void write(int i, String title, String content);

}
