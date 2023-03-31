/**
 * @Description
 * @Author everforcc
 * @Date 2023-03-30 15:04
 * Copyright
 */

package jdk.lang;

import org.junit.Test;

public class AutoCloseableTests {

    /**
     * 使用
     * try with resource
     * 语法调用，会释放资源
     */
    @Test
    public void tryWithResource() {
        try (AutoCloseableDto autoCloseableDto = new AutoCloseableDto("sss")) {
            autoCloseableDto.m();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
