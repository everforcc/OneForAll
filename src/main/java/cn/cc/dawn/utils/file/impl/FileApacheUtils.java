package cn.cc.dawn.utils.file.impl;

import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.file.IFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileApacheUtils implements IFile {
    @Override
    public void copy() {
        //FileUtils.copyFile();
    }

    @Override
    public void move() {

    }

    @Override
    public void rename() {

    }

    @Override
    public void write(String path, String content) throws IOException {
        FileUtils.writeStringToFile(new File(path),content, CharsetsEnum.UTF_8.charset, BooleanEnum.TRUE.flag);
    }

    @Override
    public String read(String path) throws IOException {

        return FileUtils.readFileToString(new File(path), CharsetsEnum.UTF_8.charset);
    }
}
