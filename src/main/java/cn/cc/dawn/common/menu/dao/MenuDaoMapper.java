package cn.cc.dawn.common.menu.dao;

import cn.cc.dawn.common.menu.vo.MenuPicVo;
import cn.cc.dawn.common.menu.vo.MenuVo;

import java.util.List;

public interface MenuDaoMapper {

    List<MenuVo> selectMenu(int type,String parentuuid);

    List<MenuVo> selectPicMenu(int type,String parentuuid);

    List<MenuPicVo> selectPicDataMenu(int type, String parentuuid);
}
