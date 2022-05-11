package cn.cc.dawn.common.file.service.impl;

import cn.cc.dawn.common.file.dao.FileDataMapper;
import cn.cc.dawn.common.file.dao.FileMediumMapper;
import cn.cc.dawn.common.file.dao.FileObjMapper;
import cn.cc.dawn.common.file.dto.FileDataDto;
import cn.cc.dawn.common.file.dto.FileObjDto;
import cn.cc.dawn.common.file.dto.FileMediumDto;
import cn.cc.dawn.common.file.service.IFileService;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Primary // 首先使用这个类
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.files.type", havingValue = "MYSQL")
public class MysqlFileService implements IFileService {

    @Resource
    FileObjMapper fileObjMapper;

    @Resource
    FileMediumMapper fileMediumMapper;

    @Resource
    FileDataMapper fileDataMapper;

    IFile fileHandle = new FileApacheUtils();


    /**
     * 磁盘文件系统
     */
//    @Autowired
//    APPConfiguration appconfiguration;

    /**
     *  1. 保存主表数据
     *
     *  save fileObjDto
     *
     *  2. 保存 savetype 数据
     *
     *  3. 保存file对象
     * @param uploadFile
     * @param fileObjDto
     * @param <T>
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public <T extends FileObjDto> T write(MultipartFile uploadFile, T fileObjDto) {
        return write(uploadFile.getBytes(),fileObjDto);
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public <T extends FileObjDto> T write(byte[] bytes, T fileObjDto) {
        String uname = fileObjDto.getUname();
        FileMediumEnum fileMediumEnum = fileObjDto.getSaveType();
        /**
         * 1. 主表数据
         */
        FileObjDto fileObjMain = new FileObjDto();
        fileObjMain.setUname(uname);
        fileObjMain.setRname(fileObjDto.getRname());
        fileObjMain.setContentType(fileObjDto.getContentType());
        fileObjMain.setSize(fileObjDto.getSize());
        /**
         * 2. savetype表
         */
        FileMediumDto fileMediumDto = new FileMediumDto();
        fileMediumDto.setFileMediumEnum(fileMediumEnum);
        fileMediumDto.setUname(uname);

        /**
         * 文件实例保存的位置
         */
        if(FileMediumEnum.WINDOWS.equals(fileMediumEnum)){
            fileMediumDto.setPath(fileObjDto.getPath());
            //fileHandle.write(fileObjDto.getPath() + File.separator + fileObjDto.getUname() ,bytes);
        }else if(FileMediumEnum.MYSQL.equals(fileMediumEnum)){
            /**
             * 3. mysql 文件表
             * 入库 文件
             */
            FileDataDto fileDataDto = new FileDataDto();
            fileDataDto.setFile(bytes);
            fileDataDto.setUname(uname);
            fileDataMapper.insert(fileDataDto);
        }

        boolean flag = this.saveDB(fileObjMain, fileMediumDto);

        return fileObjDto;
    }

    @Override
    public Boolean saveDB(FileObjDto fileObjDto, FileMediumDto fileMediumDto) {
        AppCode.A00201.assertHasInsert(fileObjMapper.insert(fileObjDto));
        AppCode.A00201.assertHasInsert(fileMediumMapper.insert(fileMediumDto));
        return true;
    }
}
