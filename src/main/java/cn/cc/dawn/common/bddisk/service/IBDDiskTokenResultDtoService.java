package cn.cc.dawn.common.bddisk.service;

import cn.cc.dawn.common.bddisk.dto.BDDiskTokenResultDto;

public interface IBDDiskTokenResultDtoService {

    int insert(BDDiskTokenResultDto bdDiskTokenResultDto, int userid);

    int update(BDDiskTokenResultDto bdDiskTokenResultDto, int userid);

    BDDiskTokenResultDto select(int userid);

}
