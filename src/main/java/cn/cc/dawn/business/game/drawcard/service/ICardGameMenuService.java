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

package cn.cc.dawn.business.game.drawcard.service;

import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;

import java.util.List;

public interface ICardGameMenuService {

    List<CardGameConfigDto> listGame();

    String getNotice(int id,String gameid);

    String drawCard(int userid,String gameid);

}
