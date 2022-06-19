/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 17:26
 */

package cn.cc.dawn.config.cache;

/**
 * 系统使用过程中，手动放入缓存和取出
 */
public enum CacheUserDefine {

    BD_DISK_ORDERUSESR_AUTH("百度网盘引导用户授权"),
    BD_DISK_ACCESS_TOKEN("百度网盘Access_Token"),
    BD_DISK_USER_MSG("百度网盘用户基本信息"),
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

/*    public String formatKey(int... keys){
        return String.format(cacheKeyFormat,this.name(),String.join(":", keys.toString()));
    }*/

}
