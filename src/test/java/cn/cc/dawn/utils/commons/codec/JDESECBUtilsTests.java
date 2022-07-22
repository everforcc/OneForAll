/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-22 14:57
 * Copyright
 */

package cn.cc.dawn.utils.commons.codec;

import org.junit.Test;

public class JDESECBUtilsTests {

    @Test
    public void createKeysTests() {
        String data = "你们都是傻逼啊";
        String key = "ThmC40Hw5dvTuXiirGjEP9jm";
        String encData = JDESECBUtils.encrypt(data, key);
        System.out.println("--- encData ---");
        // ECB
        // 76be68c3e664066c939fff829ea58c66e23c9a6dc1f24aae
        System.out.println(encData);
        String decData = JDESECBUtils.decrypt(encData, key);
        System.out.println("--- decData ---");
        System.out.println(decData);
    }

}
