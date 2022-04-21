package cn.cc.dawn.local.craw.bilibili.utils;

import cn.cc.dawn.utils.file.path.FilePath;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Yukino
 * 2020/3/3
 */
public class Method_down {

    private static Print_Record println = Print_Record.getInstanse("");

    /**
     * 下载自定义文件名称
     * @param url
     * @param fileName
     * @throws Exception
     */
    public static void downByUrl(String url,String dir,String fileName) throws Exception{
        URL uri = new URL(url);
        InputStream in = uri.openStream();
        file(uri.openStream(), FilePath.build(BilConstant.bilibiliFilePath).ofPath(dir).path(),fileName);
    }

    /**
     *
     * 根据网络路径命名
     * @param urlPath
     * @param dir
     * @throws Exception
     */
    public static void down(String urlPath,String dir)throws Exception{
        /**
         * 截取网络图片的名字和参数
         */
        String imageName = urlPath.substring(urlPath.lastIndexOf("/") + 1, urlPath.length());
        println.println("生成文件名:" + imageName);
        if (imageName.contains("?")) {
            println.println("处理字符串");
            imageName = imageName.substring(0, imageName.lastIndexOf("@"));
            println.println("再次生成文件名" + imageName);
        }
        URL uri = new URL(urlPath);
        file(uri.openStream(),FilePath.build(BilConstant.bilibiliFilePath).ofPath(dir).path(),imageName);
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
        File oldFile = FilePath.build(BilConstant.bilibiliFilePath).ofFileName(oldName).file();
        File newFile = FilePath.build(BilConstant.bilibiliFilePath).ofFileName(newName).file();
        oldFile.renameTo(newFile);
        println.println(oldFile.getPath() + "改名为:" + newFile.getPath());
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
            println.println( "文件已经存在:" + filePath + fileName );
           return;
        }
        println.println("开始下载");
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
        println.println(filePath+fileName + "下载完成");
        /**
         * 计算下载所用时间
         */
        Date enddate = new Date();
        double time = enddate.getTime() - begindate.getTime();
        println.println("耗时：" + time / 1000 + "s");
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