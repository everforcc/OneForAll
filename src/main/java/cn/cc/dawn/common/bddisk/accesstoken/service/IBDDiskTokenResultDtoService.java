package cn.cc.dawn.common.bddisk.accesstoken.service;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;

public interface IBDDiskTokenResultDtoService {

    int insert(BDDiskTokenResultDto bdDiskTokenResultDto, int userid);

    int update(BDDiskTokenResultDto bdDiskTokenResultDto, int userid);

    BDDiskTokenResultDto select(int userid);

}
