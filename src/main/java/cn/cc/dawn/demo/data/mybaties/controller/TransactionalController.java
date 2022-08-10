/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-10 11:54
 * Copyright
 */

package cn.cc.dawn.demo.data.mybaties.controller;

import cn.cc.dawn.demo.data.mybaties.service.TransactionalService;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/open/data/tran")
public class TransactionalController {

    @Resource
    TransactionalService transactionalService;

    /**
     * 不入库
     *
     * @return 插入结果
     */
    @GetMapping("/withOutTry")
    public ResultE<Void> withOutTry() {
        return new ResultE<Void>().call(
                () -> transactionalService.withOutTry()
        );
    }

    /**
     * 不入库
     *
     * @return 插入结果
     */
    @GetMapping("/withTry")
    public ResultE<Void> withTry() {
        return new ResultE<Void>().call(
                () -> transactionalService.withTry()
        );
    }

    /**
     * 入库
     *
     * @return 插入结果
     */
    @GetMapping("/withOutException")
    public ResultE<Void> withOutException() {
        return new ResultE<Void>().call(
                () -> transactionalService.withOutException()
        );
    }

}
