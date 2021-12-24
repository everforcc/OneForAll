package cn.cc.dawn.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

//@Configuration(proxyBeanMethods = false)
//@ConfigurationPropertiesScan
@EnableConfigurationProperties
/*
Not registered via @EnableConfigurationProperties, marked as Spring component, or scanned via @ConfigurationPropertiesScan
 */
@Component
@ConfigurationProperties(prefix = "data")
public class ConfigurationData {

    private String str;

    private List<String> stringList;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
