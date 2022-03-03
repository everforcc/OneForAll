package cn.cc.dawn.utils.file;

import java.io.IOException;

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

}
