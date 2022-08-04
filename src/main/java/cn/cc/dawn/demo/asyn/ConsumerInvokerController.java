/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-04 11:16
 * Copyright
 */

package cn.cc.dawn.demo.asyn;

import cn.cc.dawn.demo.param.dto.FieldEnum;
import cn.cc.dawn.demo.param.dto.ParamDto;
import cn.cc.dawn.utils.consumer.ConsumerInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * 测试异步同步操作
 */
@Slf4j
@RequestMapping("/open/asyn")
@RestController
public class ConsumerInvokerController {

    @Resource
    ConsumerInvoker<ParamDto> consumerInvoker;

    /**
     * 测试异步操作
     */
    @GetMapping("/consumer")
    public void asyncInvokeTests() {
        log.info("开始");
        ParamDto paramDto = new ParamDto();
        CompletableFuture<ParamDto> paramDtoCompletableFuture = consumerInvoker.asyncInvoke(paramDto,
                r -> r.setName("consumer_name"),
                r -> r.setStatusEnum(FieldEnum.EFFECT),
                r -> r.setDescription("最后的描述"));
        log.info("toString: " + paramDto);
        paramDtoCompletableFuture.whenComplete((d, t) -> log.info("whenComplete: {}", paramDto));
        log.info("toString: " + paramDto);
        log.info("结束");
    }

}
