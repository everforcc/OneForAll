package cn.cc.dawn.common.bddisk.accesstoken.service;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;

public interface IBDDiskAuthService {

    String getUserAuthUrl();

    String getAccessToken(int userid, String code,boolean coverOldToken);

    BDDiskTokenResultDto refreshAccessToken(int userid, String refresh_token);

}
