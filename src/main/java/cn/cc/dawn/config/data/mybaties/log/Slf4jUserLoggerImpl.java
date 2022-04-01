package cn.cc.dawn.config.data.mybaties.log;

import org.apache.ibatis.logging.Log;
import org.slf4j.Logger;

public class Slf4jUserLoggerImpl implements Log {

    private final Logger log;

    public Slf4jUserLoggerImpl(Logger logger) {
        this.log = logger;
        log.isInfoEnabled();
    }

    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return this.log.isTraceEnabled();
    }

    public void error(String s, Throwable e) {
        this.log.error(s, e);
    }

    public void error(String s) {
        this.log.error(s);
    }

    public void debug(String s) {
        System.out.println("Slf4jUserLoggerImpl： " + s);
        //this.log.debug(s);
    }

    public void trace(String s) {
        this.log.trace(s);
    }

    public void warn(String s) {
        this.log.warn(s);
    }

}
