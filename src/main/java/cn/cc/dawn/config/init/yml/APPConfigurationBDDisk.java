/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 11:16
 * Copyright
 */

package cn.cc.dawn.config.init.yml;

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
@ConfigurationProperties(prefix = "app.bddisk")
public class APPConfigurationBDDisk {

    private String ID;
    /**
     * 有的地方也叫 client_id
     */
    private String API_Key;
    private String Secret_Key;

    /**
     * Web端
     * https://pan.baidu.com/union/doc/0ksg0sbig
     */
    /**
     * 1. 用户授权地址
     */
    private String authorizeWebUrl;

    /**
     * 2. 授权后回调地址
     */
    private String redirect_uriWebUrl;

    /**
     * 3. code换access_token
     */
    private String access_tokenWebUrl;

    /**
     * 4. 刷新token
     */
    private String refresh_tokenWebUrl;

    /**
     * 移动端
     */

    /**
     * 5. xpan_nas
     * 用户信息相关
     */
    private String xpan_nas;
    /**
     * 7. xpan_file
     * 文件上传相关
     */
    private String xpan_file;
    /**
     * 8. 分片上传
     */
    private String slice_file;

}
