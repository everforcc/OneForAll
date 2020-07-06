package cn.cc.dawn.persistence.io;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Yukino
 * 2020/7/3
 * 写入操作
 */
public class InputStream_IO_Write {

    public InputStream_IO_Write() {
    }

    public InputStream_IO_Write(String path,String fileName) {
        // 后缀可能有文件名，但是路径应该不会有点，所以分开它
        /*if(path.contains("\\.")){
            path=path.substring(0,path.lastIndexOf(File.separator));
            System.out.println(path);
        }*/
        this.fileName=fileName;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        this.path = path;
    }

    private String fileName;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 系统文件 文件末尾追加
     */
    public void IO_PrintWriter_Append(String path, String content) { this.writeAppend(path,new String[]{content}); }

    public void IO_PrintWriter_Append(String content) { this.writeAppend(null,new String[]{content}); }

    public void IO_PrintWriter_Append(String path, String[] content) {
        this.writeAppend(path,content);
    }

    public void IO_PrintWriter_Append(String[] content) { this.writeAppend(null,content); }

    private void writeAppend(String path, String content[]){
        if(StringUtils.isEmpty(path)){
            path=this.path; // 这里处理的还是有问题，也有可能没初始化path也过来，再说吧
        }
        File f = new File(path+this.fileName);
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            //File f=new File("D:a.txt");
            // java.io.FileNotFoundException: C:\NEW\ideaProject\aly\OneForAll\temp\古文名句\《楞严经》.txt (另一个程序正在使用此文件，进程无法访问。)
            fw = new FileWriter(f, true);
            // 总是开启关闭浪费资源，直接组装好给放进来
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        // web乱码 printWriter=new PrintWriter(new OutputStreamWriter(new FileOutputStream(ndfFileName), "UTF-8"));

        // 追加
        for(String str:content) {
            pw.println(str);
        }

        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String checkFileName(String fileName){
        //在Windows系统中，文件名命名规则如下：
        //1）文件名最长可以使用255个字符；
        //2）可以使用扩展名，扩展名用来表示文件类型，也可以使用多间隔符的扩展名（如win.ini.txt是一个合法的文件名，但其文件类型由最后一个扩展名决定）；
        //3）文件名中允许使用空格，但不允许使用下列字符（英文输入法状态）：< > / \ | : " * ?；
        //4）windows系统对文件名中字母的大小写在显示时有不同，但在使用时不区分大小写。
        String pattern="\\<*\\>*\\/*\\\\*\\|*\\:*\"*\\**\\?*\\；*\\ *";

        fileName=fileName.replaceAll(pattern,"");
        if(fileName.length()>255){
            return fileName.substring(0,250);
        }
        return fileName;
    }

}
