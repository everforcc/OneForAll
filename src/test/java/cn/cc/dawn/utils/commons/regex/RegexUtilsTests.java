/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-20 15:27
 * Copyright
 */

package cn.cc.dawn.utils.commons.regex;

import org.junit.Test;

public class RegexUtilsTests {

    /**
     * 测试分组
     */
    @Test
    public void groupLow() {
        String pattern_1 = "^(?![^a-z]+$).{8,}$";
        String pattern_2 = "^(?![^a-z]+$).{8,}$";

        String pas_1 = "aaaaaaaa";
        String pas_2 = "AAAAAAAA";
        String pas_3 = "AAAAAAAa";

        System.out.println(RegexUtils.isMatches(pattern_1, pas_1));
        System.out.println(RegexUtils.isMatches(pattern_1, pas_2));
        System.out.println(RegexUtils.isMatches(pattern_1, pas_3));
    }

    /**
     * 正则测试
     */
    @Test
    public void tConcatReg() {
        String pattern_1 = "[a-zA-Z0-9.-]{1,3}";
        String pas_1 = "aaaaaaaa";
        String pas_2 = "aaa";
        String pas_3 = "aa1";
        String pas_4 = "aa.";
        String pas_5 = "aa-";
        String pas_6 = "aa#";
        System.out.println(RegexUtils.isMatches(pattern_1, pas_1));
        System.out.println(RegexUtils.isMatches(pattern_1, pas_2));
        System.out.println(RegexUtils.isMatches(pattern_1, pas_3));
        System.out.println(RegexUtils.isMatches(pattern_1, pas_4));
        System.out.println(RegexUtils.isMatches(pattern_1, pas_5));
        System.out.println(RegexUtils.isMatches(pattern_1, pas_6));
    }

}
