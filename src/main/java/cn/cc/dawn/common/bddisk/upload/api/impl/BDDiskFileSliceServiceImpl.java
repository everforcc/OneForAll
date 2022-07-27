/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 17:35
 */

package cn.cc.dawn.common.bddisk.upload.api.impl;

import cn.cc.dawn.common.bddisk.accesstoken.serv.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.upload.api.IBDDiskFileSliceApi;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFileSliceUploadReqDto;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.config.init.yml.APPConfigurationBDDisk;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * 2. 分片上传
 */
@Slf4j
@Service
public class BDDiskFileSliceServiceImpl implements IBDDiskFileSliceApi {

    @Resource
    IHttp httpApacheImpl;
    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;
    @Autowired
    APPConfigurationBDDisk appConfigurationBDDisk;

    /**
     * 2.分片上传
     *
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 文件信息
     */
    @Override
    public BDFileVo sliceUploadFile(BDFileVo bdFileVo, int userid) {
        String access_token = ibdDiskOfferAuthService.offerAccessToken(userid);
        BDDiskFileSliceUploadReqDto bdDiskFileSliceUploadReqDto = new BDDiskFileSliceUploadReqDto(access_token, bdFileVo.getUploadid(), bdFileVo.getTargetPath());
        File[] files = bdFileVo.getFiles();
        int length = files.length;
        for (int i = 0; i < length; i++) {
            log.info("正在上传分片 - {}", i);
            HttpParamDto httpParamDto = new HttpParamDto();
            httpParamDto.setHttpTypeEnum(HttpTypeEnum.POST);

            bdDiskFileSliceUploadReqDto.setPartseq(i);


            String url = bdDiskFileSliceUploadReqDto.toStringWithBaseUrl(appConfigurationBDDisk.getSlice_file());
            log.info("分片上传url, {}", url);
            httpParamDto.setUrl(url);
            httpParamDto.setFile(files[i]);
            String resultJson = httpApacheImpl.getMsg(httpParamDto);

            log.info("分片上传结果: {}", resultJson);
        }

        return null;
    }

}
