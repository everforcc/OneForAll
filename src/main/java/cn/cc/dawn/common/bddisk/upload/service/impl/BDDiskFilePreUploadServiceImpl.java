/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-23 10:10
 * Copyright
 */

package cn.cc.dawn.common.bddisk.upload.service.impl;

import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFilePreUploadReqDto;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFilePreUploadResDto;
import cn.cc.dawn.common.bddisk.upload.service.IBDDiskFilePreUploadService;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.config.init.yml.APPConfigurationBDDisk;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.http.IHttp;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class BDDiskFilePreUploadServiceImpl implements IBDDiskFilePreUploadService {

//    @Autowired
//    IFile iFile;
    @Resource
    IHttp httpApacheImpl;
    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;
    @Autowired
    APPConfigurationBDDisk appConfigurationBDDisk;

    @Override
    public BDFileVo preUploadFile(BDFileVo bdFileVo, int userid) {
//        String localPath = bdFileVo.getLocalPath();
//        String targetPath = bdFileVo.getTargetPath();

        String access_token = ibdDiskOfferAuthService.offerAccessToken(userid);
        BDDiskFilePreUploadReqDto bdDiskFilePreUploadReqDto = new BDDiskFilePreUploadReqDto(bdFileVo, access_token);
        String xpan_file = appConfigurationBDDisk.getXpan_file();
        String xpan_file_preUrl = bdDiskFilePreUploadReqDto.toStringWithBaseUrl(xpan_file);

        log.info("请求url: {}",xpan_file_preUrl);
        HttpParamDto httpParamDto = new HttpParamDto();

        httpParamDto.setHttpTypeEnum(HttpTypeEnum.POST);
        httpParamDto.setUrl(xpan_file_preUrl);

        //String content = JSONObject.toJSONString(bdDiskFilePreUploadReqDto);
        //log.info("content: {}",content);
        //httpParamDto.setContent(content);

        String resultJson = httpApacheImpl.getMsg(httpParamDto);
        log.info("请求返回json, {}",resultJson);
        BDDiskFilePreUploadResDto bdDiskFilePreUploadResDto = JSONObject.parseObject(resultJson, BDDiskFilePreUploadResDto.class);
        JSONObject resultJSONObject = JSONObject.parseObject(resultJson);
        // 如果等于2表示已经上传完毕
        if("2".equals(bdDiskFilePreUploadResDto.getReturn_type())){
            bdFileVo.setUploadResult(true);
        }else {
            bdFileVo.setUploadid(resultJSONObject.getString("uploadid"));
            bdFileVo.setSize(bdDiskFilePreUploadReqDto.getSize());
        }

        return bdFileVo;
    }
}
