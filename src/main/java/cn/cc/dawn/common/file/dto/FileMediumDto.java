package cn.cc.dawn.common.file.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件存储介质
 */
@Getter
@Setter
public class FileMediumDto extends CommonFiledDto {

    /**
     * 父节点的id FileObjDto
     */
    //private int parentid;

    private String uname;
    /**
     * 文件存储介质
     */
    private FileMediumEnum fileMediumEnum;

    /**
     * 存储路径，如果是目录结构的话，各种介质使用同种路径
     */
    private String path;

}
