package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.constant.FileRegexConstant;
import cn.cc.dawn.utils.file.constant.SystemFileConstant;

import java.io.File;

/**
 * 用来处理持久化时文件路径的问题
 */
public class FilePath {

    /**
     * 根目录
     */
    private static IFilePathBusi defaultBusiRoot = new TestFilePathBusi();
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
    private FilePath(boolean flag, IFilePathBusi iFilePathBusi) {
         this.busiRoot = SystemFileConstant.pathRoot() + iFilePathBusi.busiPath();
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
    public static FilePath build(IFilePathBusi iFilePathBusi){
        return new FilePath(BooleanEnum.TRUE.flag, iFilePathBusi);
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
        this.fileName = checkFileName(fileName);
        return this;
    }

    public String getSuffix() {
        return getSuffix(false);
    }

    public String getSuffix(boolean offset) {
        //return fileName.replaceFirst(FileRegexConstant.suffix, (offset ? "." : ""));
        return getSuffix(fileName,offset);
    }

    public static String getSuffix(String fileName){
        return getSuffix(fileName,false);
    }

    public static String getSuffix(String fileName,boolean offset){
        return fileName.replaceFirst(FileRegexConstant.suffix, (offset ? "." : ""));
    }

    /**
     * 将参数处理为文件路径
     * TODO 在这里处理公共的文件名/路径,异常
     * @return
     */
    public String path(){
        StringBuffer stringBuffer = new StringBuffer(busiRoot);
        for(String dir:dirs){
            dir = checkFileName(dir);
            stringBuffer.append( File.separator + dir );
        }
        File file = new File(stringBuffer.toString());
        if(!file.exists()){
            file.mkdirs();
        }

        if(RStringUtils.isNotEmpty(fileName)){
            stringBuffer.append(File.separator + fileName);
        }
        busiRoot = stringBuffer.toString();
        return busiRoot;
    }


    public File file(){
        AppCode.A00305.assertHasTrue(RStringUtils.isNotEmpty(fileName));
        return new File(busiRoot + File.separator +  fileName);
    }


    public static void main(String[] args) {
        System.out.println(FilePath.build().ofPath("dir1", "dir2").path());
        System.out.println(FilePath.build().ofPath("dir1", "dir2").ofFileName("1.txt").path());
        System.out.println(FilePath.build().ofPath("dir1", "dir2").ofFileName("1.txt").file());
    }

    public static String checkFileName(String fileName){
        fileName=fileName.replaceAll(FileRegexConstant.fileNamePattern,"");
        AppCode.A00162.assertHasTrue((fileName.length() < FileRegexConstant.fileNameMaxLength),"文件名长度不能超过255",fileName);
        /*if(fileName.length()>255){
            return fileName.substring(0,250);
        }*/
        return fileName;
    }

    public static class TestFilePathBusi implements IFilePathBusi {

        /**
         * 测试使用的临时文件的位置
         * @return
         */
        @Override
        public String busiPath() {
            return "\\test";
        }
    }

}
