package cn.cc.dawn.common.menu.service;

import cn.cc.dawn.common.menu.dao.MenuDaoMapper;
import cn.cc.dawn.common.menu.vo.MenuPicVo;
import cn.cc.dawn.common.menu.vo.MenuVo;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.enums.impl.MenuEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class MenuService {

    @Resource
    MenuDaoMapper menuDaoMapper;


    /**
     * 查询顶级目录
     * @param customUser
     * @return
     */
    public List<MenuVo> pageTop(CustomUser customUser){
        // 此处需要权限控制

        List<MenuVo> menuVoList = menuDaoMapper.selectMenu(MenuEnum.ROOT.comment, "0");
        for (MenuVo menuVo : menuVoList) {
            log.info(menuVo.toString());
        }
        return menuVoList;
    }

    /**
     * 查询类型目录
     * @param customUser
     * @param parentuuid
     * @return
     */
    public List<MenuVo> pageType(CustomUser customUser,String type,String parentuuid){
        // 此处需要权限控制
        List<MenuVo> menuVoList = menuDaoMapper.selectMenu(MenuEnum.valueOf(type).comment,parentuuid);
        for (MenuVo menuVo : menuVoList) {
            log.info(menuVo.toString());
        }
        return menuVoList;
    }

    public List<MenuVo> pagePicMenu(CustomUser customUser,String type,String parentuuid){
        // 此处需要权限控制
        List<MenuVo> menuVoList = menuDaoMapper.selectPicMenu(MenuEnum.valueOf(type).comment,parentuuid);
        return menuVoList;
    }

    public List<MenuPicVo> pagePicDataMenu(CustomUser customUser,String type,String parentuuid){
        // 此处需要权限控制
        List<MenuPicVo> menuVoList = menuDaoMapper.selectPicDataMenu(MenuEnum.valueOf(type).comment,parentuuid);
        return menuVoList;
    }

}
