package cn.cc.dawn.utils.file;

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

}
