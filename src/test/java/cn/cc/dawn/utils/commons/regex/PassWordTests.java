/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-20 15:27
 * Copyright
 */

package cn.cc.dawn.utils.commons.regex;

import org.junit.Test;

public class PassWordTests {

    @Test
    public void groupLow(){
        String pattern_1 = "^(?![^a-z]+$).{8,}$";
        String pattern_2 = "^(?![^a-z]+$).{8,}$";

        String pas_1 = "aaaaaaaa";
        String pas_2 = "AAAAAAAA";
        String pas_3 = "AAAAAAAa";

        System.out.println(RegexUtils.matcheStr(pattern_1, pas_1));
        System.out.println(RegexUtils.matcheStr(pattern_1, pas_2));
        System.out.println(RegexUtils.matcheStr(pattern_1, pas_3));
    }

    @Test
    public void tPasReg(){
        //String pattern = "^(?![^a-zA-Z]+$)(?!\D+$)(?![a-zA-Z0-9]+$).{8,}$";
        //
        /**
         * 1. 不能全是 非小写  至少一个小写
         * 2. 不能全是 非大写  至少一个大写
         * 3. 不能全是 非数字  至少一个数字
         * 4. 不能全是 大小写数字  至少一个字符
         *
         * 数字，大写，小写，特殊字符
         */
        String pattern = "^(?![^a-z]+$)(?![^A-Z]+$)(?!\\D+$)(?![a-zA-Z0-9]+$).{8,}$";

        String pas_1 = "aaaaaaaa";
        String pas_2 = "AAAAAAAA";
        String pas_3 = "12345678";
        String pas_4 = "########";

        // 大写+小
        // 大写+数字
        // 大写+特殊字符
        // 小写+数字
        // 小写+特殊字符
        // 数字+特殊字符
        String pas_5 = "AAAAaaaa";
        String pas_6 = "AAAA1111";
        String pas_7 = "AAAA$$$$";
        String pas_8 = "aaaa1111";
        String pas_9 = "aaaa####";
        String pa_10 = "1111####";

        // 没有大写
        // 没有小写
        // 没有数字
        // 没有特殊字符
        String pa_11 = "aaaa11##";
        String pa_12 = "AAAA11##";
        String pa_13 = "AAAAaa##";
        String pa_14 = "AAAAaa11";

        // 全都有
        String pa_15 = "AAaa11##";

        System.out.println(RegexUtils.matcheStr(pattern, pas_1));
        System.out.println(RegexUtils.matcheStr(pattern, pas_2));
        System.out.println(RegexUtils.matcheStr(pattern, pas_3));
        System.out.println(RegexUtils.matcheStr(pattern, pas_4));
        System.out.println(RegexUtils.matcheStr(pattern, pas_5));
        System.out.println(RegexUtils.matcheStr(pattern, pas_6));
        System.out.println(RegexUtils.matcheStr(pattern, pas_7));
        System.out.println(RegexUtils.matcheStr(pattern, pas_8));
        System.out.println(RegexUtils.matcheStr(pattern, pas_9));
        System.out.println(RegexUtils.matcheStr(pattern, pa_10));
        System.out.println(RegexUtils.matcheStr(pattern, pa_11));
        System.out.println(RegexUtils.matcheStr(pattern, pa_12));
        System.out.println(RegexUtils.matcheStr(pattern, pa_13));
        System.out.println(RegexUtils.matcheStr(pattern, pa_14));
        System.out.println(RegexUtils.matcheStr(pattern, pa_15));
    }

}
