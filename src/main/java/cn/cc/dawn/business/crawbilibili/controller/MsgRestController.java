package cn.cc.dawn.business.crawbilibili.controller;

import cn.cc.dawn.business.jdkcraw.Bilibili_Cover;
import cn.cc.dawn.config.init.Init;
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
@RestController
public class MsgRestController {

    /**
     *  controller
     *  管理链接，和前端对接
     */

    Bilibili_Cover bilibili_cover = new Bilibili_Cover();
    Init init =  Init.getInstanse();

    @RequestMapping("/getfun")
    public String getfun(){
        return init.getFun();
    }


    @RequestMapping("/bilibili_cover")
    public String getCover(@RequestParam("num") String num ){

        System.out.println(num);
        return bilibili_cover.getBilCover(num);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("E:\\audio\\"+file.getOriginalFilename());
            Files.write(path,bytes);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }

}
