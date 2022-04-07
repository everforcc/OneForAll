package cn.cc.dawn.common.file.controller;

import cn.cc.dawn.common.file.dto.FileObjDto;
import cn.cc.dawn.common.file.service.IFileService;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.file.FileBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/c/upload")
public class FileUploadController {

    @Autowired
    IFileService iFileService;

    @PostMapping("/write")
    @ResponseBody
    public ResultE<FileObjDto> write(@RequestParam final MultipartFile file){
        return new ResultE<FileObjDto>().execute(e->
                e.setSuccess(
                        iFileService.write(file,FileObjDto.build(
                                FileBuilder.FileMsg
                                        .of(file)
                                        .buildUuidUname())
                        )
                )
        );
    }

}
