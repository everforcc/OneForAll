package cn.cc.dawn.business.game.drawcard.service;

import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;

import java.util.List;

public interface ICardGameConfigService {

    CardGameConfigDto selectGameConfigDto(String uuid);

    List<CardGameConfigDto> listGameConfigDto();

    int insert(CardGameConfigDto cardGameConfigDto);

}
