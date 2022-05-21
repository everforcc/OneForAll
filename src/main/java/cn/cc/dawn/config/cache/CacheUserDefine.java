/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 17:26
 * Copyright
 */

package cn.cc.dawn.config.cache;

/**
 * 系统使用过程中，手动放入缓存和取出
 */
public enum CacheUserDefine {

    BD_DISK_ORDERUSESR_AUTH("百度网盘引导用户授权"),
    BD_DISK_ACCESS_TOKEN("百度网盘Access_Token"),
    ;

    public String cacheKey;

    private static final String cacheKeyFormat = "%s::%s";

    CacheUserDefine(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String formatKey(){
        return String.format(cacheKeyFormat,this.name(),this.name());
    }

    public String formatKey(String... keys){
        return String.format(cacheKeyFormat,this.name(),String.join(":", keys));
    }

}
