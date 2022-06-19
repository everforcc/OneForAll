package cn.cc.dawn.business.game.drawcard.util;

import cn.cc.dawn.utils.commons.lang.RRandomUtils;

public class ProbabilityUtil {

    private static Double start = 0.00d;
    private static Double end = 1.00d;

    public static Double randomDouble(){
        return RRandomUtils.getRandomDouble(start,end);
    }

}
