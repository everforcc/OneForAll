package cn.cc.dawn.business.game.drawcard.dao;

import cn.cc.dawn.business.game.drawcard.dto.CardGameLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICardGameLogDaoMapper {

    int insert(CardGameLog cardGameLog);

    List<CardGameLog> listUserLog(@Param("userid") int userid,@Param("gameid")String gameid);

}
