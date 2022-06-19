/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-22 10:13
 */

package cn.cc.dawn.common.bddisk.constant;

import java.util.HashMap;
import java.util.Map;

public class BDDiskErrorCode {

    public static Map<String,String> errorCodeMap = new HashMap<>();

    public static String success = "0";

    // https://pan.baidu.com/union/doc/okumlx17r
    static {
        // 您成功了。
        errorCodeMap.put("0","请求成功");
        /**
         * 1.检查必选参数是否都已填写；
         * 2.检查参数位置，有的参数是在url里，有的是在body里；
         * 3.检查每个参数的值是否正确。
         */
        errorCodeMap.put("2","参数错误");
        /**
         * 更新access token。
         */
        errorCodeMap.put("111","access token 失效");
        /**
         * 1.access_token 是否有效;
         * 2.授权是否成功；
         * 3.参考接入授权FAQ；
         * 4.阅读文档《使用入门->接入授权》章节
         */
        errorCodeMap.put("-6","身份验证失败");
        /**
         * 建议10分钟之后用户再进行授权重试。
         */
        errorCodeMap.put("6","不允许接入用户数据");
        /**
         * 接口请求过于频繁，注意控制。
         */
        errorCodeMap.put("31034","命中接口频控");
    }

}
