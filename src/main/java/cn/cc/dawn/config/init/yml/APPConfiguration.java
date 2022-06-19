package cn.cc.dawn.config.init.yml;

import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/*
Not registered via @EnableConfigurationProperties, marked as Spring component, or scanned via @ConfigurationPropertiesScan
 */
//@Configuration(proxyBeanMethods = false)
//@ConfigurationPropertiesScan
@EnableConfigurationProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "app")
public class APPConfiguration {

    /**
     * 文件保存磁盘的路径，目前在使用这一种
     */
    private String filepath;

    /**
     * 文件保存(win还是mysql) 和位置
     */
    private Files files;

    /**
     * ftp配置
     */
    private Ftp ftp;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Files {

        /**
         * 文件保存系统的路径
         */
        private String path;

        /**
         * 文件保存 的方法
         */
        private FileMediumEnum type;

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

}
