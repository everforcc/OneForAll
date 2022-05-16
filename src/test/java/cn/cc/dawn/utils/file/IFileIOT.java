package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class IFileIOT {

    private static IFile apacheiFile = new FileApacheUtils();

    @Test
    public void write(){
        try {
            apacheiFile.write(FilePath.build().ofPath("/test/novel").ofFileName("test.txt").path(),"123\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){
        try {
            log.info(apacheiFile.readTXT(FilePath.build().ofPath("/test/novel").ofFileName("test.txt").path()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}