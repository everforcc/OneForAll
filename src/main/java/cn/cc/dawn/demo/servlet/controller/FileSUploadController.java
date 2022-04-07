package cn.cc.dawn.demo.servlet.controller;

import cn.cc.dawn.common.file.dto.FileObjDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/d/file/upload")
public class FileSUploadController {

    // 存文件信息
    public static Map<String, FileObjDto> mapCache = new HashMap<>();
    public static Map<String,InputStream> streamMapCache = new HashMap<>();

    //public static Map<String,FileObj> mapCache = new HashMap<>();

    /**
     * 1. md5校验文件信息
     * 2. 拿到是否有文件
     * 2.1 没直接上传
     * 2.2 有拿到文件位置
     */

    /*@PostMapping("/hand")
    public void handleFileUploadReq(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) httpServletRequest;

        MultipartFile file = multipartRequest.getFile("ImFileName");

    }*/

    @PostMapping("/hand")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        // 校验文件存在return
        long range = 0L;
        // 校验
        FileObjDto fileObjDto = new FileObjDto();
        fileObjDto.setRname(file.getOriginalFilename());
        String fileName = fileObjDto.getUname();
        System.out.println("file.getSize(): " + file.getSize());
        InputStream in = null;

        try {
            in = file.getInputStream();
            streamMapCache.put(fileObjDto.getMd5(),in);
            FileOutputStream fo = new FileOutputStream("C:/test/" + fileName,true);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
                range+=length;
            }
        } catch (IOException e) {
            e.printStackTrace();
            fileObjDto.setRange(range);
            fileObjDto.setSize(file.getSize() + "");
            mapCache.put(fileObjDto.getMd5(), fileObjDto);

        }finally {
            System.out.println("fileName:" + fileName + "[end]");
        }
    }

    @GetMapping("/check/{md}")
    public String check(@PathVariable("md")String md) {
        // 校验
        return mapCache.containsKey(md)?JSONObject.toJSONString(mapCache.get(md)):null;
    }


}
