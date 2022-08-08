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

    /**
     * 测试密码
     */
    @Test
    public void tPasReg() {
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
        // !@#$%^&*_
        //String pattern = "^(?![^a-z]+$)(?![^A-Z]+$)(?!\\D+$)(?![a-zA-Z0-9]+$)(?!\\s+$).{8,}$";
        String pattern = "^(?![^a-z]+$)(?![^A-Z]+$)(?!\\D+$)(?![^!@#$%^&*_\\.]+$)(?=[a-zA-Z0-9!@#$%^&*_\\.]+$).{8,}$";

        String pas_01 = "aaaaaaaa";
        String pas_02 = "AAAAAAAA";
        String pas_03 = "12345678";
        String pas_04 = "########";

        // 大写+小
        // 大写+数字
        // 大写+特殊字符
        // 小写+数字
        // 小写+特殊字符
        // 数字+特殊字符
        String pas_05 = "AAAAaaaa";
        String pas_06 = "AAAA1111";
        String pas_07 = "AAAA$$$$";
        String pas_08 = "aaaa1111";
        String pas_09 = "aaaa####";
        String pas_10 = "1111####";

        // 没有大写
        // 没有小写
        // 没有数字
        // 没有特殊字符
        String pas_11 = "aaaa11##";
        String pas_12 = "AAAA11##";
        String pas_13 = "AAAAaa##";
        String pas_14 = "AAAAaa11";

        // 包含所有指定字符, 但是也包含非指定字符
        String pas_16 = "AAaa11# ";
        String pas_17 = "AAaa11##  ";
        String pas_18 = "AAaa11###(  ";


        // 全都有 !@#$%^&*_
        String pas_30 = "AAaa11!!";
        String pas_31 = "AAaa11@@";
        String pas_32 = "AAaa11##";
        String pas_33 = "AAaa11$$";
        String pas_34 = "AAaa11%%";
        String pas_35 = "AAaa11^^";
        String pas_36 = "AAaa11&&";
        String pas_37 = "AAaa11**";
        String pas_38 = "AAaa11__";
        String pas_39 = "AAaa11!@#$%^&*_";

        // 新加个 .
        String pas_40 = "AAaa11..";
        String pas_41 = "AAaa11\\.";


        // 四种字符不全
        System.out.println("[01] [" + pas_01 + "] [" + RegexUtils.isMatches(pattern, pas_01) + "]");
        System.out.println("[02] [" + pas_02 + "] [" + RegexUtils.isMatches(pattern, pas_02) + "]");
        System.out.println("[03] [" + pas_03 + "] [" + RegexUtils.isMatches(pattern, pas_03) + "]");
        System.out.println("[04] [" + pas_04 + "] [" + RegexUtils.isMatches(pattern, pas_04) + "]");
        System.out.println("[05] [" + pas_05 + "] [" + RegexUtils.isMatches(pattern, pas_05) + "]");
        System.out.println("[06] [" + pas_06 + "] [" + RegexUtils.isMatches(pattern, pas_06) + "]");
        System.out.println("[07] [" + pas_07 + "] [" + RegexUtils.isMatches(pattern, pas_07) + "]");
        System.out.println("[08] [" + pas_08 + "] [" + RegexUtils.isMatches(pattern, pas_08) + "]");
        System.out.println("[09] [" + pas_09 + "] [" + RegexUtils.isMatches(pattern, pas_09) + "]");
        System.out.println("[10] [" + pas_10 + "] [" + RegexUtils.isMatches(pattern, pas_10) + "]");
        System.out.println("[11] [" + pas_11 + "] [" + RegexUtils.isMatches(pattern, pas_11) + "]");
        System.out.println("[12] [" + pas_12 + "] [" + RegexUtils.isMatches(pattern, pas_12) + "]");
        System.out.println("[13] [" + pas_13 + "] [" + RegexUtils.isMatches(pattern, pas_13) + "]");
        System.out.println("[14] [" + pas_14 + "] [" + RegexUtils.isMatches(pattern, pas_14) + "]");
        System.out.println("[16] [" + pas_16 + "] [" + RegexUtils.isMatches(pattern, pas_16) + "]");
        System.out.println("[17] [" + pas_17 + "] [" + RegexUtils.isMatches(pattern, pas_17) + "]");
        System.out.println("[18] [" + pas_18 + "] [" + RegexUtils.isMatches(pattern, pas_18) + "]");
        System.out.println("[30] [" + pas_30 + "] [" + RegexUtils.isMatches(pattern, pas_30) + "]");
        System.out.println("[31] [" + pas_31 + "] [" + RegexUtils.isMatches(pattern, pas_31) + "]");
        System.out.println("[32] [" + pas_32 + "] [" + RegexUtils.isMatches(pattern, pas_32) + "]");
        System.out.println("[33] [" + pas_33 + "] [" + RegexUtils.isMatches(pattern, pas_33) + "]");
        System.out.println("[34] [" + pas_34 + "] [" + RegexUtils.isMatches(pattern, pas_34) + "]");
        System.out.println("[35] [" + pas_35 + "] [" + RegexUtils.isMatches(pattern, pas_35) + "]");
        System.out.println("[36] [" + pas_36 + "] [" + RegexUtils.isMatches(pattern, pas_36) + "]");
        System.out.println("[37] [" + pas_37 + "] [" + RegexUtils.isMatches(pattern, pas_37) + "]");
        System.out.println("[38] [" + pas_38 + "] [" + RegexUtils.isMatches(pattern, pas_38) + "]");
        System.out.println("[39] [" + pas_39 + "] [" + RegexUtils.isMatches(pattern, pas_39) + "]");
        System.out.println("[40] [" + pas_40 + "] [" + RegexUtils.isMatches(pattern, pas_40) + "]");
        System.out.println("[41] [" + pas_41 + "] [" + RegexUtils.isMatches(pattern, pas_41) + "]");
    }


}
