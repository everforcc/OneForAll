/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 08:35
 * Copyright
 */

package cn.cc.dawn.business.game.drawcard.controller;

import cn.cc.dawn.business.game.drawcard.dto.CardGameConfigDto;
import cn.cc.dawn.business.game.drawcard.service.ICardGameConfigService;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/ccall/game/drawcard")
@RestController
public class CardGameConfigController {

    @Autowired
    ICardGameConfigService cardGameConfigService;

    @GetMapping("/allGame")
    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    public ResultE<String> allGame(@AuthenticationPrincipal CustomUser customUser){
        return new ResultE<String>().execute(e ->
                e.setSuccess(customUser.getUsername() + ": 查询所有的卡池")
        );
    }

    @GetMapping("/modifyGame/{gameid}")
    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    public ResultE<String> modifyGame(@AuthenticationPrincipal CustomUser customUser,@PathVariable String gameid){
        return new ResultE<String>().execute(e ->
                e.setSuccess(customUser.getUsername() + ": 查询所有的卡池")
        );
    }

    @PostMapping("/putGame")
    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    public ResultE<Integer> putGame(@AuthenticationPrincipal CustomUser customUser,@RequestBody String json){
        return new ResultE<Integer>().execute(e ->
                e.setSuccess(cardGameConfigService.insert(JSONObject.parseObject(json, CardGameConfigDto.class)))
        );
    }

}
