package cn.cc.dawn.common.dao;

import cn.cc.dawn.common.dto.AccountsDto;

import java.util.List;

public interface AccountsDaoMapper {

    List<AccountsDto> selectAll();

}
