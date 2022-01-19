package cn.cc.dawn.common.controller;

import cn.cc.dawn.common.dto.AccountsDto;
import cn.cc.dawn.common.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/common")
@RestController
//@RequiredArgsConstructor
public class AccountsController {


    @Autowired
    private AccountsService accountsService;

    @GetMapping("/findAll")
    public List<AccountsDto> findAll(){
        return accountsService.selectAll();
    }

}
