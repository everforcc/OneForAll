package cn.cc.dawn.business.game.drawcard.service;

import cn.cc.dawn.business.game.drawcard.dto.CardGameLog;

import java.util.List;

public interface ICardGameLogService {

    List<CardGameLog> selectCardGameLog(int userid, String gameid);

    int insert(CardGameLog cardGameLog, int userid);

}
