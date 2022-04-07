package cn.cc.dawn.config.init.yml;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties
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
