package cn.cc.dawn.common.service;

import cn.cc.dawn.common.dto.FileSystemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
@Transactional
//@RequiredArgsConstructor
public class FileSystemService {

    public String testPattern(@Valid @NotNull(message = "【fileSystemDto】不能为null") final FileSystemDto fileSystemDto){
        System.out.println(fileSystemDto.getName());
        return "";
    }

}
