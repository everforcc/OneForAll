/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 13:02
 * Copyright
 */

package cn.cc.dawn.business.game.drawcard.service.impl;

import cn.cc.dawn.business.game.drawcard.constant.CardGameConstant;
import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;
import cn.cc.dawn.business.game.drawcard.dto.CardGameLog;
import cn.cc.dawn.business.game.drawcard.service.ICardGameConfigService;
import cn.cc.dawn.business.game.drawcard.service.ICardGameLogService;
import cn.cc.dawn.business.game.drawcard.service.ICardGameMenuService;
import cn.cc.dawn.business.game.drawcard.util.ProbabilityUtil;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.exception.AppCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardGameMenuServiceImpl implements ICardGameMenuService {

    @Autowired
    ICardGameConfigService iCardGameConfigService;
    @Autowired
    ICardGameLogService cardGameLogService;

    @Override
    public List<CardGameConfigDto> listGame() {
        return iCardGameConfigService.listGameConfigDto();
    }

    /**
     * 公告
     * @return
     */
    @Override
    public String getNotice(int id,String gameid) {
        CardGameConfigDto configDto = iCardGameConfigService.selectGameConfigDto(gameid);
        return configDto.getNotice();
    }

    /**
     * 抽卡
     * @return
     */
    @Override
    @Transactional
    public String drawCard(int userid,String gameid) {
        String result;
        CardGameConfigDto configDto = iCardGameConfigService.selectGameConfigDto(gameid);
        AppCode.A01300.assertHasTrue(RObjectsUtils.nonNull(configDto));

        Double baseProbability = configDto.getBaseProbability();
        Double randomDouble = ProbabilityUtil.randomDouble();

        AppCode.A01301.assertHasTrue(baseProbability > 0);

        CardGameLog cardGameLog = new CardGameLog();
        cardGameLog.setGameid(gameid);
        cardGameLog.setUserid(userid);
        cardGameLog.setGameProbability(baseProbability);
        cardGameLog.setUserProbability(randomDouble);

        if(baseProbability >= randomDouble){
            result = CardGameConstant.WIN;
        }else {
            result = CardGameConstant.LOSE;
        }
        cardGameLog.setResult(result);
        cardGameLogService.insert(cardGameLog,userid);
        return result;
    }
}
