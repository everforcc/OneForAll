package cn.cc.dawn.demo.servlet.controller;

import cn.cc.dawn.utils.file.IFilePath;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RequestMapping(value = "/d/file/down")
public class FileDownController {

    // 可以成功下载
    @RequestMapping("/file")
    public String file(HttpServletResponse response){
        String path= IFilePath.pathRoot() + "/test/github/视频/19286458_陈翔六点半/aid329651628_cid237925043_陈翔六点半：就你这种人，也配暗恋我？_完整暧昧短信.flv";
        String fileName="2.flv";
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = fileName;
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1)
                    .toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response
                    .getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "fun ";
        //return "index";
    }

}
