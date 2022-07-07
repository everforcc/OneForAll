package cn.cc.dawn.demo.function;

import cn.cc.dawn.demo.function.dto.ValidatedDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ValidatedDtoTests {

    @Test
    void contextLoads() {
        log.info("文件名测试");
        ValidatedDto fileSystemDto = new ValidatedDto();
        fileSystemDto.setFileName("中文/");
        log.info("文件名测试通过");
    }

}
