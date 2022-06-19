package cn.cc.dawn.business.game.drawcard.service;

import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;

import java.util.List;

public interface ICardGameMenuService {

    List<CardGameConfigDto> listGame();

    String getNotice(int id,String gameid);

    String drawCard(int userid,String gameid);

}
