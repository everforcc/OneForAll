package cn.cc.dawn.local.craw.business.data.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.enums.impl.FileTypeEnum;
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

    private FileMediumEnum medium = FileMediumEnum.WINDOWS;
    private FileTypeEnum filetype = FileTypeEnum.TXT;

}
