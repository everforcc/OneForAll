package cn.cc.dawn.local.craw.bilibili.utils;

import cn.cc.dawn.utils.file.path.FilePath;
import cn.cc.dawn.utils.file.path.IFilePath;

/**
 * Yukino
 * 2020/3/9
 */
public class Print_Record {

    private static Print_Record print_record;

    private String fileName;

    private Print_Record(String fileName){
        this.fileName = FilePath.build(new LogFilePath()).ofPath("userdefinelog").ofFileName(ToolTime.nowTime() + "_" + fileName + ".log").path();
    };

    public static synchronized  Print_Record getInstanse(String fileName){
        if(print_record == null){
            print_record = new Print_Record(fileName);
        }
        return print_record;
    }


    public void println(String msg){
        /*Method_down.record(Constant.rootFilePath + "log\\" ,fileName,msg);
        System.out.println("通过帮助类输出----------");
        System.out.println(msg);*/
    }

    public static class LogFilePath implements IFilePath{

        @Override
        public String busiPath() {
            return "log";
        }
    }

}
