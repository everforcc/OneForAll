/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-23 17:35
 * Copyright
 */

package cn.cc.dawn.common.bddisk.upload.service.impl;

import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFileSliceUploadReqDto;
import cn.cc.dawn.common.bddisk.upload.service.IBDDiskFileSliceService;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.config.init.yml.APPConfigurationBDDisk;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.http.IHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Slf4j
@Service
public class BDDiskFileSliceServiceImpl implements IBDDiskFileSliceService {

    @Resource
    IHttp httpApacheImpl;
    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;
    @Autowired
    APPConfigurationBDDisk appConfigurationBDDisk;

    @Override
    public BDFileVo sliceUploadFile(BDFileVo bdFileVo, int userid) {
        String access_token = ibdDiskOfferAuthService.offerAccessToken(userid);
        BDDiskFileSliceUploadReqDto bdDiskFileSliceUploadReqDto = new BDDiskFileSliceUploadReqDto(access_token,bdFileVo.getUploadid(),bdFileVo.getTargetPath());
        File[] files = bdFileVo.getFiles();
        int length = files.length;
        for(int i=0; i<length; i++){
            log.info("正在上传分片 - {}",i);
            HttpParamDto httpParamDto = new HttpParamDto();
            httpParamDto.setHttpTypeEnum(HttpTypeEnum.POST);

            bdDiskFileSliceUploadReqDto.setPartseq(i);


            String url = bdDiskFileSliceUploadReqDto.toStringWithBaseUrl(appConfigurationBDDisk.getSlice_file());
            log.info("分片上传url, {}",url);
            httpParamDto.setUrl(url);
            httpParamDto.setFile(files[i]);
            String resultJson = httpApacheImpl.getMsg(httpParamDto);

            log.info("分片上传结果: {}",resultJson);
        }

        return null;
    }

}
