/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-20 20:27
 * Copyright
 */

package cn.cc.dawn.demo.data.mybaties.controller;

import cn.cc.dawn.demo.data.mybaties.dao.MybatisDao;
import cn.cc.dawn.demo.data.mybaties.dto.MybatisUser;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/demo/data/mybatis")
public class MybatisController {

    /**
     * 总结常用的mybatisxml的格式
     */
    @Resource
    MybatisDao mybatisDao;

    /**
     * foreach
     */
    @GetMapping("/foreach")
    public ResultE<String> selectForeach() {
        return new ResultE<String>().execute(e -> e.setSuccess(mybatisDao.pagesIn(Arrays.asList(18, 20))));
    }

    @GetMapping("/insertList")
    public ResultE<Integer> insertList() {
        MybatisUser mybatisUser_1 = new MybatisUser(101L, "list-1", 1, "", 1, 1, new Date(), new Date());
        MybatisUser mybatisUser_2 = new MybatisUser(102L, "list-2", 1, "", 1, 1, new Date(), new Date());
        MybatisUser mybatisUser_3 = new MybatisUser(103L, "list-3", 1, "", 1, 1, new Date(), new Date());
        return new ResultE<Integer>().execute(e -> e.setSuccess(
                mybatisDao.insertList(Arrays.asList(mybatisUser_1, mybatisUser_2, mybatisUser_3)
                )));

    }

}
