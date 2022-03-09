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

public interface IFile {

    /**
     * 常见的文件操作
     * 1. 复制
     * 2. 移动
     * 3. 改名
     */

    void copy();

    void move();

    void rename();

    void write(String path,String content) throws IOException;

    String read(String path) throws IOException;

    default String creationTime(File file,String pattern){
        BasicFileAttributes attr = getCreationTime(file,pattern);
        Instant instant_0 = attr.creationTime().toInstant();
        String format_0 = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(instant_0);
        return format_0;
    };

    default String lastModifiedTime(File file){
        return lastModifiedTime(file, DateFormatConstant.yyyy_MM_dd_HH_mm_ss);
    };

    default String lastModifiedTime(File file,String pattern){
        BasicFileAttributes attr = getCreationTime(file,pattern);
        Instant instant_0 = attr.lastModifiedTime().toInstant();
        String format_0 = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(instant_0);
        return format_0;
    };

    default String lastAccessTime(File file,String pattern){
        BasicFileAttributes attr = getCreationTime(file,pattern);
        Instant instant_0 = attr.lastAccessTime().toInstant();
        String format_0 = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(instant_0);
        return format_0;
    };

    static BasicFileAttributes getCreationTime(File file,String pattern) {
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
