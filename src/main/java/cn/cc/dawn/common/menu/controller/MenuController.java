package cn.cc.dawn.common.menu.controller;

import cn.cc.dawn.common.menu.service.MenuService;
import cn.cc.dawn.common.menu.vo.MenuPicVo;
import cn.cc.dawn.common.menu.vo.MenuVo;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/c/menu")
public class MenuController {

    /**
     * 这个controller不能 新增/删除/修改
     * 仅仅用户查询使用
     */

    /**
     * 1. 根目选择，返回目录
     */

    @Autowired
    MenuService menuService;


    @GetMapping("/test")
    public void menu(@AuthenticationPrincipal CustomUser customUser){
        log.info(customUser.getUsername());
    }

    /**
     * 顶级目录
     * @param customUser
     * @return
     */
    @GetMapping("/menu")
    public ResultE<MenuVo> topMenu(@AuthenticationPrincipal CustomUser customUser){
        // 进入页面返回顶级目录
        return new ResultE<MenuVo>().execute(e ->
                e.setSuccess(menuService.pageTop(customUser))
        );
    }

    /**
     * 最好不要超过两级，否则使用起来非常麻烦
     * @param customUser
     * @param parentuuid
     * @return
     */
    @GetMapping("/menu/{type}/{parentuuid}")
    public ResultE<MenuVo> typeMenu(@AuthenticationPrincipal CustomUser customUser,@PathVariable String type,@PathVariable String parentuuid){
        // 进入页面返回顶级目录
        return new ResultE<MenuVo>().execute(e ->
                e.setSuccess(menuService.pageType(customUser,type,parentuuid))
        );
    }


    @GetMapping("/picmenu/{type}/{parentuuid}")
    public ResultE<MenuVo> picmenu(@AuthenticationPrincipal CustomUser customUser,@PathVariable String type,@PathVariable String parentuuid){
        return new ResultE<MenuVo>().execute(e ->
                e.setSuccess(menuService.pagePicMenu(customUser,type,parentuuid))
        );
    }

    @GetMapping("/picdata/{type}/{parentuuid}")
    public ResultE<MenuPicVo> picdata(@AuthenticationPrincipal CustomUser customUser, @PathVariable String type, @PathVariable String parentuuid){
        return new ResultE<MenuPicVo>().execute(e ->
                e.setSuccess(menuService.pagePicDataMenu(customUser,type,parentuuid))
        );
    }

}
