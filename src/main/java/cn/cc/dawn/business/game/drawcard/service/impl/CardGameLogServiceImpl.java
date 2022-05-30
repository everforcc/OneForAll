/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 14:11
 * Copyright
 */

package cn.cc.dawn.business.game.drawcard.service.impl;

import cn.cc.dawn.business.game.drawcard.dao.ICardGameLogDaoMapper;
import cn.cc.dawn.business.game.drawcard.dto.CardGameLog;
import cn.cc.dawn.business.game.drawcard.service.ICardGameLogService;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.annotation.ServiceAspect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@ServiceAspect
@Service
public class CardGameLogServiceImpl implements ICardGameLogService {

    @Resource
    ICardGameLogDaoMapper cardGameLogDaoMapper;

    @Override
    public List<CardGameLog> selectCardGameLog(int userid,String gameid) {
        return cardGameLogDaoMapper.listUserLog(userid,gameid);
    }

    @Override
    public int insert(CardGameLog cardGameLog, int userid) {
        AppCode.A00201.assertHasInsert(cardGameLogDaoMapper.insert(cardGameLog));
        return 1;
    }
}
