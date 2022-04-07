package cn.cc.dawn.config.init.yml;

import cn.cc.dawn.utils.enums.impl.FileMediumEnum;

//@EnableConfigurationProperties
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Component
//@ConfigurationProperties(prefix = "app.files")
public class APPConfigurationFiles {

    private String path;

    private FileMediumEnum type;

}
