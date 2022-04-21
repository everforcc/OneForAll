package cn.cc.dawn.common.menu.controller;

import cn.cc.dawn.common.menu.dto.MenuDto;
import cn.cc.dawn.common.menu.dto.MenuPicDataDto;
import cn.cc.dawn.common.menu.dto.MenuPicDto;
import cn.cc.dawn.common.menu.service.BackMenuPicDataService;
import cn.cc.dawn.common.menu.service.BackMenuPicService;
import cn.cc.dawn.common.menu.vo.MenuPicVo;
import cn.cc.dawn.common.menu.vo.MenuVo;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/c/backmenupic")
public class BackMenuPicController {

    /**
     * 文件上传的业务逻辑
     */

    @Autowired
    BackMenuPicService backMenuPicService;

    @Autowired
    BackMenuPicDataService backMenuPicDataService;

    /**
     * 上传文件后， 这边接受uuid，然后入库
     */
    @PostMapping("/save")
    public ResultE<MenuVo> save(@AuthenticationPrincipal CustomUser customUser, @RequestBody String body) {
//        return new ResultE<Void>().call(() -> {
//                    backMenuPicService.insert(new MenuPicDto(), customUser.getId(), JSONObject.parseObject(body, MenuVo.class));
//                }
//        );
        return new ResultE<MenuVo>().execute(e->
                e.setSuccess(backMenuPicService.insert(new MenuPicDto(), customUser.getId(), JSONObject.parseObject(body, MenuVo.class)))
        );
    }

    @PostMapping("/add")
    public ResultE<MenuPicVo> addPic(@AuthenticationPrincipal CustomUser customUser, @RequestBody String body){
        return new ResultE<MenuPicVo>().execute(e->
                e.setSuccess(backMenuPicDataService.insert(new MenuPicDataDto(),customUser.getId(),JSONObject.parseObject(body, MenuPicVo.class)))
        );
    }



}
