package cn.cc.dawn.common.menu.service;

import cn.cc.dawn.common.menu.dao.BackMenuPicDaoMapper;
import cn.cc.dawn.common.menu.dto.MenuPicDto;
import cn.cc.dawn.common.menu.vo.MenuPicVo;
import cn.cc.dawn.common.menu.vo.MenuVo;
import cn.cc.dawn.utils.annotation.ServiceAspect;
import cn.cc.dawn.utils.i.valited.ISave;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@Service
@Validated
@ServiceAspect
public class BackMenuPicService {

    @Resource
    BackMenuPicDaoMapper backMenuPicDaoMapper;

    /**
     * 管理图片目录
     */
    @Transactional
    @Validated({ISave.class})
    public MenuVo insert(@Valid MenuPicDto menuPicDto, int userid, MenuVo menuVo){
        menuPicDto.setParentuuid(menuVo.getParentuuid());
        menuPicDto.setName(menuVo.getName());
        menuPicDto.setType(menuVo.getType());
        backMenuPicDaoMapper.insert(menuPicDto);

        menuVo.setUuid(menuPicDto.getUuid());
        return menuVo;
    }

    public void picMenu(@Valid MenuPicDto menuPicDto,int userid,MenuVo menuVo){

    }

    public void page(@Valid MenuPicDto menuPicDto, int userid, MenuPicVo menuPicVo){

    }


}
