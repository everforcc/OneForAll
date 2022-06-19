/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-17 17:16
 */

package cn.cc.dawn.demo.param.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/demo/param")
@RestController
public class SameReqMapController {

    @GetMapping("/difChild")
    public String difChild(@RequestParam("param") String param){
        log.info("reqParam参数 param: {}", param);
        return "123";
    }

}
