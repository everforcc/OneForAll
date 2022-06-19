package cn.cc.dawn.utils.file;

import cn.cc.dawn.config.init.yml.APPConfiguration;
import cn.cc.dawn.utils.commons.io.JFileNameUtils;
import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.constant.FileRegexConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 用来处理持久化时文件路径的问题
 */
@Component
public class FilePath {

    // 获取文件根目录
    @Autowired
    APPConfiguration appConfiguration;

    /**
     * 测试文件存放目录
     */
    private static IFilePathBusi defaultBusiRoot = new TestFilePathBusi();
    /**
     * 测试文件 完整目录
     */
    private static String busiRoot;

    // 文件名
    private String fileName;
    // 文件夹名
    private String[] dirs;

    /**
     * 构造
     */
    private FilePath() {
    }

    private FilePath(boolean flag, IFilePathBusi iFilePathBusi) {
        this.busiRoot = appConfiguration.getFilepath() + iFilePathBusi.busiPath();
    }

    private FilePath(String filePath) {
        this.busiRoot = filePath;
    }

    /**
     * 1. 只能通过build实例化，默认的是test目录
     *
     * @return
     */
    public static FilePath build() {
        return new FilePath(BooleanEnum.TRUE.flag, defaultBusiRoot);
    }

    public static FilePath build(IFilePathBusi iFilePathBusi) {
        return new FilePath(BooleanEnum.TRUE.flag, iFilePathBusi);
    }

    /**
     * 此代码测试使用，尽量不要在系统中用
     * 如果用，必须保证代码逻辑严谨
     *
     * @param filePath
     * @return
     */
    public static FilePath build(String filePath) {
        return new FilePath(filePath);
    }

    /**
     * 2. of系列方法，返回的是当前对象
     * 传的值是目录
     * 用来传文件目录
     *
     * @param dirs
     * @return
     */
    public FilePath ofPath(String... dirs) {
        this.dirs = dirs;
        return this;
    }

    public FilePath ofFileName(String fileName) {
        this.fileName = checkFileName(fileName);
        return this;
    }

    public String getSuffix() {
        return getSuffix(false);
    }

    public String getSuffix(boolean offset) {
        return getSuffix(fileName, offset);
    }

    public static String getSuffix(String fileName) {
        return getSuffix(fileName, false);
    }

    public static String getSuffix(String fileName, boolean offset) {
        return fileName.replaceFirst(FileRegexConstant.suffix, (offset ? "." : ""));
    }

    /**
     * 将参数处理为文件路径
     * TODO 在这里处理公共的文件名/路径,异常
     *
     * @return
     */
    public String path() {
        StringBuffer stringBuffer = new StringBuffer(busiRoot);
        for (String dir : dirs) {
            dir = checkFileName(dir);
            stringBuffer.append(File.separator + dir);
        }
        File file = new File(stringBuffer.toString());
        if (!file.exists()) {
            file.mkdirs();
        }

        if (RStringUtils.isNotEmpty(fileName)) {
            fileName = checkFileName(fileName);
            stringBuffer.append(File.separator + fileName);
        }
        busiRoot = stringBuffer.toString();
        return busiRoot;
    }


    public File file() {
        AppCode.A00305.assertHasTrue(RStringUtils.isNotEmpty(fileName));
        return new File(busiRoot + File.separator + fileName);
    }


    public static void main(String[] args) {
        System.out.println(FilePath.build().ofPath("dir1", "dir2").path());
        System.out.println(FilePath.build().ofPath("dir1", "dir2").ofFileName("1.txt").path());
        System.out.println(FilePath.build().ofPath("dir1", "dir2").ofFileName("1.txt").file());
    }

    public static String checkFileName(String fileName) {
        return JFileNameUtils.checkFileName(fileName);
    }

    public static class TestFilePathBusi implements IFilePathBusi {

        /**
         * 测试使用的临时文件的位置
         *
         * @return
         */
        @Override
        public String busiPath() {
            return "\\test";
        }
    }

}
