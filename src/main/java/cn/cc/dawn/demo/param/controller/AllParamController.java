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

package cn.cc.dawn.demo.param.controller;

import cn.cc.dawn.demo.param.dto.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping(value = "/d/param")
@RestController
public class AllParamController {

    /**
     * 1. queryString
     * @param param
     * @return
     */
    @GetMapping("/reqParam")
    public String reqParam(@RequestParam("param") String param){
        log.info("reqParam参数 param: {}", param);
        return "123";
    }

    /**
     * 2. dto
     * @param paramDto
     * @return
     */
    @GetMapping("/reqDto")
    public String reqDto(ParamDto paramDto){
        log.info("reqParam参数 param: {}", paramDto.toString());
        return "123";
    }

    /**
     * 3. rest
     * @param param
     * @return
     */
    @GetMapping("/rest/{param}")
    public String rest(@PathVariable("param") String param){
        log.info("rest参数 param: {}", param);
        return "123";
    }

    /**
     * 4. 测试rest和param一起用
     * @param param
     * @return
     */
    @GetMapping("/restWith/{rest}")
    public String restWith(@PathVariable("rest") String rest, @RequestParam("param") String param){
        log.info("reqParam参数 rest: {}", rest);
        log.info("reqParam参数 param: {}", param);
        return "123";
    }

    /**
     * 5. post-string
     * @param json
     * @return
     */
    @PostMapping("/postJson")
    public String postJson(@RequestBody String json){
        log.info("postJson参数 param: {}", json);
        return "123";
    }

    /**
     * 6. post-dto
     * 此处注意前端请求的时候要设置请求头
     * Content-Type: application/json
     * @param paramDto
     * @return
     */
    @PostMapping("/postObj")
    public String postObj(@RequestBody final ParamDto paramDto){
        log.info("postObj参数 param: {}", paramDto.toString());
        return "123";
    }

    /**
     * 7. post-file
     * @param file
     * @return
     */
    @PostMapping("/postFile")
    public String postFile(@RequestParam("field-name") MultipartFile file){
        log.info("postFile参数 getSize: {}", file.getSize());
        log.info("postFile参数 getName: {}", file.getName());
        log.info("postFile参数 getOriginalFilename: {}", file.getOriginalFilename());
        return "123";
    }

    /**
     * . postform表单
     * @param obj
     * @return
     */
    @PostMapping("/postForm")
    public String postForm(@RequestBody Object obj){
        log.info("postForm参数 param: {}", obj.toString());
        return "123";
    }

    /**
     * 剩余的书签栏阅读
     */

}
