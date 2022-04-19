package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.constant.DateFormatConstant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 常见的文件操作
 * 1. 复制
 * 2. 移动
 * 3. 改名
 */
public interface IFileHandle {

    void copy();

    void move();

    void rename();

    void write(String path,String content) throws IOException;

    String read(String path) throws IOException;

    /**
     * 设置文件权限,根据系统区分
     */

    /**
     * 创建时间
     * @param file
     * @param pattern
     * @return
     */
    default String creationTime(File file,String pattern){
        BasicFileAttributes attr = getCreationTime(file);
        Instant instant_0 = attr.creationTime().toInstant();
        String format_0 = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(instant_0);
        return format_0;
    };

    /**
     * 最后一次修改时间
     * @param file
     * @return
     */
    default String lastModifiedTime(File file){
        return lastModifiedTime(file, DateFormatConstant.yyyy_MM_dd_HH_mm_ss);
    };

    /**
     * 最后一次修改时间，自定义格式
     * @param file
     * @param pattern
     * @return
     */
    default String lastModifiedTime(File file,String pattern){
        BasicFileAttributes attr = getCreationTime(file);
        Instant instant_0 = attr.lastModifiedTime().toInstant();
        String format_0 = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(instant_0);
        return format_0;
    };

    /**
     * 最近访问的时间
     * @param file
     * @param pattern
     * @return
     */
    default String lastAccessTime(File file,String pattern){
        BasicFileAttributes attr = getCreationTime(file);
        Instant instant_0 = attr.lastAccessTime().toInstant();
        String format_0 = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(instant_0);
        return format_0;
    };

    /**
     * 文件时间对象
     * @param file
     * @return
     */
    static BasicFileAttributes getCreationTime(File file) {
        if (file == null) {
            return null;
        }
        BasicFileAttributes attr = null;
        try {
            Path path =  file.toPath();
            attr = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attr;
    }

}
