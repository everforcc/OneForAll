package cn.cc.dawn.config.init.yml;

import cn.cc.dawn.open.auth.cache.CustomerUserCache;
import cn.cc.dawn.open.auth.cache.ICustomerUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "app.cache")
public class APPConfigurationCache {

    CustomerUserCache customuser;
    //ICustomerUser customuser;

}
