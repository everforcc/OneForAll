package cn.cc.dawn.business.craw.crawbilibili.controller;

import cn.cc.dawn.business.craw.crawbilibili.bilibili.Bilibili_Flv;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;

/**
 * Yukino
 * 2020/6/21
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String index(){
        return "fun ";
        //return "index";
    }

    // 可以成功下载
    @RequestMapping("/file")
    public String file(HttpServletResponse response){
        String path="E:\\test\\github\\视频\\19286458_陈翔六点半\\aid329651628_cid237925043_陈翔六点半：就你这种人，也配暗恋我？_完整暧昧短信.flv";
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

    @RequestMapping("/bilibili")
    public String bilibili(HttpServletResponse response, HttpServletRequest request){

        String fileName="1.flv";
        try {
            // path是指欲下载的文件的路径。
            // File file = new File(path);
            // 取得文件名。
            String filename = fileName;
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1)
                    .toUpperCase();
            Bilibili_Flv bilibili_flv = new Bilibili_Flv();


            // 精简的输出和debug
            HttpURLConnection conn = bilibili_flv.downAV((String)request.getParameter("num"),true);
            //
            InputStream fis = new BufferedInputStream(conn.getInputStream());

            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            // 先给固定的
            response.addHeader("Content-Length", "" + conn.getContentLength() );
            OutputStream toClient = new BufferedOutputStream(response
                    .getOutputStream());
            response.setContentType("application/octet-stream");


/*
            // 以流的形式下载文件。

            //byte[] buffer = new byte[fis.available()];
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            toClient.write(buffer);
            toClient.flush();
            toClient.close();*/

            /**
             *   1k 缓存下载
             */
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = fis.read(buf, 0, buf.length)) != -1) {
                toClient.write(buf, 0, length);
            }
            fis.close();
            toClient.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println(" 爬那个部分的错");
            e.printStackTrace();
        }
        return "fun ";
        //return "index";
    }
    // 测试直接把b站的视频流转过来
}
