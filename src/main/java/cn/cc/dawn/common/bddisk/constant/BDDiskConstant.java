/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 15:34
 * Copyright
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

}
