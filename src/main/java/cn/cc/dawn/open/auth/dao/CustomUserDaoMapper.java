package cn.cc.dawn.open.auth.dao;

import cn.cc.dawn.open.auth.dto.CustomUser;

public interface CustomUserDaoMapper {

    CustomUser selectUser(String username,String password);

}
