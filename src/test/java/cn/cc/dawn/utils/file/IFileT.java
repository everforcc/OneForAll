package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class IFileT {

    private static IFile apacheiFile = new FileApacheUtils();

    @Test
    public void write(){
        try {
            apacheiFile.write("E:\\filesystem\\test\\novel\\test.txt","123\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){
        try {
            log.info(apacheiFile.read("E:\\filesystem\\test\\novel\\test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
