/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-25 17:12
 * Copyright
 */

package jdk;

import org.junit.Before;
import org.junit.Test;

public class TestApiTests {

    String staticStr = "staticStr";

    @Before
    public void testBefor(){
        staticStr = "before";
    }

    @Test
    public void test_1(){
        System.out.println("staticStr: " + staticStr);
    }

}
