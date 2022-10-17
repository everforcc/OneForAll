package cn.cc.dawn.demo.xss.controller;

import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/open/xss")
@RestController
public class XSSController {

    /**
     * 返回数据
     *
     * @return
     */
    @GetMapping("/str")
    public ResultE<String> testExceptionReturn(@RequestParam("str") String str) {
        return new ResultE<String>().execute(e -> {
            log.info("str: {}", str);
            e.setSuccess(str);
        });
    }

}
