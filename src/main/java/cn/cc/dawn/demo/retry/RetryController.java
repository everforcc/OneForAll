/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-29 15:00
 * Copyright
 */

package cn.cc.dawn.demo.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常重试
 */
@Slf4j
@RequestMapping("/demo/retry")
@RestController
public class RetryController {

    @GetMapping("/retry")
    public String retry(){
        retryException();
        return "123";
    }

    /**
     * 异常重试参数
     */
    @Retryable(value = RuntimeException.class, maxAttempts = 5)
    public void retryException(){
        log.info("错误重试");
        throw new RuntimeException("错误重试");
    }

}
