package cn.cc.dawn.common.file.service.impl;

import cn.cc.dawn.common.file.dto.FileObjDto;
import cn.cc.dawn.common.file.service.IFileService;
import cn.cc.dawn.config.init.yml.APPConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Primary // 首先使用这个类
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.files.type", havingValue = "WINDOWS")
public class WindowsFileService implements IFileService {

    @Autowired
    APPConfiguration appconfiguration;

    @Override
    public <T extends FileObjDto> T write(MultipartFile uploadFile, T fileObjDto) {
        APPConfiguration.Files files = appconfiguration.getFiles();
        System.out.println("write file :" + appconfiguration.getFiles());
        log.debug("write file :" + appconfiguration.getFiles());
        log.info("write file :" + appconfiguration.getFiles().getPath());
        log.warn("write file :" + appconfiguration.getFiles().getPath());
        return fileObjDto;
    }
}
