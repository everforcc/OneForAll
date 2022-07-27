/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 10:10
 */

package cn.cc.dawn.common.bddisk.upload.api.impl;

import cn.cc.dawn.common.bddisk.accesstoken.serv.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFilePreUploadReqDto;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFilePreUploadResDto;
import cn.cc.dawn.common.bddisk.upload.api.IBDDiskFilePreUploadApi;
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

/**
 * 1. 预上传
 */
@Slf4j
@Service
public class BDDiskFilePreUploadServiceImpl implements IBDDiskFilePreUploadApi {

//    @Autowired
//    IFile iFile;
    @Resource
    IHttp httpApacheImpl;
    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;
    @Autowired
    APPConfigurationBDDisk appConfigurationBDDisk;

    /**
     * 1. 预上传
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 文件信息
     */
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
