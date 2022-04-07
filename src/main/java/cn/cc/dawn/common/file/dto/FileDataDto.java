package cn.cc.dawn.common.file.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileDataDto extends CommonFiledDto {

    /**
     * 重新生成的文件名 uuid
     */
    private String uname;
    /**
     * 用户上传的文件名
     */
    //  private String rname;
    /**
     * 文件类型
     */
    //  private String contentType;
    /**
     * 文件大小
     */
    //  private String size;
    /**
     * 文件对象
     */
    private byte[] file;


}
