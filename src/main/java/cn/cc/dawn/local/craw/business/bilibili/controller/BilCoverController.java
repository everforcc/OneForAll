package cn.cc.dawn.local.craw.business.bilibili.controller;

import cn.cc.dawn.local.craw.business.bilibili.flow.Bilibili_Cover;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Yukino
 * 2020/6/22
 */
@Slf4j
@RequestMapping("/local/craw/bilcover")
@RestController
public class BilCoverController {

    /**
     * controller
     * 管理链接，和前端对接
     */
    Bilibili_Cover bilibili_cover = new Bilibili_Cover();

    @RequestMapping("/bilibili_cover/{num}")
    public ResultE<String> getCover(@PathVariable String num) {
        log.info(num);
        return new ResultE<String>().execute(e ->
                e.setSuccess(bilibili_cover.getBilCover(num))
        );
    }

}
