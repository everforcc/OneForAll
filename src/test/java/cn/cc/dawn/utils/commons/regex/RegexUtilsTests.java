/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-20 15:27
 * Copyright
 */

package cn.cc.dawn.utils.commons.regex;

import org.junit.Test;

public class RegexUtilsTests {

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
        // !@#$%^&*_
        //String pattern = "^(?![^a-z]+$)(?![^A-Z]+$)(?!\\D+$)(?![a-zA-Z0-9]+$)(?!\\s+$).{8,}$";
        String pattern = "^(?![^a-z]+$)(?![^A-Z]+$)(?!\\D+$)(?![^!@#$%^&*_]+$)(?=[a-zA-Z0-9!@#$%^&*_]+$).{8,}$";

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

        // 四种字符不全
        System.out.println("[01] [" + RegexUtils.matcheStr(pattern, pas_01) + "]");
        System.out.println("[02] [" + RegexUtils.matcheStr(pattern, pas_02) + "]");
        System.out.println("[03] [" + RegexUtils.matcheStr(pattern, pas_03) + "]");
        System.out.println("[04] [" + RegexUtils.matcheStr(pattern, pas_04) + "]");
        System.out.println("[05] [" + RegexUtils.matcheStr(pattern, pas_05) + "]");
        System.out.println("[06] [" + RegexUtils.matcheStr(pattern, pas_06) + "]");
        System.out.println("[07] [" + RegexUtils.matcheStr(pattern, pas_07) + "]");
        System.out.println("[08] [" + RegexUtils.matcheStr(pattern, pas_08) + "]");
        System.out.println("[09] [" + RegexUtils.matcheStr(pattern, pas_09) + "]");
        System.out.println("[10] [" + RegexUtils.matcheStr(pattern, pas_10) + "]");
        System.out.println("[11] [" + RegexUtils.matcheStr(pattern, pas_11) + "]");
        System.out.println("[12] [" + RegexUtils.matcheStr(pattern, pas_12) + "]");
        System.out.println("[13] [" + RegexUtils.matcheStr(pattern, pas_13) + "]");
        System.out.println("[14] [" + RegexUtils.matcheStr(pattern, pas_14) + "]");
        System.out.println("[16] [" + RegexUtils.matcheStr(pattern, pas_16) + "]");
        System.out.println("[17] [" + RegexUtils.matcheStr(pattern, pas_17) + "]");
        System.out.println("[18] [" + RegexUtils.matcheStr(pattern, pas_18) + "]");
        System.out.println("[30] [" + RegexUtils.matcheStr(pattern, pas_30) + "]");
        System.out.println("[31] [" + RegexUtils.matcheStr(pattern, pas_31) + "]");
        System.out.println("[32] [" + RegexUtils.matcheStr(pattern, pas_32) + "]");
        System.out.println("[33] [" + RegexUtils.matcheStr(pattern, pas_33) + "]");
        System.out.println("[34] [" + RegexUtils.matcheStr(pattern, pas_34) + "]");
        System.out.println("[35] [" + RegexUtils.matcheStr(pattern, pas_35) + "]");
        System.out.println("[36] [" + RegexUtils.matcheStr(pattern, pas_36) + "]");
        System.out.println("[37] [" + RegexUtils.matcheStr(pattern, pas_37) + "]");
        System.out.println("[38] [" + RegexUtils.matcheStr(pattern, pas_38) + "]");
        System.out.println("[39] [" + RegexUtils.matcheStr(pattern, pas_39) + "]");

    }


}
