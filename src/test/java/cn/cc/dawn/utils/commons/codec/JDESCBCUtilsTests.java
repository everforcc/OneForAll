/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-22 14:44
 * Copyright
 */

package cn.cc.dawn.utils.commons.codec;

import org.junit.Test;

public class JDESCBCUtilsTests {

    @Test
    public void createKeysTests() {
        String data = "你们都是傻逼啊";
        String key = "ThmC40Hw5dvTuXiirGjEP9jm";
        String encData = JDESCBCUtils.encrypt(data, key);
        // CBC
        // 2db994d0353c040991d599618caa2fa2bcb11bd749c7b1fa
        System.out.println("--- encData ---");
        System.out.println(encData);
        String decData = JDESCBCUtils.decrypt(encData, key);
        System.out.println("--- decData ---");
        System.out.println(decData);
    }

}
