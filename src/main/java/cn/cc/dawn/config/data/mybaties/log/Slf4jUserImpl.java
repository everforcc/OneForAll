package cn.cc.dawn.config.data.mybaties.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

@Slf4j
public class Slf4jUserImpl implements Log {

    //private final Log log;

    public Slf4jUserImpl(String clazz) {
//        Logger logger = LoggerFactory.getLogger(clazz);
//        this.log = new Slf4jUserLoggerImpl(logger);

//        logger = (Logger)log;
//        logger.isInfoEnabled();
    }

    public boolean isDebugEnabled() {
        return true;
    }

    public boolean isTraceEnabled() {
        return true;
    }

    public void error(String s, Throwable e) {
        //System.err.println(s);
        //e.printStackTrace(System.err);
    }

    public void error(String s) {
        //System.err.println(s);
    }

    public void debug(String s) {
        ////System.out.println("Slf4jUserImpl");
        ////System.out.println(s);
        //System.out.println(s);
        //log.info("log.isDebugEnabled(): " + log.isDebugEnabled());
        if(log.isInfoEnabled()&&(s.startsWith("==>")||s.startsWith("<=="))){
            log.info(s);
        }else {
            log.debug(s);
        }

    }

    public void trace(String s) {
        //System.out.println(s);
    }

    public void warn(String s) {
        //System.out.println(s);
    }



}
