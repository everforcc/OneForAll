package cn.cc.dawn.common.file.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileObj {

    private String md5;
    /**
     * 用户上传的文件名
     */
    private String name;
    /**
     * 文件md5
     */
    private String rname;
    /**
     * 文件类型
     */
    private String contentType;
    private long range;
    /**
     * 文件大小
     */
    private long size;


}
