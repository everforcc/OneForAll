package cn.cc.dawn.open.auth.service;

import cn.cc.dawn.open.auth.cache.CustomerUserCache;
import cn.cc.dawn.open.auth.dao.CustomUserDaoMapper;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.open.auth.vo.CustomUserLoginVO;
import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.exception.AppCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class AuthService {

    @Resource
    CustomUserDaoMapper customUserDao;

    @Cacheable(cacheNames = CustomerUserCache.CUSTOMERUSER_TOKEN,key = "#username")
    public String authToken(String username, CustomUserLoginVO customUserLoginVO){
        log.info("用户: " + customUserLoginVO);
        CustomUser customUser = customUserDao.selectUser(username,customUserLoginVO.getPassword());
        log.info("查询返回数据: " + customUser);

        AppCode.A00101.assertHasTrue(ObjectUtils.nonNull(customUser));

        return customUser.token();
    }

}
