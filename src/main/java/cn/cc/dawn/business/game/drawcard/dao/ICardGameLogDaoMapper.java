/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 14:01
 * Copyright
 */

package cn.cc.dawn.business.game.drawcard.dao;

import cn.cc.dawn.business.game.drawcard.dto.CardGameLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICardGameLogDaoMapper {

    int insert(CardGameLog cardGameLog);

    List<CardGameLog> listUserLog(@Param("userid") int userid,@Param("gameid")String gameid);

}
