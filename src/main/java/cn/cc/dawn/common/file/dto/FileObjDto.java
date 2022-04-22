package cn.cc.dawn.common.file.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.file.FileBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileObjDto extends CommonFiledDto {

    /**
     * 新生成的uuid文件名
     */
    private String uname;
    /**
     * 用户上传的文件名
     */
    private String rname;
    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 文件大小
     */
    private String size;

    /**
     *  模拟权限分配的方式
     *  mysql-ftp-aly-bdy-
     *  0-0-0-0-
     *  还是用子类好了
     */
    //private FileMediumEnum saveType;

    /**
     * 文件md5
     */
    private String md5;
    /**
     * 拆分，留下来用来分批次下载，待定
     */
    private long range;

    public FileObjDto(FileBuilder.FileMsg fileMsg) {
        this.uname = fileMsg.getUname();
        this.rname = fileMsg.getRname();
        this.contentType = fileMsg.getContentType();
        this.size = fileMsg.getSize();
    }


    public static FileObjDto build(FileBuilder.FileMsg fileMsg){
        return new FileObjDto(fileMsg);
    }

}
