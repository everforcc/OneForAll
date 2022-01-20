package cn.cc.dawn.config.yml;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.List;

//@Configuration(proxyBeanMethods = false)
//@ConfigurationPropertiesScan
@EnableConfigurationProperties
/*
Not registered via @EnableConfigurationProperties, marked as Spring component, or scanned via @ConfigurationPropertiesScan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "data")
public class ConfigurationData {

    private String str;

    private List<String> stringList;

    private Gproxy gproxy;

    private UserCache testCache;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Gproxy {
        private String ip;
        private int port;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserCache {
        /**
         * 过期时间
         */

        private Duration expired;
        /**
         * 堆内保持数据行数
         */
        private long heap;
        /**
         * 堆外占用内存
         */
        private DataSize offheap;
        /**
         * 磁盘占用空间
         */
        private DataSize disk;
    }

//    public Proxy getGoogleProxy(){
//        return new Proxy(Proxy.Type.HTTP,new InetSocketAddress(gproxy.getIp(),gproxy.getPort()));
//    }

}
