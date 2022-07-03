package cn.cc.dawn.utils.file;


import com.google.common.io.ByteStreams;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FilePathTests {

    @Test
    public void t1(){
        try {
            File file = FilePath.build().ofPath("项目/boc/临时").ofFileName("sj-健康监测二维码.jpg").file();
            System.out.println(file.length());
            System.out.println(ByteStreams.toByteArray(FileUtils.openInputStream(file)).length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
