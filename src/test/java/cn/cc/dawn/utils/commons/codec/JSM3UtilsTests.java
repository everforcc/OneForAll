/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-04 17:32
 * Copyright
 */

package cn.cc.dawn.utils.commons.codec;

import org.junit.Test;

public class JSM3UtilsTests {

    /**
     * 测试sm3 base是否可用
     */
    @Test
    public void sm3Base64Tests() {
        String string = "string";
        String sm3Base64 = JSM3Utils.sm3Base64(string);
        System.out.println(sm3Base64);
    }

}
