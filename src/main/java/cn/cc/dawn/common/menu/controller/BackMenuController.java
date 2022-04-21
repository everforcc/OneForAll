package cn.cc.dawn.common.menu.controller;

import cn.cc.dawn.common.menu.dto.MenuDto;
import cn.cc.dawn.common.menu.service.BackMenuService;
import cn.cc.dawn.common.menu.vo.MenuVo;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/c/backmenu")
public class BackMenuController {

    /**
     * 设置权限后提供给后台使用
     * - [ ] 新增 文件
     * - [ ] 修改
     * - [ ] 删除
     *
     * 再开个controller
     * - [ ] 上传数据/区分不同的业务类型
     *
     */
    @Autowired
    BackMenuService backMenuService;
    /**
     * 保存menu
     */
    @PostMapping("/save")
    public ResultE<MenuVo> save(@AuthenticationPrincipal CustomUser customUser, @RequestBody String body) {
        return new ResultE<MenuVo>().execute(e->
                    e.setSuccess(backMenuService.insert(new MenuDto(), customUser.getId(), JSONObject.parseObject(body, MenuVo.class)))
        );
    }

    /**
     * 标记删除
     */
    public void deleteMark(){

    }

    /**
     * 标记是否展示
     */
    public void deleteshow(){

    }

    public void update(){

    }

}
