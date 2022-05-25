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

    //private String defaultaeskey;

    private String filepath;

    private Files files;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Files{
        private String path;
        private FileMediumEnum type;
    }

}
