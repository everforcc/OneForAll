package cn.cc.dawn.local.craw.bilibili.controller;

import cn.cc.dawn.local.craw.bilibili.flow.Bilibili_Cover;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.file.path.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Yukino
 * 2020/6/22
 */
@Slf4j
@RequestMapping("/local/craw/bilcover")
@RestController
public class BilCoverController {

    /**
     *  controller
     *  管理链接，和前端对接
     */

    Bilibili_Cover bilibili_cover = new Bilibili_Cover();

    @RequestMapping("/bilibili_cover/{num}")
    public ResultE<String> getCover(@PathVariable String num ){
        log.info(num);
        return new ResultE<String>().execute(e->
                e.setSuccess(bilibili_cover.getBilCover(num))
        );
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
