/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 11:36
 * Copyright
 */

package cn.cc.dawn.demo.param;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/d/param")
@RestController
public class AllParamController {

    @GetMapping("/rest/{param}")
    public String rest(@PathVariable("param") String param){
        log.info("rest参数 param: {}", param);
        return "123";
    }

    @GetMapping("/reqParam")
    public String reqParam(@RequestParam("param") String param){
        log.info("reqParam参数 param: {}", param);
        return "123";
    }

    @GetMapping("/restWith")
    public String restWith(@RequestParam("param") String param){
        log.info("reqParam参数 param: {}", param);
        return "123";
    }

    @PostMapping("/postJson")
    public String postJson(@RequestBody String json){
        log.info("postJson参数 param: {}", json);
        return "123";
    }


    /*

    说明： 这种传参方式，pom中需要配置jackson-databind

<!--jackson-->

<dependency>

      <groupId>com.fasterxml.jackson.core</groupId>

      <artifactId>jackson-databind</artifactId>

      <version>2.9.0</version>

</dependency>

和spring配置文件增加注解驱动

<mvc:annotation-driven>

</mvc:annotation-driven>
-----------------------------------
©著作权归作者所有：来自51CTO博客作者阿123斯蒂芬的原创作品，请联系作者获取转载授权，否则将追究法律责任
spring mvc controller三种传参方式
https://blog.51cto.com/u_14543873/4235889

    */
    @PostMapping("/postObj")
    public String postObj(@RequestBody Object obj){
        log.info("postObj参数 param: {}", obj.toString());
        return "123";
    }

    /**
     * form表单
     * @param obj
     * @return
     */
    @PostMapping("/form")
    public String form(@RequestBody Object obj){
        log.info("postObj参数 param: {}", obj.toString());
        return "123";
    }

    /**
     * 剩余的书签栏阅读
     */

}
