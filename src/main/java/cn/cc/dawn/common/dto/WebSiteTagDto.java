package cn.cc.dawn.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteTagDto extends CommonFiledDto{

    private int webrootid;

    private String weburl;

    private String webname;

}
