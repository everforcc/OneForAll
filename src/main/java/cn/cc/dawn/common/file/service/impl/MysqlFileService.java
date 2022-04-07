package cn.cc.dawn.common.file.service.impl;

import cn.cc.dawn.common.file.dao.FileDataMapper;
import cn.cc.dawn.common.file.dao.FileMediumMapper;
import cn.cc.dawn.common.file.dao.FileObjMapper;
import cn.cc.dawn.common.file.dto.FileDataDto;
import cn.cc.dawn.common.file.dto.FileObjDto;
import cn.cc.dawn.common.file.dto.FileMediumDto;
import cn.cc.dawn.common.file.service.IFileService;
import cn.cc.dawn.config.init.yml.APPConfiguration;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.exception.AppCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

        String uname = fileObjDto.getUname();
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
        fileMediumDto.setFileMediumEnum(FileMediumEnum.MYSQL);
        fileMediumDto.setUname(uname);

        boolean flag = this.saveDB(fileObjMain, fileMediumDto);

        /**
         * 3. mysql 文件表
         */
        FileDataDto fileDataDto = new FileDataDto();
        fileDataDto.setFile(uploadFile.getBytes());
        fileDataDto.setUname(uname);

        /**
         * 入库 文件
         */
        fileDataMapper.insert(fileDataDto);

        return fileObjDto;
    }

    @Override
    public Boolean saveDB(FileObjDto fileObjDto, FileMediumDto fileMediumDto) {
        AppCode.A00201.assertHasInsert(fileObjMapper.insert(fileObjDto));
        AppCode.A00201.assertHasInsert(fileMediumMapper.insert(fileMediumDto));
        return true;
    }
}
