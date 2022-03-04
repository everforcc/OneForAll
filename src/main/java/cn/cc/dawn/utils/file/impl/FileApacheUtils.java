package cn.cc.dawn.utils.file.impl;

import cn.cc.dawn.utils.constant.CharsetsConstant;
import cn.cc.dawn.utils.constant.FlagConstant;
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
        FileUtils.writeStringToFile(new File(path),content, CharsetsConstant.UTF_8, FlagConstant.TRUE);
    }

    @Override
    public String read(String path) throws IOException {

        return FileUtils.readFileToString(new File(path), CharsetsConstant.UTF_8);
    }
}
