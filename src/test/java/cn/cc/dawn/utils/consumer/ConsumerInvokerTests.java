/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-04 10:51
 * Copyright
 */

package cn.cc.dawn.utils.consumer;

import cn.cc.dawn.demo.param.dto.FieldEnum;
import cn.cc.dawn.demo.param.dto.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootTest
public class ConsumerInvokerTests {

    @Resource
    ConsumerInvoker<ParamDto> consumerInvoker;

    //@Test
    public void asyncInvokeTests() {
        log.info("开始");
        ParamDto paramDto = new ParamDto();
        CompletableFuture<ParamDto> paramDtoCompletableFuture = consumerInvoker.asyncInvoke(paramDto,
                r -> r.setName("consumer_name"),
                r -> r.setStatusEnum(FieldEnum.EFFECT),
                r -> r.setDescription("最后的描述"));
        log.info("toString: " + paramDto);
        paramDtoCompletableFuture.whenComplete((d, t) -> d.setDescription("完成后修改描述"));
        log.info("toString: " + paramDto);
        log.info("结束");
    }

}

