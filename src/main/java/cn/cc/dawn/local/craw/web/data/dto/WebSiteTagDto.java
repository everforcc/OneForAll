package cn.cc.dawn.local.craw.web.data.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteTagDto extends CommonFiledDto {

    private int webrootid;

    private String weburl;

    private String webname;

}
