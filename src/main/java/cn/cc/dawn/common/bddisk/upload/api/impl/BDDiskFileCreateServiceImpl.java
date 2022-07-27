/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 18:54
 */

package cn.cc.dawn.common.bddisk.upload.api.impl;

import cn.cc.dawn.common.bddisk.accesstoken.serv.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.upload.api.IBDDiskFileCreateApi;
import cn.cc.dawn.common.bddisk.upload.dto.BDDiskFileCreateUploadReqDto;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.config.init.yml.APPConfigurationBDDisk;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 3. 合并文件
 */
@Service
@Slf4j
public class BDDiskFileCreateServiceImpl implements IBDDiskFileCreateApi {

    @Resource
    IHttp httpApacheImpl;
    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;
    @Autowired
    APPConfigurationBDDisk appConfigurationBDDisk;

    /**
     * 3. 合并文件
     *
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 合并结果
     */
    @Override
    public boolean createUploadFile(BDFileVo bdFileVo, int userid) {
        try {
            String access_token = ibdDiskOfferAuthService.offerAccessToken(userid);
            BDDiskFileCreateUploadReqDto bdDiskFileCreateUploadReqDto = new BDDiskFileCreateUploadReqDto(access_token);

            String url = bdDiskFileCreateUploadReqDto.toStringWithBaseUrl(appConfigurationBDDisk.getXpan_file());
            log.info("文件合并请求接口, {}", url);
            HttpParamDto httpParamDto = new HttpParamDto();
            httpParamDto.setHttpTypeEnum(HttpTypeEnum.POST);
            httpParamDto.setUrl(url);

            Map<String, String> map = new HashMap<>();
            //map.put("path", URLEncoder.encode(bdFileVo.getTargetPath(), CharsetsEnum.UTF_8.charset.toString()));
            //map.put("path", "/apps/everforcc/" + URLEncoder.encode(bdFileVo.getTargetPath(), CharsetsEnum.UTF_8.charset.toString()));
            map.put("path", bdFileVo.getTargetPath());
            map.put("size", Long.toString(bdFileVo.getSize()));
            map.put("isdir", "0");
            map.put("block_list", bdFileVo.getBlock_list().toString());
            map.put("uploadid", bdFileVo.getUploadid());
            //httpParamDto.setHeaders(map);
            httpParamDto.setFormDates(map);
            String result = httpApacheImpl.getMsg(httpParamDto);
            log.info("合并文件返回值, {}", result);
        } catch (Exception e) {
            // 这块还要细化处理
            e.printStackTrace();
        } finally {
            // 上传完毕需要删除文件
            bdFileVo.setUploadResult(true);
        }
        return false;
    }

}
