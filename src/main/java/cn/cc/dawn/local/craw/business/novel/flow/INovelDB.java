package cn.cc.dawn.local.craw.business.novel.flow;

/**
 * 在这个类里面去调用INovelDeal
 */
public interface INovelDB {



    /**
     * 先去数据库查，如果没有，那就请求并保存
     * 日志要打出是从哪里来的数据
     * 具体怎么处理html看业务逻辑
     * @param url
     * @return
     */
    String getHTMLByUrl(String url);

}
