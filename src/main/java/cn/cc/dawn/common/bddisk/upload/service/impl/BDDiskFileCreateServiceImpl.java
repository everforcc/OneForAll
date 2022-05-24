/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-23 18:54
 * Copyright
 */

package cn.cc.dawn.common.bddisk.upload.service.impl;

import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFileCreateUploadReqDto;
import cn.cc.dawn.common.bddisk.upload.service.IBDDiskFileCreateService;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.config.init.yml.APPConfigurationBDDisk;
import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.http.IHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BDDiskFileCreateServiceImpl implements IBDDiskFileCreateService {

    @Resource
    IHttp httpApacheImpl;
    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;
    @Autowired
    APPConfigurationBDDisk appConfigurationBDDisk;

    @Override
    public boolean createUploadFile(BDFileVo bdFileVo, int userid) {
        String access_token = ibdDiskOfferAuthService.offerAccessToken(userid);
        BDDiskFileCreateUploadReqDto bdDiskFileCreateUploadReqDto = new BDDiskFileCreateUploadReqDto(access_token);

        String url = bdDiskFileCreateUploadReqDto.toStringWithBaseUrl(appConfigurationBDDisk.getXpan_file());
        log.info("文件合并请求接口, {}",url);
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setHttpTypeEnum(HttpTypeEnum.POST);
        httpParamDto.setUrl(url);

        Map<String,String> map = new HashMap<>();
        //map.put("path", URLEncoder.encode(bdFileVo.getTargetPath(), CharsetsEnum.UTF_8.charset.toString()));
        //map.put("path", "/apps/everforcc/" + URLEncoder.encode(bdFileVo.getTargetPath(), CharsetsEnum.UTF_8.charset.toString()));
        map.put("path",bdFileVo.getTargetPath());
        map.put("size",Long.toString(bdFileVo.getSize()));
        map.put("isdir","0");
        map.put("block_list",bdFileVo.getBlock_list().toString());
        map.put("uploadid",bdFileVo.getUploadid());
        //httpParamDto.setHeaders(map);
        httpParamDto.setFormDates(map);
        String result = httpApacheImpl.getMsg(httpParamDto);
        log.info("合并文件返回值, {}",result);
        return false;
    }

}
