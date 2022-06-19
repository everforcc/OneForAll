/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-16 15:12
 */

package cn.cc.dawn.local.craw.business.bilibili.constant;

public class BilAlbumRegex {

    /**
     * 正则表达式中 \\.代表.
     * "" 中 \\ 代表 \
     *
     * 复制出来的请求头放到文件里面初始化格式
     * http://i1.hdslb.com/bfs/archive/e7f1467860cb3a91a2291b815ca9320e2c89ba62.jpg 刀剑神域直叶
     * http://i2.hdslb.com/bfs/archive/baf3a437486008249cdd0ab4c66ad8612ae7f21e.png 穹妹
     * http://i0.hdslb.com/bfs/archive/74af0a6d77214ae7be8f2c11de0079d9004689c9.jpg 嘴唇
     */
    public static final String regex = "http://i\\d\\.hdslb\\.com/bfs/archive/\\w*\\.[A-Za-z]{3,4}";
    //private static final String regex = "http://i\\d\\.hdslb\\.com/bfs/archive/\\w*\\.(jpg){0,1}(png){0,1}";

}
