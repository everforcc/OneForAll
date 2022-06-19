/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 15:34
 */

package cn.cc.dawn.common.bddisk.constant;

/**
 * 百度云常用的常量
 */
public class BDDiskConstant {

    public static String redirect_uri = "oob";
    public static String refresh_token = "refresh_token";
    public static String authorization_code = "authorization_code";

    /**
     * 提前多久释放token，避免后续使用过程中token失效
     * 最多持有，这么久
     * 因为缓存消失时间比对方约定的少这么久
     */
    public static int holdTime = 3600 * 24;

    public static String Header_Host = "pan.baidu.com";
}
