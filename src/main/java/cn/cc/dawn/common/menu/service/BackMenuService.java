package cn.cc.dawn.common.menu.service;

import cn.cc.dawn.common.menu.dao.BackMenuDaoMapper;
import cn.cc.dawn.common.menu.dto.MenuDto;
import cn.cc.dawn.common.menu.vo.MenuVo;
import cn.cc.dawn.utils.annotation.ServiceAspect;
import cn.cc.dawn.utils.i.valited.ISave;
import cn.cc.dawn.utils.i.valited.IUpdate;
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
public class BackMenuService {

    @Resource
    BackMenuDaoMapper backMenuDaoMapper;

    @Transactional
    @Validated({ISave.class})
    public MenuVo insert(@Valid MenuDto menuDto,int userid,MenuVo menuVo){

        menuDto.setParentuuid(menuVo.getParentuuid());
        menuDto.setParentid(0);
        menuDto.setName(menuVo.getName());
        menuDto.setType(menuVo.getType());
        backMenuDaoMapper.insert(menuDto);
        menuVo.setUuid(menuDto.getUuid());

        return menuVo;
    }

    @Validated({IUpdate.class})
    public void update(@Valid MenuVo menuVo){

    }

}
