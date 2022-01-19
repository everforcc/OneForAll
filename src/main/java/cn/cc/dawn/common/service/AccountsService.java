package cn.cc.dawn.common.service;

import cn.cc.dawn.common.dao.AccountsDaoMapper;
import cn.cc.dawn.common.dto.AccountsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public class AccountsService {

    @Resource
    private AccountsDaoMapper accountsDaoMapper;

    public List<AccountsDto> selectAll(){
        return accountsDaoMapper.selectAll();
    }

}
