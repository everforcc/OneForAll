package cn.cc.dawn.common.file.service;

import cn.cc.dawn.common.file.dao.FileObjMapper;
import cn.cc.dawn.common.file.dto.FileObjDto;
import cn.cc.dawn.common.file.dto.FileMediumDto;
import cn.cc.dawn.utils.exception.AppCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 文件保存
 * 多种保存类型
 * mysql/ftp/aly/bdy
 *
 * 定一个加入bean，其他的按照名字区分
 */
@Validated
public interface IFileService {

    @NotNull(message = "返回值不能为null") <T extends FileObjDto> T write(
            @NotNull(message = "参数【uploadFile】不能为null") final MultipartFile uploadFile,
            @NotNull(message = "参数【fileObjDto】不能为null") final T fileObjDto
    );

    default  Boolean saveDB(
            @NotNull(message = "参数【fileObjDto】不能为null") final FileObjDto fileObjDto,
            @NotNull(message = "参数【fileSaveDto】不能为null") final FileMediumDto fileMediumDto
    ){
        AppCode.A00201.assertHasInsert(1);
        AppCode.A00201.assertHasInsert(2);

        return true;
    }

}
