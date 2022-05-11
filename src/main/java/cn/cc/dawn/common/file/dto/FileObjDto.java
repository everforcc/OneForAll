package cn.cc.dawn.common.file.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.file.FileBuilderDB;
import cn.cc.dawn.utils.inter.valited.ISave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileObjDto extends CommonFiledDto {

    /**
     * 新生成的uuid文件名
     */
    @NotNull(groups = {ISave.class},message = "uname不允许为空")
    private String uname;
    /**
     * 用户上传的文件名
     */
    @NotNull(groups = {ISave.class},message = "rname不允许为空")
    private String rname;
    /**
     * 文件类型
     */
    @NotNull(groups = {ISave.class},message = "contentType不允许为空")
    private String contentType;
    /**
     * 文件大小
     */
    @NotNull(groups = {ISave.class},message = "size不允许为空")
    private String size;

    /**
     *  模拟权限分配的方式
     *  mysql-ftp-aly-bdy-
     *  0-0-0-0-
     *  还是用子类好了
     */
    //@TableField(exist = false)
    /* 该字段不入库 */
    // 如果选windows必须指定 path
    @NotNull(groups = {ISave.class},message = "saveType不允许为空")
    private FileMediumEnum saveType;
    /* 该字段不入库 */
    private String path;

    /**
     * 文件md5
     */
    private String md5;
    /**
     * 拆分，留下来用来分批次下载，待定
     */
    private long range;

    public FileObjDto(FileBuilderDB.FileMsg fileMsg) {
        this.uname = fileMsg.getUname();
        this.rname = fileMsg.getRname();
        this.contentType = fileMsg.getContentType();
        this.size = fileMsg.getSize();
        //this.path = fileMsg.getDir() + File.separator + this.uname;
        this.path = fileMsg.getDir();
        this.saveType = fileMsg.getFileMediumEnum();
    }


    public static FileObjDto build(FileBuilderDB.FileMsg fileMsg){
        return new FileObjDto(fileMsg);
    }

}
