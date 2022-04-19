package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import cn.cc.dawn.utils.file.path.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class IFileHandleT {

    private static IFileHandle apacheiFileHandle = new FileApacheUtils();

    @Test
    public void write(){
        try {
            apacheiFileHandle.write(FilePath.build().ofPath("/test/novel").ofFileName("test.txt").path(),"123\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){
        try {
            log.info(apacheiFileHandle.read(FilePath.build().ofPath("/test/novel").ofFileName("test.txt").path()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
