/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-10 11:44
 * Copyright
 */

package cn.cc.dawn.demo.data.mybaties.service;

import cn.cc.dawn.demo.data.mybaties.dao.MybatisDao;
import cn.cc.dawn.demo.data.mybaties.dto.MybatisUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * 测试事务的各种情况
 */
@Slf4j
@Service
public class TransactionalService {

    @Resource
    MybatisDao mybatisDao;

    /**
     * 不捕获异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void withOutTry() {
        log.info("事务测试 withOutTry...");
        MybatisUser mybatisUser_4 = new MybatisUser(104L, "list-4", 4, "", 4, 4, new Date(), new Date());
        mybatisDao.insertList(Arrays.asList(mybatisUser_4));
        throw new RuntimeException("出现异常，不捕获");
    }

    /**
     * 捕获异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void withTry() {
        try {
            log.info("事务测试 withTry...");
            MybatisUser mybatisUser_5 = new MybatisUser(105L, "list-5", 5, "", 5, 5, new Date(), new Date());
            mybatisDao.insertList(Arrays.asList(mybatisUser_5));
            throw new RuntimeException("出现异常，但是捕获");
        } catch (RuntimeException e){
            log.error("捕获到了RuntimeException异常: {}", e.getMessage(), e);
        }catch (Exception e) {
            log.error("捕获到了Exception异常: {}", e.getMessage(), e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void withOutException() {
        log.info("事务测试 withOutException...");
        MybatisUser mybatisUser_6 = new MybatisUser(106L, "list-6", 6, "", 6, 6, new Date(), new Date());
        mybatisDao.insertList(Collections.singletonList(mybatisUser_6));
        log.info("事务测试 withOutException...");
    }

}
