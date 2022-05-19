package cn.cc.dawn.business.game.drawcard.dao;

import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICardGameConfigDaoMapper {

    List<CardGameConfigDto> listGameConfig();

    CardGameConfigDto selectGameConfig(@Param("uuid") String uuid);

    int insert(CardGameConfigDto configDto);

}
