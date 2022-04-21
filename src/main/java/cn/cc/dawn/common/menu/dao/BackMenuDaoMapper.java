package cn.cc.dawn.common.menu.dao;

import cn.cc.dawn.common.menu.dto.MenuDto;
import cn.cc.dawn.userinterface.ServiceAspect;

//@ServiceAspect
public interface BackMenuDaoMapper {

    void insert(MenuDto menuDto);

}
