package cn.cc.dawn.common.bddisk.accesstoken.controller;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;
import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskAuthApi;
import cn.cc.dawn.common.bddisk.accesstoken.serv.IBDDiskOfferAuthService;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认证流程
 */
@RestController
@RequestMapping(value = "/common/bddisk/auth")
public class BDDiskAuthController {

    /**
     * 代码逻辑
     * 1. 一个用户只能绑定一个(有别的需求了再说)
     * 2. 用户请求code，code换access_token
     * 3. 将返回数据入库
     * 4. 初始化的时候请求并获取数据，并入库
     * 5. 后续直接从接口获取数据，
     * 5.1 先从缓存取，
     * 5.2 取不出来，查数据库拿到refere刷新，存redis并返回
     * 5.3 refere也到期了，返回让用户重新走步奏1
     */

    @Autowired
    IBDDiskAuthApi ibdDiskAuthApi;

    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;

    /**
     * 1. 这一步认证后的结果返回给前端，登录用户带着请求后端
     *
     * @return 扫码地址
     */
    @GetMapping("/getUserAuthUrl")
    public ResultE<String> getUserAuthUrl(@AuthenticationPrincipal CustomUser customUser) {
        return new ResultE<String>().execute(e -> {
            e.setSuccess(ibdDiskAuthApi.getUserAuthUrl());
        });
    }

    // 2. 回传地址先放到隔壁类，没有前端配合有点麻烦
    /*@GetMapping("/redirectForCode")
    public ResultE<String> redirectForCode(){
        return new ResultE<String>().execute(e ->{
            e.setSuccess(ibdDiskAuthService.getUserAuthUrl());
        });
    }*/

    /**
     * 3. 用code换access_token
     * 如果有旧的就直接用
     */
    @GetMapping("/getUserAuthUrl/{coverOldToken}/{code}")
    public ResultE<String> getAccessTokenByCode(@AuthenticationPrincipal CustomUser customUser, @PathVariable("coverOldToken") boolean coverOldToken, @PathVariable("code") String code) {
        return new ResultE<String>().execute(e -> {
            e.setSuccess(ibdDiskAuthApi.getAccessToken(customUser.getId(), code, coverOldToken));
        });
    }
    /*
     * 3. 用code换access_token
     * 如果有旧的就覆盖掉
     * @param customUser
     * @param code
     * @return
     */
    /*@GetMapping("/getUserAuthUrlCover/{code}")
    public ResultE<String> getUserAuthUrlCover(@AuthenticationPrincipal CustomUser customUser,@PathVariable("code") String code){
        return new ResultE<String>().execute(e ->{
            e.setSuccess(ibdDiskAuthService.getAccessToken(customUser.getUuid(), customUser.getId(), code, true));
        });
    }*/

    /**
     * 4. 刷新token，是用来测试，实际刷新在接口5
     *
     * @param refresh_token 刷新token数据
     * @return 刷新后的token
     */
    //@GetMapping("/refreshAccessToken/{refresh_token}")
    public ResultE<BDDiskTokenResultDto> refreshAccessToken(@AuthenticationPrincipal CustomUser customUser, @PathVariable("refresh_token") String refresh_token) {
        return new ResultE<BDDiskTokenResultDto>().execute(e -> {
            e.setSuccess(ibdDiskAuthApi.refreshAccessToken(customUser.getId(), refresh_token));
        });
    }

    /**
     * 5. 用户授权后，这个接口
     * 可以获得可用的token，最长时间不超过一个小时
     *
     * @param customUser 当前用户
     * @return 用户的token
     */
    // ibdDiskOfferAuthService
    @GetMapping("/getBDDiskToken")
    public ResultE<String> getBDDiskToken(@AuthenticationPrincipal CustomUser customUser) {
        return new ResultE<String>().execute(e -> {
            e.setSuccess(ibdDiskOfferAuthService.offerAccessToken(customUser.getId()));
        });
    }


}
