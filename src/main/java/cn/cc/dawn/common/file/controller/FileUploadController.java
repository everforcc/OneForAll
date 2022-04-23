package cn.cc.dawn.common.file.controller;

import cn.cc.dawn.common.file.dto.FileObjDto;
import cn.cc.dawn.common.file.service.IFileService;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.file.FileBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/c/file/upload")
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

    @GetMapping("/tsave/{uid}")
    public void tSaveImg(){
        byte[] bytes = new byte[100];

        FileObjDto fileObjDto = FileObjDto.build(
                FileBuilder.FileMsg
                        .of("1.txt","100","E:/filesystem/test/临时")
                        .buildUuidUname()
                        .buildSaveType(FileMediumEnum.WINDOWS)
        );
        iFileService.write(bytes,fileObjDto);
    }

    //    @PostMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile file){
//        try {
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(FilePath.build().ofPath("audio").ofFileName(file.getOriginalFilename()).path());
//            Files.write(path,bytes);
//            return "文件上传成功";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "文件上传失败";
//        }
//    }

}
