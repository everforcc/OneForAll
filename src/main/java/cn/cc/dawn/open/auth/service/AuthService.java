package cn.cc.dawn.open.auth.service;

import cn.cc.dawn.open.auth.cache.CustomerUserCache;
import cn.cc.dawn.open.auth.dao.CustomUserDaoMapper;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.open.auth.vo.CustomUserLoginVO;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.exception.AppCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class AuthService {

    @Resource
    CustomUserDaoMapper customUserDao;

    @Cacheable(cacheNames = CustomerUserCache.CUSTOMERUSER_TOKEN,key = "#username")
    public String authToken(String username, CustomUserLoginVO customUserLoginVO){
        log.info("用户: " + customUserLoginVO);
        CustomUser customUser = customUserDao.selectUser(username,customUserLoginVO.getPassword());
        log.info("查询返回数据: " + customUser);

        AppCode.A00101.assertHasTrue(RObjectsUtils.nonNull(customUser));

        return customUser.token();
    }

    public String check(@Valid CustomUser customUser){
        log.info("用户: 数据校验");
        return customUser.token();
    }

}
