package cn.cc.dawn.local.craw.business.bilibili.utils;

import cn.cc.dawn.local.craw.business.bilibili.constant.BilConstant;
import cn.cc.dawn.utils.file.FilePath;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Yukino
 * 2020/3/3
 */
@Slf4j
public class Method_down {

    /**
     * 下载自定义文件名称
     * @param url
     * @param fileName
     * @throws Exception
     */
//    public static void downByUrl(String url,String dir,String fileName) throws Exception{
//        file(new URL(url).openStream(), dir,fileName);
//    }

    /**
     * 下载文件
     * @param urlPath
     * @param fileName
     * @param path
     * @throws Exception
     */
    public static void down(String urlPath,String fileName,String path)throws Exception{
        file(new URL(urlPath).openStream(),path,fileName);
    }

    /**
     * 下载视频，需要请求头
     * @param flvUrl
     * @param aid
     * @param dir
     * @param fileName
     * @throws Exception
     */
    public static HttpURLConnection downFlv(String flvUrl,String aid,String dir,String fileName,String requestMethod)throws Exception{

        /**
         * 根据地址去请求获取下载视频的链接
         */
        HttpURLConnection conn = Request_Heard.requestHeard_downFlv(flvUrl,"av"+aid,requestMethod);
        System.out.println("视频大小:"+conn.getContentLength());
        // 文件如何拆分的问题，估计最后还是的计算，但是结果可以就好
        /*for(int i=0;i<38287700;i++) {
            conn = Request_Heard.requestHeard_downFlvBySplit(flvUrl,"av"+aid,requestMethod,"bytes=" + i +"-"+(i+=10000000) );
            file(conn.getInputStream(), Constant.rootFilePath + dir + "\\", i + fileName);
        }*/
        return conn;
        // file(conn.getInputStream(), Constant.rootFilePath + dir + "\\",  fileName);
    }

    public static void rename(String oldName,String newName){
        File oldFile = FilePath.build(BilConstant.BILIBILI_FILE_PATH_BUSI).ofFileName(oldName).file();
        File newFile = FilePath.build(BilConstant.BILIBILI_FILE_PATH_BUSI).ofFileName(newName).file();
        oldFile.renameTo(newFile);
        log.info(oldFile.getPath() + "改名为:" + newFile.getPath());
    }

    /**
     * 用来下载文件
     * @param in
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    private static void file(InputStream in,String filePath,String fileName)throws Exception{
        // 总时间
        Date begindate = new Date();

        /**
         * 创建文件夹和文件名
         */
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        //File file = new File(filePath+fileName);
        File file = FilePath.build(filePath).ofFileName(fileName).file();
        if(file.exists()){
            log.info( "文件已经存在:" + filePath + fileName );
           return;
        }
        log.info("开始下载");
        FileOutputStream fo = new FileOutputStream(file);

        /**
         * 以流的方式进行下载
         */
        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = in.read(buf, 0, buf.length)) != -1) {
            fo.write(buf, 0, length);
        }
        in.close();
        fo.close();
        log.info(filePath+fileName + "下载完成");
        /**
         * 计算下载所用时间
         */
        Date enddate = new Date();
        double time = enddate.getTime() - begindate.getTime();
        log.info("耗时：" + time / 1000 + "s");
    }

    public static void record(String dir,String fileName, String content){
        try {

            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
                new File(fileName);
            }

            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("\r\n"+content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
