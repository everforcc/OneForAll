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
