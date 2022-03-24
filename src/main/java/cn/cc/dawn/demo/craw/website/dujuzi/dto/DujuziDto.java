package cn.cc.dawn.demo.craw.website.dujuzi.dto;

import java.util.List;

/**
 * Yukino
 * 2020/7/3
 */
public class DujuziDto {
    /**
     *  网站对象的实体类
     */

    /**
     * 序列号,为了序列化
     */
    private static final long serialVersionUID = 1L;

    // 作品名
    private String dujuziTitle;

    private String dujuziInfo;

    // 点赞评论热度随后再细化
    private List<String> dujuziSentenceList;

    private int pagecount;

    private String url;

}
