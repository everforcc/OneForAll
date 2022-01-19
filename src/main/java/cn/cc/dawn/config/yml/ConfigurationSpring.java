package cn.cc.dawn.config.yml;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

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
@ConfigurationProperties(prefix = "spring")
public class ConfigurationSpring {

    private Redis redis;
    private Ftp ftp;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Redis {
        private String host;
        private int port;
        private String password;
        private int database;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ftp {
        private String username;
        private String password;
        private String path;
    }

//    public Proxy getGoogleProxy(){
//        return new Proxy(Proxy.Type.HTTP,new InetSocketAddress(gproxy.getIp(),gproxy.getPort()));
//    }

}
