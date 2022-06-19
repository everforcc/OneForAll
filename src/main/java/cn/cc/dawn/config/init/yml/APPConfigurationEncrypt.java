/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-25 20:37
 */

package cn.cc.dawn.config.init.yml;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "app.encrypt")
@EnableConfigurationProperties
public class APPConfigurationEncrypt {

    private String aesEnable;

    private String defaultAeskey;

}
