/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 11:36
 */

package cn.cc.dawn.demo.param.controller;

import cn.cc.dawn.demo.param.dto.FieldEnum;
import cn.cc.dawn.demo.param.dto.ParamDto;
import cn.cc.dawn.utils.enums.impl.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RequestMapping(value = "/demo/param")
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

    @GetMapping("/reqEnumParam")
    public String reqEnumParam(@RequestParam("param") StatusEnum param){
        log.info("reqParam参数 param: {}", param.getCode());
        return "123";
    }

    @GetMapping("/reqEnumParamKey")
    public String reqEnumParamKey(@RequestParam(value = "param", required = false) Integer param){
        log.info("reqParam参数 param");
        if (Objects.nonNull(param)){
            log.info("reqParam参数 param: {}", FieldEnum.getEnum(param));
        }
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
    @PutMapping("/postObj")
    public ParamDto postObj(@RequestBody final ParamDto paramDto){
        log.info("postObj参数 param: {}", paramDto.toString());
        return paramDto;
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
