/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-22 10:38
 * Copyright
 */

package cn.cc.dawn.demo.signkey.util;

import cn.cc.dawn.utils.commons.codec.JRSAUtils;
import cn.cc.dawn.utils.commons.codec.JSha256Utils;

/**
 * form平台的签名格式
 */
public class FormShaUtil {

    private static final String json = "";
    private static final String nonce = "";

    /**
     * 1. 签名
     */
    public static void getSinKey() {
        // 1. json 拼接 nonce转小写
        String jsonWithNonce = (json + nonce).toLowerCase();

        // 2. 对数据做 sha256运算
        String jsonWithNonceSha256 = sha256(jsonWithNonce);

        // 3. 拼接时间戳
        long timestamp = System.currentTimeMillis();
        String jsonWithNonceSha256Timestamp = jsonWithNonceSha256 + timestamp;

        //4. RSA加密
        String jsonWithNonceSha256TimestampRSA = rsa(jsonWithNonceSha256Timestamp);

    }

    /**
     * 2. 报文加密
     */
    public static void msgEnc() {

    }

    /**
     * 将 str 加密 sha256
     *
     * @param jsonWithNonce str
     * @return 加密后数据
     */
    private static String sha256(String jsonWithNonce) {
        return JSha256Utils.getSHA256String(jsonWithNonce);
    }

    /**
     * 将 str 加密 rsa
     *
     * @param jsonWithNonceSha256Timestamp str
     * @return 返回加密后的数据
     */
    private static String rsa(String jsonWithNonceSha256Timestamp) {
        return JRSAUtils.encryptByPublicKey(jsonWithNonceSha256Timestamp, JRSAUtils.PUBLICKEY);
    }

    /**
     * 将 str 加密 3des
     *
     * @param str str
     * @return 返回加密后的数据
     */
    private static String des3(String str) {
        String encModel = "ECB/PKCS5Padding";
        return "3des";
    }

}
