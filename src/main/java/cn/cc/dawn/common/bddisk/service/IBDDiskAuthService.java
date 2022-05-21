package cn.cc.dawn.common.bddisk.service;

import cn.cc.dawn.common.bddisk.dto.BDDiskTokenResultDto;

public interface IBDDiskAuthService {

    String getUserAuthUrl();

    String getAccessToken(String userUid, int userid, String code,boolean coverOldToken);

    BDDiskTokenResultDto refreshAccessToken(String userUid, String refresh_token);

}
