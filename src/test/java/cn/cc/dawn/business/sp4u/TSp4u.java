package cn.cc.dawn.business.sp4u;

import cn.cc.dawn.business.crawsp4u.service.Sp4uService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TSp4u {

    @Autowired
    Sp4uService sp4uService;

    @Test
    void contextLoads() {
        String url = "http://spring4u.info/forumdisplay.php?fid=124";
        sp4uService.flow(url);
    }

}
