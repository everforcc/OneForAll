/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 13:07
 * Copyright
 */

package cn.cc.dawn.business.game.drawcard.util;

import cn.cc.dawn.utils.RandomUtils;

public class ProbabilityUtil {

    private static Double start = 0.00d;
    private static Double end = 1.00d;

    public static Double randomDouble(){
        return RandomUtils.getRandomDouble(start,end);
    }

}
