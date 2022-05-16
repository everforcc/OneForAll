package cn.cc.dawn.utils.file.impl;

import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.file.IFile;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
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

    @SneakyThrows
    @Override
    public void write(String path, String content){
        FileUtils.writeStringToFile(new File(path),content, CharsetsEnum.UTF_8.charset,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public void write(String path, String fileName, String content) {
        FileUtils.writeStringToFile(new File(path + File.separator + fileName),content, CharsetsEnum.UTF_8.charset,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public void write(String path, byte[] bytes){
        FileUtils.writeByteArrayToFile(new File(path),bytes,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public void write(String path, String fileName, byte[] bytes) {
        FileUtils.writeByteArrayToFile(new File(path + File.separator + fileName),bytes,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public String readTXT(String path){
        return FileUtils.readFileToString(new File(path),CharsetsEnum.UTF_8.charset);
    }

    @SneakyThrows
    @Override
    public byte[] readBytes(String path) {
        return FileUtils.readFileToByteArray(new File(path));
    }

    @Override
    public boolean exist(String path) {
        return new File(path).exists();
    }
}
