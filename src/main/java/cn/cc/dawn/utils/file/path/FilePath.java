package cn.cc.dawn.utils.file.path;

import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.exception.AppCode;

import java.io.File;

/**
 * 用来处理持久化时文件路径的问题
 */
public class FilePath {

    /**
     * 根目录
     */
    private static IFilePath defaultBusiRoot = new TestFilePath();
    /**
     * 测试根目录
     */
    private static String busiRoot;

    /**
     * 文件夹何文件名
     */
    private String fileName;
    private String[] dirs;

    /**
     * 构造
     */
    private FilePath() {
    }
    private FilePath(boolean flag,IFilePath iFilePath) {
         this.busiRoot = SystemFileConstant.pathRoot() + iFilePath.busiPath();
    }
    private FilePath(String filePath) {
        this.busiRoot = filePath;
    }

    /**
     * 1. 只能通过build实例化，默认的是test目录
     * @return
     */
    public static FilePath build(){
        return new FilePath(BooleanEnum.TRUE.flag,defaultBusiRoot);
    }
    public static FilePath build(IFilePath iFilePath){
        return new FilePath(BooleanEnum.TRUE.flag,iFilePath);
    }

    /**
     * 此代码测试使用，尽量不要在系统中用
     * 如果用，必须保证代码逻辑严谨
     * @param filePath
     * @return
     */
    public static FilePath build(String filePath){
        return new FilePath(filePath);
    }

    /**
     * 业务类以此为根目录在此基础上进行文件 持久化
     * 每类业务实现该方法，不要直接调用，方便后期统计业务
     * @return
     */

    /**
     * 2. of系列方法，返回的是当前对象
     * 传的值是目录
     * 用来传文件目录
     * @param dirs
     * @return
     */
    public FilePath ofPath(String... dirs){
        this.dirs = dirs;
        return this;
    }
    public FilePath ofFileName(String fileName){
        this.fileName = fileName;
        return this;
    }

    /**
     * 将参数处理为文件路径
     * TODO 在这里处理公共的文件名/路径,异常
     * @return
     */
    public String path(){
        StringBuffer stringBuffer = new StringBuffer(busiRoot);
        for(String dir:dirs){
            stringBuffer.append( File.separator + dir );
        }
        File file = new File(stringBuffer.toString());
        if(!file.exists()){
            file.mkdirs();
        }

        if(StringUtils.isNotEmpty(fileName)){
            stringBuffer.append(File.separator + fileName);
        }
        busiRoot = stringBuffer.toString();
        return busiRoot;
    }


    public File file(){
        AppCode.A00305.assertHasTrue(StringUtils.isNotEmpty(fileName));
        return new File(busiRoot + File.separator +  fileName);
    }


    public static void main(String[] args) {
        System.out.println(FilePath.build().ofPath("dir1", "dir2").path());
        System.out.println(FilePath.build().ofPath("dir1", "dir2").ofFileName("1.txt").path());
        System.out.println(FilePath.build().ofPath("dir1", "dir2").ofFileName("1.txt").file());
    }

    public static class TestFilePath implements IFilePath{

        @Override
        public String busiPath() {
            return "\\test";
        }
    }

}
