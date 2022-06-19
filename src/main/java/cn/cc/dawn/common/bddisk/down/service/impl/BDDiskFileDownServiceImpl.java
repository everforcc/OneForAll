/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-24 09:52
 */

package cn.cc.dawn.common.bddisk.down.service.impl;

import cn.cc.dawn.common.bddisk.down.service.IBDDiskFileDownService;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.http.IHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class BDDiskFileDownServiceImpl implements IBDDiskFileDownService {

    @Resource
    IHttp httpApacheImpl;

    @Override
    public boolean downFile(int userid) {
        String url = "https://d.pcs.baidu.com/file/4f9495d5fre239f72d8ad58b3e220a0a?fid=707622832-250528-931715956914422&rt=pr&sign=FDtAERV-DCb740ccc5511e5e8fedcff06b081203-oa5Zovhno9fEk2cxUVQK4yjibQI%3D&expires=8h&chkbd=0&chkv=2&dp-logid=915141329562444536&dp-callid=0&dstime=1653356810&r=877726781&origin_appid=24975976&file_type=0&access_token=121.85a568e448d5ad462d6cb7767213f4f1.Y3h2wbrPks0TNvY9c31pP5Ow1N-VffmzkfkXHrO.uwt9TA";

        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setHttpTypeEnum(HttpTypeEnum.GET);
        httpParamDto.setUrl(url);
        httpParamDto.setTargetFilePath("E:\\filesystem\\project\\OneForAll\\webapi\\bddisk\\down");

        httpApacheImpl.getFile(httpParamDto);

        return false;
    }
}
