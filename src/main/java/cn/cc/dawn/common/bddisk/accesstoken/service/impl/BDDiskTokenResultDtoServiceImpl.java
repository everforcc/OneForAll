/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 18:32
 */

package cn.cc.dawn.common.bddisk.accesstoken.service.impl;

import cn.cc.dawn.common.bddisk.accesstoken.dao.IBDDiskTokenResultDaoMapper;
import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;
import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskTokenResultDtoService;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.annotation.ServiceAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@ServiceAspect
@Service
@Slf4j
public class BDDiskTokenResultDtoServiceImpl implements IBDDiskTokenResultDtoService {

    @Resource
    IBDDiskTokenResultDaoMapper ibdDiskTokenResultDaoMapper;

    /**
     * 入库，只支持一个，插入前先清空历史数据
     *
     * @param bdDiskTokenResultDto
     * @param userid
     * @return
     */
    @Override
    public int insert(BDDiskTokenResultDto bdDiskTokenResultDto, int userid) {
        AppCode.A00201.assertHasInsert(ibdDiskTokenResultDaoMapper.insert(bdDiskTokenResultDto));
        return 1;
    }

    /**
     * 数据不做更新，每次插入新的数据，保存历史记录
     * @param bdDiskTokenResultDto
     * @param userid
     * @return
     */
    @Override
    public int update(BDDiskTokenResultDto bdDiskTokenResultDto, int userid) {
        AppCode.A00201.assertHasUpdate(ibdDiskTokenResultDaoMapper.update(bdDiskTokenResultDto));
        return 1;
    }

    @Override
    public BDDiskTokenResultDto select(int userid) {
        return ibdDiskTokenResultDaoMapper.select(userid);
    }

}
