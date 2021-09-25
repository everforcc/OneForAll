package cn.cc.dawn.business.aoplog;

import org.junit.jupiter.api.Test;

/**
 * @author guokailong 2021-09-25
 */
public class TAllLog {

    @Test
    public void t1(){
        AllLog allLog = new AllLog();
        allLog.tAOPLog(1);
    }

}
