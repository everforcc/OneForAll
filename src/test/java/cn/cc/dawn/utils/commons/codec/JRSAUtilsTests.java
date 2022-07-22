/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-22 11:45
 * Copyright
 */

package cn.cc.dawn.utils.commons.codec;

import org.junit.Test;

public class JRSAUtilsTests {

    @Test
    public void createKeysTests() {
        JRSAUtils.KeyStore keyStore = JRSAUtils.createKeys();
        String privateKey = keyStore.getPrivateKey();
        String publicKey = keyStore.getPublicKey();
        System.out.println(privateKey);
        System.out.println(publicKey);
    }

    /**
     * 公钥加密,私钥解密
     */
    @Test
    public void encryptByPublicKeyTests() {
        String str = "你们都是傻逼啊";
        String encStr = JRSAUtils.encryptByPublicKey(str, JRSAUtils.PUBLICKEY);
        System.out.println("-----------encStr");
        System.out.println(encStr);
        String decStr = JRSAUtils.decryptByPrivateKey(encStr, JRSAUtils.PRIVATEKEY);
        System.out.println("-----------decStr");
        System.out.println(decStr);
    }

}
