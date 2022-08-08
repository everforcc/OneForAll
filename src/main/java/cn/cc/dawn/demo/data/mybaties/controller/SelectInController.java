/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 18:08
 * Copyright
 */

package cn.cc.dawn.demo.data.mybaties.controller;

import cn.cc.dawn.demo.data.mybaties.dao.InDao;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/open/data/mybatis")
public class SelectInController {

    @Resource
    InDao inDao;

    /**
     * in 超过1000数据 查询示例脚本
     * @return
     */
    @GetMapping("/in")
    public ResultE<String> selectForeach() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            stringList.add("1335");
        }

        stringList.add("1336");
        return new ResultE<String>().execute(e -> e.setSuccess(inDao.whereIn(stringList)));
    }

}
