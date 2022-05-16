package cn.cc.dawn.open.auth.dao;

import cn.cc.dawn.open.auth.dto.CustomUser;
import org.apache.ibatis.annotations.Param;

public interface CustomUserDaoMapper {

    CustomUser selectUser(@Param("username") String username,@Param("password") String password);

}
