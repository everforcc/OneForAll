/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 13:00
 * Copyright
 */

package cn.cc.dawn.business.game.drawcard.service.impl;

import cn.cc.dawn.business.game.drawcard.dao.ICardGameConfigDaoMapper;
import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;
import cn.cc.dawn.business.game.drawcard.service.ICardGameConfigService;
import cn.cc.dawn.utils.annotation.ServiceAspect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@ServiceAspect
@Service
public class CardGameConfigServiceImpl implements ICardGameConfigService {
    private static List<CardGameConfigDto> cardGameConfigDtoList = new ArrayList<>();
    private static CardGameConfigDto configDto_1 = new CardGameConfigDto("描述1","公告1","游戏名1",0.16d);
    private static CardGameConfigDto configDto_2 = new CardGameConfigDto("描述2","公告2","游戏名2",0.16d);
    static {
        configDto_1.setId(1);
        configDto_2.setId(2);
        cardGameConfigDtoList.add(configDto_1);
        cardGameConfigDtoList.add(configDto_2);
    }

    @Resource
    ICardGameConfigDaoMapper iCardGameConfigDaoMapper;

    /**
     * 根据uuid查出游戏
     * @param uuid
     * @return
     */
    // 加入缓存
    @Override
    public CardGameConfigDto selectGameConfigDto(String uuid) {
        // 查询已存在的活动
        //return configDto_1;
        return iCardGameConfigDaoMapper.selectGameConfig(uuid);
    }

    @Override
    public List<CardGameConfigDto> listGameConfigDto() {
        //return gameConfigDtoList;
        return iCardGameConfigDaoMapper.listGameConfig();
    }

    @Override
    public int insert(CardGameConfigDto cardGameConfigDto) {
        return iCardGameConfigDaoMapper.insert(cardGameConfigDto);
    }
}
