package cn.cc.dawn.demo.servlet;


import com.google.common.io.ByteStreams;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void t1(){
        String path = "E:/项目/boc/临时/sj-健康监测二维码.jpg";
        try {
            File file = new File(path);
            System.out.println(file.length());
            System.out.println(ByteStreams.toByteArray(FileUtils.openInputStream(file)).length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
