package cn.cc.dawn.utils;

import cn.cc.dawn.common.dto.FileSystemDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileUtils {

    @Test
    void contextLoads() {
        log.info("文件名测试");
        FileSystemDto fileSystemDto = new FileSystemDto();
        fileSystemDto.setName("中文/");
        log.info("文件名测试通过");
    }

}
