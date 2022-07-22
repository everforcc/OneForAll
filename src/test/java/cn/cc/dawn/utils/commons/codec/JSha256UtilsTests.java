/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-22 15:22
 * Copyright
 */

package cn.cc.dawn.utils.commons.codec;

import org.junit.Test;

public class JSha256UtilsTests {

    @Test
    public void createKeysTests() {
        String str = "str-str-str-str-str-str";
        // Hex.encodeHexString
        // 3a284a73eec74557fda69384e43ff11172012ae575ffdb889aac727a9e5d16b0
        // byte2Hex
        // 3a284a73eec74557fda69384e43ff11172012ae575ffdb889aac727a9e5d16b0
        String strSha256 = JSha256Utils.getSHA256String(str);
        System.out.println(strSha256);
    }

}
