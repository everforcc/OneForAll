/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 08:27
 * Copyright
 */

package cn.cc.dawn.business.game.drawcard.controller;

import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;
import cn.cc.dawn.business.game.drawcard.dto.CardGameLog;
import cn.cc.dawn.business.game.drawcard.service.ICardGameLogService;
import cn.cc.dawn.business.game.drawcard.service.ICardGameMenuService;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/b/game/drawcard")
@RestController
public class CardGameController {

    @Autowired
    ICardGameMenuService cardGameMenuService;
    @Autowired
    ICardGameLogService cardGameLogService;

    @GetMapping("/findGame")
    public ResultE<CardGameConfigDto> findtGame(@AuthenticationPrincipal CustomUser customUser){
        return new ResultE<CardGameConfigDto>().execute(e ->
                e.setSuccess(cardGameMenuService.listGame())
        );
    }

    @GetMapping("/play/{gameid}")
    public ResultE<String> play(@AuthenticationPrincipal CustomUser customUser,@PathVariable String gameid){
        return new ResultE<String>().execute(e ->
                e.setSuccess(cardGameMenuService.drawCard(customUser.getId(),gameid))
        );
    }

    @GetMapping("/playrecoder/{gameid}")
    public ResultE<CardGameLog> playrecoder(@AuthenticationPrincipal CustomUser customUser, @PathVariable String gameid){
        return new ResultE<CardGameLog>().execute(e ->
                e.setSuccess(cardGameLogService.selectCardGameLog(customUser.getId(),gameid))
        );
    }

    @GetMapping("/charge/{gameid}")
    public ResultE<String> charge(@AuthenticationPrincipal CustomUser customUser,@PathVariable String gameid){
        return new ResultE<String>().execute(e ->
                e.setSuccess(customUser.getUsername() + ": 充钱提高抽卡概率")
        );
    }

}
