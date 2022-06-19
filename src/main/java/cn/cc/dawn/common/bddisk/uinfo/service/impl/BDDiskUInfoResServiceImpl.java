/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-22 11:30
 */

package cn.cc.dawn.common.bddisk.uinfo.service.impl;

import cn.cc.dawn.common.bddisk.uinfo.dao.BDDiskUInfoResMapper;
import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;
import cn.cc.dawn.common.bddisk.uinfo.service.IBDDiskUInfoResService;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.annotation.ServiceAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@ServiceAspect
@Slf4j
@Service
public class BDDiskUInfoResServiceImpl implements IBDDiskUInfoResService {

    @Resource
    BDDiskUInfoResMapper bdDiskUInfoResMapper;

    @Override
    public int insert(BDDiskUInfoResDto bdDiskUInfoResDto, int userid) {
        AppCode.A00201.assertHasInsert(bdDiskUInfoResMapper.insert(bdDiskUInfoResDto));
        return 1;
    }

    @Override
    public BDDiskUInfoResDto select(int userid) {
        return bdDiskUInfoResMapper.select(userid);
    }
}
