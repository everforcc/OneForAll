package cn.cc.dawn.common.menu.service;

import cn.cc.dawn.common.menu.dao.BackMenuPicDataDaoMapper;
import cn.cc.dawn.common.menu.dto.MenuPicDataDto;
import cn.cc.dawn.common.menu.vo.MenuPicVo;
import cn.cc.dawn.common.menu.vo.MenuVo;
import cn.cc.dawn.userinterface.ServiceAspect;
import cn.cc.dawn.utils.inter.valited.ISave;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

@Slf4j
@Service
@Validated
@ServiceAspect
public class BackMenuPicDataService {

    @Resource
    BackMenuPicDataDaoMapper backMenuPicDataDaoMapper;

    /**
     * 向指定目录上传图片
     */
    @Transactional
    @Validated({ISave.class})
    public MenuPicVo insert(MenuPicDataDto menuPicDataDto, int userid, MenuPicVo menuPicVo){
        menuPicDataDto.setPicuuid(menuPicVo.getPicuuid());
        menuPicDataDto.setParentuuid(menuPicVo.getParentuuid());
        backMenuPicDataDaoMapper.insert(menuPicDataDto);
        return menuPicVo;
    }

}
